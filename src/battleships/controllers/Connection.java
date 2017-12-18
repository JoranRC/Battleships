package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.Timer;

public class Connection {

    Gui gui;
    Game gameController;
    private boolean didUSend = false;
    private static Socket connection;
    private static Socket serverConnection;
    private static ServerSocket server;
    private static ObjectInputStream input;
    public static ObjectOutputStream output;

    private boolean noConnection = true;
    private boolean inputListening2 = true;
    private boolean inputListening = true;
    private String serverIP;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            inputBegin();
        }};

    public Connection(Gui gui, Game gameController) {
        this.gui = gui;
        this.gameController = gameController;

        gui.connect.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        noConnection = false;
                        serverIP = JOptionPane.showInputDialog("What is the IP adres of the host?");
                        try {
                            connection = new Socket(InetAddress.getByName(serverIP), 6789);
                            SetupStreams();
                            timer.schedule(task, 1000);
                            gui.connect.setEnabled(false);
                        }catch(IOException ioException) {
                            ioException.printStackTrace();
                            JOptionPane.showMessageDialog(null, "This server is unreachable");
                        }

                    }
                });
        gui.sendCoordinates.addActionListener(

                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        ArrayList<ArrayList> shipsToSend = new ArrayList<>();
                        shipsToSend.add(gameController.ship1.getLocation());
                        shipsToSend.add(gameController.ship2.getLocation());
                        shipsToSend.add(gameController.ship3.getLocation());
                        shipsToSend.add(gameController.ship4.getLocation());

                        try {
                            output.writeObject(shipsToSend);
                            output.flush();
                            gui.sendCoordinates.setEnabled(false);
                            didUSend = true;
                        }catch(IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
        gui.closeconnection.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            noConnection = false;
                            inputListening2 = false;
                            inputListening = false;
                            connection.close();
                            serverConnection.close();
                            server.close();
                            output.close();
                            input.close();
                        }catch(IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });


        while(noConnection == true) {
            try {
                server = new ServerSocket(6789, 10);
                InetAddress address = InetAddress.getLocalHost();
                String hostIP = address.getHostAddress() ;
                gui.whatisIP.setText("Adress for client: " + hostIP);
                serverConnection = server.accept();
                connection = serverConnection;
                SetupStreams();
                gui.connect.setEnabled(false);
                noConnection = false;
                inputBegin();
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void SetupStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void inputBegin() {
        noConnection = false;
        ArrayList<ArrayList> receivedShips;

        gui.enableButtonsBoard();
        while(inputListening) {
            try {
                receivedShips = (ArrayList<ArrayList>) input.readObject();
                if(receivedShips.isEmpty()) {

                }else {
                    gameController.shipOP.setLocation(receivedShips.get(0));
                    gameController.ship2OP.setLocation(receivedShips.get(1));
                    gameController.ship3OP.setLocation(receivedShips.get(2));
                    gameController.ship4OP.setLocation(receivedShips.get(3));
                    inputListening = false;
                    if(didUSend){
                        gui.enableButtonsBoard2();
                    }
                    inputSecond();
                }
            }catch(ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "\n idk what that user sent!");
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private boolean checkIfGameOver(){
        if(!gameController.ship1.isAfloat() && !gameController.ship2.isAfloat() && !gameController.ship3.isAfloat() && !gameController.ship4.isAfloat()){
            gui.battleShip2.setText("BATTLESHIP \n" + "Status: " + gui.afloatStatusFlase);
            gui.submarine2.setText("SUBMARINE \n" + "Status: " + gui.afloatStatusFlase);
            gui.patrolShip2.setText("PATROL SHIP\n" + "Status: " + gui.afloatStatusFlase);
            gui.aircraftCarrier2.setText("AIRCRAFT CARRIER\n" + "Status: " + gui.afloatStatusFlase);
            return true;
        }
        return false;
    }

    private boolean shipHit(Ship newShip, int[] coordinates){
        int leftSide = coordinates[0];
        int rightSide = coordinates[1];
        for(int[] intCoordinateToBeChecked : newShip.getLocation()) {
            if (Arrays.equals(intCoordinateToBeChecked, coordinates)) {
                newShip.getLocation().remove(intCoordinateToBeChecked);
                gui.buttons[leftSide][rightSide].setIcon(Gui.shipHit);
                gui.buttons[leftSide][rightSide].setDisabledIcon(Gui.shipHit);
                if (newShip.getLocation().size() == 0) {
                    newShip.setAfloat(false);
                }
                if(checkIfGameOver()){
                    JOptionPane.showMessageDialog(null, "I'm sorry, but you are not the winner of this game!");
                    System.exit(0);
                }
                return true;
            }
        }
        return false;
    }

    public void inputSecond() {
        int[] receivedIntArray;
        while(inputListening2){
            try {
                receivedIntArray = (int[]) input.readObject();
                if(receivedIntArray == null){

                }else{

                    gui.enableButtonsBoard2();

                    if(shipHit(gameController.ship3, receivedIntArray)){
                        if(!gameController.ship3.isAfloat()){
                            gui.battleShip2.setText("BATTLESHIP \n" + "Status: " + gui.afloatStatusFlase);
                        }
                    } else if(shipHit(gameController.ship1, receivedIntArray)){
                        if(!gameController.ship1.isAfloat()){
                            gui.submarine2.setText("SUBMARINE \n" + "Status: " + gui.afloatStatusFlase);
                        }
                    } else if(shipHit(gameController.ship2, receivedIntArray)){
                        if(!gameController.ship2.isAfloat()){
                            gui.patrolShip2.setText("PATROL SHIP\n" + "Status: " + gui.afloatStatusFlase);
                        }
                    } else if(shipHit(gameController.ship4, receivedIntArray)){
                        if(!gameController.ship4.isAfloat()){
                            gui.aircraftCarrier2.setText("AIRCRAFT CARRIER\n" + "Status: " + gui.afloatStatusFlase);
                        }
                    } else {
                        int leftSide = receivedIntArray[0];
                        int rightSide = receivedIntArray[1];
                        gui.buttons[leftSide][rightSide].setIcon(Gui.bomb);
                        gui.buttons[leftSide][rightSide].setDisabledIcon(Gui.bomb);
                        gui.buttons[leftSide][rightSide].setEnabled(false);
                    }

                }
            }catch(ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "\n idk what that user sent!");
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }





}
