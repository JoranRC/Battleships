package battleships.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HandlerClass implements ActionListener {

    int[] arguments;
    Game gameController;

    public HandlerClass(int[] arguments, Game gameController) {
        this.arguments = arguments;
        this.gameController = gameController;
    }

    public void actionPerformed(ActionEvent event) {
        gameController.placeShip(Game.orientation, arguments);
    }


}
