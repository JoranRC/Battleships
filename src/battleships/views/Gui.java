package battleships.views;

import battleships.controllers.Game;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    public String afloatStatusTrue = "Afloat and battle ready";
    public String afloatStatusFlase = "Sunk to the bottom of the sea";
    public JFrame frame = new JFrame("Capple games: Battleships");
    public JPanel boardAll = new JPanel(new GridLayout(0,2));
    public JPanel board = new JPanel(new GridLayout(10,10));
    public JPanel board2 = new JPanel(new GridLayout(10,10));
    public JPanel sidePanelLeft = new JPanel();
    public JPanel sidePanelRight = new JPanel();
    public JPanel infoPanelLeft = new JPanel(new GridLayout(5,0));
    public JPanel infoPanelRight = new JPanel(new GridLayout(5,0));
    public JTextArea submarine = new JTextArea("SUBMARINE \n" + "Status: " + afloatStatusTrue);
    public JTextArea patrolShip = new JTextArea("PATROL SHIP\n" + "Status: " + afloatStatusTrue);
    public JTextArea battleShip = new JTextArea("BATTLESHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea aircraftCarrier = new JTextArea("AIRCRAFT CARRIER\n" + "Status: " + afloatStatusTrue);
    public JTextArea submarine2 = new JTextArea("SUBMARINE \n" + "Status: " + afloatStatusTrue);
    public JTextArea patrolShip2 = new JTextArea("PATROL SHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea battleShip2 = new JTextArea("BATTLESHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea aircraftCarrier2 = new JTextArea("AIRCRAFT CARRIER \n" + "Status: " + afloatStatusTrue);

    public JButton[][] buttons = new JButton[10][10];
    public JButton[][] buttons2 = new JButton[10][10];

    public JButton connect = new JButton("Connect to a host");
    public JButton closeconnection = new JButton("Close connection");
    public JButton sendCoordinates = new JButton("Send Coordinates");
    public JButton changeOrientation = new JButton("Orientation: Horizontal");
    public JLabel whatisIP = new JLabel();
    public JPanel menuBar = new JPanel(new GridLayout(4,0));
    public JPanel fill = new JPanel();

    ImageIcon ship = new ImageIcon(getClass().getResource("Ship.png"));
    ImageIcon shipHit = new ImageIcon(getClass().getResource("Shiphit.png"));
    ImageIcon bomb = new ImageIcon(getClass().getResource("Bomb.png"));

    public Gui(){

        for(int i=0; i<buttons.length; i++) {
            for(int j=0; j<buttons[i].length; j++) {
                buttons[i][j] = new JButton(i + " " + j);
                board2.add(buttons[i][j]);
            }
        }

        for(int i=0; i<buttons2.length; i++) {
            for(int j=0; j<buttons2[i].length; j++) {
                buttons2[i][j] = new JButton(i + " " + j);
                board.add(buttons2[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 540);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        board.setPreferredSize(new Dimension(540, 540));
        board2.setPreferredSize(new Dimension(540, 540));
        boardAll.setPreferredSize(new Dimension(1500, 540));
        boardAll.add(board2);
        boardAll.add(board);
        menuBar.setPreferredSize(new Dimension(210, 80));
        menuBar.setBackground(Color.WHITE);
        menuBar.add(connect);
        menuBar.add(closeconnection);
        menuBar.add(sendCoordinates);
        menuBar.add(whatisIP);
        fill.setPreferredSize(new Dimension(210,80));
        changeOrientation.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if(Game.orientation == 0){
                            Game.orientation = 1;
                            changeOrientation.setText("Orientation: Vertical");
                        } else {
                            Game.orientation = 0;
                            changeOrientation.setText("Orientation: Horizontal");
                        }
                    }
                });
        fill.add(changeOrientation);
        fill.setBackground(Color.WHITE);
        infoPanelRight.setPreferredSize(new Dimension(210, 460));
        infoPanelRight.setBackground(Color.WHITE);
        infoPanelRight.setBorder(BorderFactory.createTitledBorder(null, "Schepen tegenstander", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("times new roman",Font.PLAIN, 16), Color.BLUE));
        infoPanelLeft.setBorder(BorderFactory.createTitledBorder(null, "Eigen schepen",TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("times new roman",Font.PLAIN, 16), Color.BLUE));
        infoPanelLeft.setPreferredSize(new Dimension(210, 460));
        infoPanelLeft.setBackground(Color.WHITE);
        sidePanelLeft.setPreferredSize(new Dimension(210,540));
        sidePanelRight.setPreferredSize(new Dimension(210,540));
        sidePanelLeft.setBackground(Color.WHITE);
        sidePanelRight.setBackground(Color.WHITE);
        infoPanelLeft.add(submarine2);
        infoPanelLeft.add(patrolShip2);
        infoPanelLeft.add(battleShip2);
        infoPanelLeft.add(aircraftCarrier2);
        sidePanelLeft.add(menuBar, BorderLayout.NORTH);
        sidePanelLeft.add(infoPanelLeft);
        infoPanelRight.add(submarine);
        infoPanelRight.add(patrolShip);
        infoPanelRight.add(battleShip);
        infoPanelRight.add(aircraftCarrier);
        sidePanelRight.add(fill, BorderLayout.NORTH);
        sidePanelRight.add(infoPanelRight, BorderLayout.SOUTH);

        frame.add(sidePanelLeft, BorderLayout.WEST);
        frame.add(sidePanelRight, BorderLayout.EAST);
        frame.add(boardAll, BorderLayout.CENTER);
    }

    public void disableButtonsBoard(){
        for(int i=0; i<buttons.length; i++) {
            for(int j=0; j<buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void enableButtonsBoard(){
        for(int i=0; i<buttons.length; i++) {
            for(int j=0; j<buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void disableButtonsBoard2(){
        for(int i=0; i<buttons2.length; i++) {
            for(int j=0; j<buttons2[i].length; j++) {
                buttons2[i][j].setEnabled(false);
            }
        }
    }

    public void enableButtonsBoard2(){
        for(int i=0; i<buttons2.length; i++) {
            for(int j=0; j<buttons2[i].length; j++) {
                buttons2[i][j].setEnabled(false);
            }
        }
    }
}
