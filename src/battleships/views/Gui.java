package battleships.views;

import javax.swing.*;
import java.awt.*;

public class Gui {

    public String afloatStatusTrue = "Afloat and battle ready";
    public String afloatStatusFlase = "Sunk to the bottom of the sea";
    public JFrame Frame = new JFrame("Capple games: Battleships");
    public JPanel Boardall = new JPanel(new GridLayout(0,2));
    public JPanel Board = new JPanel(new GridLayout(10,10));
    public JPanel Board2 = new JPanel(new GridLayout(10,10));
    public JPanel Sidepanelleft = new JPanel();
    public JPanel Sidepanelright = new JPanel();
    public JPanel Infopanelleft = new JPanel(new GridLayout(5,0));
    public JPanel Infopanelright = new JPanel(new GridLayout(5,0));
    public JTextArea Onderzeeer = new JTextArea("ONDERZEEER \n" + "Status: " + afloatStatusTrue);
    public JTextArea Patrouilleschip = new JTextArea("PATROUILLESCHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea Slagschip = new JTextArea("SLAGSCHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea Vliegdekschip = new JTextArea("VLIEGDEKSCHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea Onderzeeer2 = new JTextArea("ONDERZEEER \n" + "Status: " + afloatStatusTrue);
    public JTextArea Patrouilleschip2 = new JTextArea("PATROUILLESCHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea Slagschip2 = new JTextArea("SLAGSCHIP \n" + "Status: " + afloatStatusTrue);
    public JTextArea Vliegdekschip2 = new JTextArea("VLIEGDEKSCHIP \n" + "Status: " + afloatStatusTrue);

    public JButton[][] Buttons = new JButton[10][10];
    public JButton[][] Buttons2 = new JButton[10][10];

    public JButton Connect = new JButton("Connect to a host");
    public JButton Closeconnection = new JButton("Close connection");
    public JButton SendCoordinates = new JButton("Send Coordinates");
    public JLabel WhatisIP = new JLabel();
    public JPanel MenuBar = new JPanel(new GridLayout(4,0));
    public JPanel Fill = new JPanel();

    ImageIcon Ship = new ImageIcon(getClass().getResource("Ship.png"));
    ImageIcon Shiphit = new ImageIcon(getClass().getResource("Shiphit.png"));
    ImageIcon Bomb = new ImageIcon(getClass().getResource("Bomb.png"));

    public Gui(){

    }
}
