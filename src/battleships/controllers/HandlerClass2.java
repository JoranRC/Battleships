package battleships.controllers;

import battleships.models.Ship;
import battleships.views.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public boolean shipHit(Ship newShip, int leftSide, int rightSide){
        for(int[] intCoordinateToBeChecked : newShip.getLocation()) {
            if (Arrays.equals(intCoordinateToBeChecked, arguments)) {
                newShip.getLocation().remove(intCoordinateToBeChecked);
                gui.buttons2[leftSide][rightSide].setIcon(Gui.ship);
                gui.buttons2[leftSide][rightSide].setDisabledIcon(Gui.ship);
                System.out.println(newShip.getLocation().size());
                if (newShip.getLocation().size() == 0) {
                    newShip.setAfloat(false);
                    gui.battleShip.setText("BATTLESHIP \n" + "Status: " + gui.afloatStatusFlase);
                }
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent event) {
        int leftSide = arguments[0];
        int rightSide = arguments[1];
        if(shipHit(gameController.ship3OP, leftSide, rightSide)){
            return;
        }
        gui.buttons2[leftSide][rightSide].setIcon(Gui.bomb);
        gui.buttons2[leftSide][rightSide].setDisabledIcon(Gui.bomb);

    }
}
