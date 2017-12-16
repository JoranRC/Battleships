package battleships.models;

import java.util.ArrayList;

public class Ship {

    private boolean orientation;
    private int shipLength;
    private ArrayList<Integer> location;

    public Ship(boolean orientation, int shipLength, ArrayList<Integer> location) {
        this.orientation = orientation;
        this.shipLength = shipLength;
        this.location = location;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public ArrayList<Integer> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Integer> location) {
        this.location = location;
    }
}
