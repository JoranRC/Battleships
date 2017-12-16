package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    Gui gui;
    Ship ship1;
    Ship ship2;
    Ship ship3;
    Ship ship4;
    public static int orientation = 0;
    public static int shipLength = 4;
    public static ArrayList<int[]> excludeList = new ArrayList<>();

    ImageIcon ship = new ImageIcon(Gui.class.getResource("Ship.png"));
    ImageIcon shipHit = new ImageIcon(Gui.class.getResource("Shiphit.png"));
    ImageIcon bomb = new ImageIcon(Gui.class.getResource("Bomb.png"));


    public Game(Gui gui) {
        this.gui = gui;
        int[] integerArrayTest = {0,0};
        HandlerClass handler = new HandlerClass(integerArrayTest, this);
        gui.buttons[0][0].addActionListener(handler);
    }

    public ArrayList<int[]> returnApprovedLocation(int orientation, int shipLength, int[] startLocation){
        ArrayList<int[]> location = new ArrayList<>();
        int[] integerArray = startLocation;
        int[] integerArray1 = new int[2];
        int[] integerArray2 = new int[2];
        int[] integerArray3 = new int[2];

        int startL = integerArray[0];
        int startR = integerArray[1];

        if(orientation == 0){
            switch(shipLength){
                case 1:
                    location.add(integerArray);
                    break;
                case 2:
                    location.add(integerArray);
                    integerArray1[0] = startL;
                    integerArray1[1] = startR + 1;
                    location.add(integerArray1);
                    break;
                case 3:
                    location.add(integerArray);
                    integerArray1[0] = startL;
                    integerArray1[1] = startR + 1;
                    location.add(integerArray1);
                    integerArray2[0] = startL;
                    integerArray2[1] = startR + 2;
                    location.add(integerArray2);
                    break;
                case 4:
                    location.add(integerArray);
                    integerArray1[0] = startL;
                    integerArray1[1] = startR + 1;
                    location.add(integerArray1);
                    integerArray2[0] = startL;
                    integerArray2[1] = startR + 2;
                    location.add(integerArray2);
                    integerArray3[0] = startL;
                    integerArray3[1] = startR + 3;
                    location.add(integerArray3);
                    break;
                default:
                    break;
            }
        } else {
            switch(shipLength){
                case 1:
                    location.add(integerArray);
                    break;
                case 2:
                    integerArray1[0] = startL + 1;
                    integerArray1[1] = startR;
                    location.add(integerArray1);
                    break;
                case 3:
                    integerArray1[0] = startL + 1;
                    integerArray1[1] = startR;
                    location.add(integerArray1);
                    integerArray2[0] = startL + 2;
                    integerArray2[1] = startR;
                    location.add(integerArray2);
                    break;
                case 4:
                    integerArray1[0] = startL + 1;
                    integerArray1[1] = startR;
                    location.add(integerArray1);
                    integerArray2[0] = startL + 2;
                    integerArray2[1] = startR;
                    location.add(integerArray2);
                    integerArray3[0] = startL + 3;
                    integerArray3[1] = startR;
                    location.add(integerArray3);
                    break;
                default:
                    break;
            }
        }

        int arrayListLength = location.size();
        arrayListLength--;
        int[] returnIntegerArray = location.get(arrayListLength);
        startL = returnIntegerArray[0];
        startR = returnIntegerArray[1];

        try{
            gui.buttons[startL][startR].setEnabled(false);
            return location;
        }catch(ArrayIndexOutOfBoundsException ae){
            return null;
        }
    }

    public void placeShip(int orientation, int shipLength, int[] startLocation){
        if(returnApprovedLocation(orientation, shipLength, startLocation) == null){
            JOptionPane.showMessageDialog(null, "The ship cannot be placed here");
        } else {

            ArrayList<int[]> approvedLocation = returnApprovedLocation(orientation, shipLength, startLocation);

            switch(shipLength){
                case 1:
                    ship1 = new Ship(orientation, true, shipLength, approvedLocation);
                    break;
                case 2:
                    ship2 = new Ship(orientation, true, shipLength, approvedLocation);
                    break;
                case 3:
                    ship3 = new Ship(orientation, true, shipLength, approvedLocation);
                    break;
                case 4:
                    ship4 = new Ship(orientation, true, shipLength, approvedLocation);
                    break;
                default:
                    break;
            }

            for(int[] integerArray : approvedLocation){
                int leftSide = integerArray[0];
                int rightSide = integerArray[1];
                int[] excludePosition = {--leftSide,rightSide};
                System.out.println(excludePosition[0]);
                System.out.println(excludePosition[1]);
                int[] excludePosition2 = {++leftSide, rightSide};
                int[] excludePosition3 = {leftSide,--rightSide};
                int[] excludePosition4 = {leftSide, ++rightSide};
                excludeList.add(excludePosition);
                excludeList.add(excludePosition2);
                excludeList.add(excludePosition3);
                excludeList.add(excludePosition4);
                excludeList.add(integerArray);
                System.out.println(excludeList);
                gui.buttons[leftSide][rightSide].setIcon(ship);
                gui.buttons[leftSide][rightSide].setDisabledIcon(ship);
                gui.buttons[leftSide][rightSide].setEnabled(false);
            }


        }

    }

}
