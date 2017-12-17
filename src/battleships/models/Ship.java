package battleships.models;

import java.util.ArrayList;

public class Ship {

    public int orientation;
    public boolean afloat;
    public int shipLength;
    public ArrayList<int[]> location;

    public Ship(int orientation, boolean afloat, int shipLength, ArrayList<int[]> location) {
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

    public ArrayList<int[]> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<int[]> location) {
        this.location = location;
    }
}
