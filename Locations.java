import java.util.ArrayList;

public class Locations {

    private final String name;
    private final String description;
    private final Exits north, east, south, west;
    private int northernLocation, easternLocation, southernLocation, westernLocation;
    private final ArrayList<String> locationInventory = new ArrayList<>();
    private boolean lightsOn;

    public Locations(String name, String description, Exits north, Exits east,
                     Exits south, Exits west) {
        this.name = name;
        this.description = description;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        initializeDirectionLocations();
    }

    private void initializeDirectionLocations(){
        this.northernLocation = north.getExitLeadsTo();
        this.easternLocation = east.getExitLeadsTo();
        this.southernLocation = south.getExitLeadsTo();
        this.westernLocation = west.getExitLeadsTo();
    }

    // GETTER METHODS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getLocationInventory() {
        return locationInventory;
    }

    public boolean getLights() {
        return  this.lightsOn;
    }

    public Exits getNorth() {
        return north;
    }

    public Exits getEast() {
        return east;
    }

    public Exits getSouth() {
        return south;
    }

    public Exits getWest() {
        return west;
    }

    public int getNorthernLocation() {
        return northernLocation;
    }

    public int getEasternLocation() {
        return easternLocation;
    }

    public int getSouthernLocation() {
        return southernLocation;
    }

    public int getWesternLocation() {
        return westernLocation;
    }

    // METHODS

    public Boolean itemInInventory(String item) {
        return this.locationInventory.contains(item);
    }

    public void lightsOn() {
        this.lightsOn = true;
    }

}
