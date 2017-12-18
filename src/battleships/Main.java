package battleships;

import battleships.controllers.Connection;
import battleships.controllers.Game;
import battleships.views.Gui;

public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();
        //had trouble with the closeConnection button so it's disabled for now
        gui.closeconnection.setEnabled(false);
        gui.disableButtonsBoard();
        gui.disableButtonsBoard2();
        gui.sendCoordinates.setEnabled(false);
        Game game = new Game(gui);
        Connection connection = new Connection(gui,game);
    }
}
