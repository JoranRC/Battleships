package battleships;

import battleships.controllers.Game;
import battleships.views.Gui;

public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui();
        Game game = new Game(gui);
    }
}
