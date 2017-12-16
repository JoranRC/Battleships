package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.ArrayList;

public class Game {

    Gui gui;
    Ship ship;
    Ship ship2;
    Ship ship3;
    Ship ship4;


    public Game(Gui gui) {
        this.gui = gui;
    }

    public ArrayList<Integer[]> returnApprovedLocation(int orientation, int shipLength, ArrayList<Integer[]> location){
        Integer[] integerArray = location.get(0);
        Integer[] integerArray1 = new Integer[2];
        Integer[] integerArray2 = new Integer[2];
        Integer[] integerArray3 = new Integer[2];

        int startL = integerArray[0];
        int startR = integerArray[1];

        if(orientation == 0){
            switch(shipLength){
                case 1:
                    break;
                case 2:
                    integerArray1[0] = startL;
                    integerArray1[1] = startR + 1;
                    location.add(integerArray1);
                    break;
                case 3:
                    integerArray1[0] = startL;
                    integerArray1[1] = startR + 1;
                    location.add(integerArray1);
                    integerArray2[0] = startL;
                    integerArray2[1] = startR + 2;
                    location.add(integerArray2);
                    break;
                case 4:
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
        Integer[] returnIntegerArray = location.get(arrayListLength - 1);
        startL = returnIntegerArray[0];
        startR = returnIntegerArray[1];

        try{
            gui.buttons[startL][startR].setEnabled(false);
            return location;
        }catch(ArrayIndexOutOfBoundsException ae){
            return null;
        }
    }

    public void placeShip(int orientation, int shipLength, ArrayList<Integer[]> startLocation){
        
        if(ship == null){
            ship = new Ship(orientation, true, shipLength, startLocation);
            return;
        } else if(ship2 == null){
            ship2 = new Ship(orientation, true, shipLength, startLocation);
            return;
        } else if(ship3 == null){
            ship3 = new Ship(orientation, true, shipLength, startLocation);
            return;
        } else if(ship4 == null){
            ship4 = new Ship(orientation, true, shipLength, startLocation);
            return;
        } else {
            return;
        }
    }


}
