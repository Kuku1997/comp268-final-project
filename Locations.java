import java.util.ArrayList;

public class Locations {

    private final String name;
    private final String description;
    private final Exits north, east, south, west;
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
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getLocationInventory() {
        return locationInventory;
    }

    public Boolean itemInInventory(String item) {
        return this.locationInventory.contains(item);
    }

    public void lightsOn() {
        this.lightsOn = true;
    }

    public boolean getLights() {
        return  this.lightsOn;
    }

    public int getNorth() {
        return north.getExitLeadsTo();
    }

    public int getEast() {
        return east.getExitLeadsTo();
    }

    public int getSouth() {
        return south.getExitLeadsTo();
    }

    public int getWest() {
        return west.getExitLeadsTo();
    }

}
