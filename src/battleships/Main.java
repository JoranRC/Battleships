package battleships;

import battleships.controllers.Connection;
import battleships.controllers.Game;
import battleships.views.Gui;

public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.disableButtonsBoard();
        gui.disableButtonsBoard2();
        Game game = new Game(gui);
        Connection connection = new Connection(gui,game);
    }
}
