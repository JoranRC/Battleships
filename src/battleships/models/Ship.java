package battleships.models;

import java.util.ArrayList;

public class Ship {

    private int orientation;
    private boolean afloat;
    private int shipLength;
    private ArrayList<Integer[]> location;

    public Ship(int orientation, boolean afloat, int shipLength, ArrayList<Integer[]> location) {
        this.orientation = orientation;
        this.afloat = afloat;
        this.shipLength = shipLength;
        this.location = location;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public boolean isAfloat() {
        return afloat;
    }

    public void setAfloat(boolean afloat) {
        this.afloat = afloat;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public ArrayList<Integer[]> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Integer[]> location) {
        this.location = location;
    }
}
