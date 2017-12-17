package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    Gui gui;
    Ship ship1;
    Ship ship2;
    Ship ship3;
    Ship ship4;
    Ship shipOP;
    Ship ship2OP;
    Ship ship3OP;
    Ship ship4OP;
    public static int orientation = 0;
    public static int shipLength = 1;
    public static ArrayList<int[]> excludeList = new ArrayList<>();


    ImageIcon ship = new ImageIcon(Gui.class.getResource("Ship.png"));
    ImageIcon shipHit = new ImageIcon(Gui.class.getResource("Shiphit.png"));
    ImageIcon bomb = new ImageIcon(Gui.class.getResource("Bomb.png"));


    public Game(Gui gui) {
        this.gui = gui;

        //Let OP! Puur om te testen!
        int[] test8 = {4,1};
        ArrayList<int[]> testArrayLocation3 = new ArrayList<>();
        testArrayLocation3.add(test8);
        shipOP = new Ship(orientation, true,1, testArrayLocation3);

        int[] test9 = {6,1};
        int[] test10 = {6,2};
        ArrayList<int[]> testArrayLocation4 = new ArrayList<>();
        testArrayLocation4.add(test9);
        testArrayLocation4.add(test10);
        ship2OP = new Ship(orientation, true,2, testArrayLocation4);

        int[] test1 = {2,1};
        int[] test2 = {2,2};
        int[] test3 = {2,3};
        ArrayList<int[]> testArrayLocation = new ArrayList<>();
        testArrayLocation.add(test1);
        testArrayLocation.add(test2);
        testArrayLocation.add(test3);
        ship3OP = new Ship(orientation, true,3, testArrayLocation);


        int[] test4 = {2,5};
        int[] test5 = {2,6};
        int[] test6 = {2,7};
        int[] test7 = {2,8};
        ArrayList<int[]> testArrayLocation2 = new ArrayList<>();
        testArrayLocation2.add(test4);
        testArrayLocation2.add(test5);
        testArrayLocation2.add(test6);
        testArrayLocation2.add(test7);
        ship4OP = new Ship(orientation, true,4, testArrayLocation2);


        //hier begint het echte werk pas!
        for(int i=0; i<gui.buttons.length; i++) {
            for(int j=0; j<gui.buttons[i].length; j++) {
                int[] integerArrayToButton = {i,j};
                HandlerClass handler = new HandlerClass(integerArrayToButton, this);
                gui.buttons[i][j].addActionListener(handler);
            }
        }

        for(int i=0; i<gui.buttons2.length; i++) {
            for(int j=0; j<gui.buttons2[i].length; j++) {
                int[] integerArrayToButton = {i,j};
                HandlerClass2 handler = new HandlerClass2(integerArrayToButton, this, gui);
                gui.buttons2[i][j].addActionListener(handler);
            }
        }
    }

    private ArrayList<int[]> returnApprovedLocation(int orientation, int shipLength, int[] startLocation){
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
                    location.add(integerArray);
                    integerArray1[0] = startL + 1;
                    integerArray1[1] = startR;
                    location.add(integerArray1);
                    break;
                case 3:
                    location.add(integerArray);
                    integerArray1[0] = startL + 1;
                    integerArray1[1] = startR;
                    location.add(integerArray1);
                    integerArray2[0] = startL + 2;
                    integerArray2[1] = startR;
                    location.add(integerArray2);
                    break;
                case 4:
                    location.add(integerArray);
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
            gui.buttons[startL][startR].setEnabled(true);
            return location;
        }catch(ArrayIndexOutOfBoundsException ae){
            return null;
        }
    }

    private boolean checkExcludeList(ArrayList<int[]> locationToBeChecked) {
        for(int[] intCoordinateToBeChecked : locationToBeChecked){
            for(int[] intCoordinateInExcludeList : excludeList ){
                if(Arrays.equals(intCoordinateToBeChecked, intCoordinateInExcludeList)){
                    return true;
                }
            }
        }

        return false;
    }

    public void placeShip(int orientation, int[] startLocation){
        if(returnApprovedLocation(orientation, shipLength, startLocation) == null){
            JOptionPane.showMessageDialog(null, "The ship cannot be placed here");
        } else {

            ArrayList<int[]> approvedLocation = returnApprovedLocation(orientation, shipLength, startLocation);

            if(checkExcludeList(approvedLocation)){
                JOptionPane.showMessageDialog(null, "The ship cannot be placed here");
                return;
            }

            switch(shipLength){
                case 1:
                    ship1 = new Ship(orientation, true, shipLength, approvedLocation);
                    shipLength++;
                    break;
                case 2:
                    ship2 = new Ship(orientation, true, shipLength, approvedLocation);
                    shipLength++;
                    break;
                case 3:
                    ship3 = new Ship(orientation, true, shipLength, approvedLocation);
                    shipLength++;
                    break;
                case 4:
                    ship4 = new Ship(orientation, true, shipLength, approvedLocation);
                    gui.disableButtonsBoard();
                    gui.sendCoordinates.setEnabled(true);
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
                int[] excludePosition2 = {leftSide+=2, rightSide};
                leftSide-=1;
                int[] excludePosition3 = {leftSide,--rightSide};
                int[] excludePosition4 = {leftSide, rightSide+=2};
                rightSide-=1;
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
