package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class HandlerClass2 implements ActionListener {

    int[] arguments;
    Game gameController;
    Gui gui;

    public HandlerClass2(int[] arguments, Game gameController, Gui gui) {
        this.arguments = arguments;
        this.gameController = gameController;
        this.gui = gui;
    }

    private boolean checkIfGameOver(){
        if(!gameController.shipOP.isAfloat() && !gameController.ship2OP.isAfloat() && !gameController.ship3OP.isAfloat() && !gameController.ship4OP.isAfloat()){
            gui.battleShip.setText("BATTLESHIP \n" + "Status: " + gui.afloatStatusFlase);
            gui.submarine.setText("SUBMARINE \n" + "Status: " + gui.afloatStatusFlase);
            gui.patrolShip.setText("PATROL SHIP\n" + "Status: " + gui.afloatStatusFlase);
            gui.aircraftCarrier.setText("AIRCRAFT CARRIER\n" + "Status: " + gui.afloatStatusFlase);
            return true;
        }
        return false;
    }

    private boolean shipHit(Ship newShip, int leftSide, int rightSide){
        for(int[] intCoordinateToBeChecked : newShip.getLocation()) {
            if (Arrays.equals(intCoordinateToBeChecked, arguments)) {
                newShip.getLocation().remove(intCoordinateToBeChecked);
                gui.buttons2[leftSide][rightSide].setIcon(Gui.ship);
                gui.buttons2[leftSide][rightSide].setDisabledIcon(Gui.ship);
                gui.buttons2[leftSide][rightSide].setEnabled(false);
                System.out.println(newShip.getLocation().size());
                if (newShip.getLocation().size() == 0) {
                    newShip.setAfloat(false);
                }
                if(checkIfGameOver()){
                    JOptionPane.showMessageDialog(null, "Congrats, you won!");
                    System.exit(0);
                }
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent event) {
        int leftSide = arguments[0];
        int rightSide = arguments[1];
        try {
            Connection.output.writeObject(arguments);
            Connection.output.flush();
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
        gui.disableButtonsBoard2();
        if(shipHit(gameController.ship3OP, leftSide, rightSide)){
            if(!gameController.ship3OP.isAfloat()){
                gui.battleShip.setText("BATTLESHIP \n" + "Status: " + gui.afloatStatusFlase);
            }
            return;
        }
        if(shipHit(gameController.shipOP, leftSide, rightSide)){
            if(!gameController.shipOP.isAfloat()){
                gui.submarine.setText("SUBMARINE \n" + "Status: " + gui.afloatStatusFlase);
            }
            return;
        }
        if(shipHit(gameController.ship2OP, leftSide, rightSide)){
            if(!gameController.ship2OP.isAfloat()){
                gui.patrolShip.setText("PATROL SHIP\n" + "Status: " + gui.afloatStatusFlase);
            }
            return;
        }
        if(shipHit(gameController.ship4OP, leftSide, rightSide)){
            if(!gameController.ship4OP.isAfloat()){
                gui.aircraftCarrier.setText("AIRCRAFT CARRIER\n" + "Status: " + gui.afloatStatusFlase);
            }
            return;
        }
        gui.buttons2[leftSide][rightSide].setIcon(Gui.bomb);
        gui.buttons2[leftSide][rightSide].setDisabledIcon(Gui.bomb);
        gui.buttons2[leftSide][rightSide].setEnabled(false);

    }
}
