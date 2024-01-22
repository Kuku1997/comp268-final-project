import java.util.ArrayList;
import java.util.Scanner;

public class Control {

    public final ArrayList<Locations> map = new ArrayList<>();
    public ArrayList<Characters> characterList;
    public static Player user;
    private final Actions actions = new Actions();
    String description = "test description";

    // Used to start new game.
    public void start() {
        this.fillMap();
        user = new Player("Alice", "A little girl", map.get(0));
        this.readInput();
    }

    // Fills arrayList 'map' with Locations objects that will represent a map
    // of rooms in this game.
    private void fillMap() {
        map.add(new Locations("Meadow", description, new Exits(-1), new Exits(-1),
                new Exits(1), new Exits(-1)));
        map.add(new Locations("Bottom of tunnel", description, new Exits(-1),
                new Exits(true, true, 2), new Exits(-1), new Exits(-1)));
        //map.add(new Locations("Woods", description, 5, 3, 4, -1));
        map.add(new Locations("Woods", description, new Exits(5),
                new Exits(true, false, 3), new Exits(4), new Exits(-1)));
        //map.add(new Locations("House", description, -1, -1, 4, 2));
        map.add(new Locations("House", description, new Exits(-1), new Exits(-1),
                new Exits(4), new Exits(2)));
        //map.add(new Locations("Queen's Court", description, -1, 3, -1, -1));
        map.add(new Locations("Queen's Court", description, new Exits(-1), new Exits(3),
                new Exits(-1), new Exits(-1)));
        //map.add(new Locations("Tea Party", description, -1, 2, -1, -1));
        map.add(new Locations("Tea Party", description, new Exits(-1), new Exits(2),
                new Exits(-1), new Exits(-1)));
    }

    private void fillCharacterList() {

    }

    public void readInput() {
        Scanner in = new Scanner(System.in);
        String input;
        do {
            System.out.print("> ");
            input = in.nextLine();
            // run as long as user does not input the termination phrase "quit/QUIT/Quit"
            if (input.contains("quit") || input.contains("QUIT") || input.contains("Quit")) {
                return;
            }
            runCommand(input);
        } while (true);
    }

    private void runCommand(String input) {
        String command = input.toLowerCase();
        String[] commands = command.split(" ");

        // Determine what command to run based on user input.
        if (commands[0].equals(actions.get(0))) { // command = "go"
            this.movePlayer(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(1))) { // command = "take"
            this.takeItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(2))) { // command = "drop"
            this.dropItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(3)) || commands[0].equals(actions.get(4))) { // command = "eat" or "drink"
            this.useItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(5))) { // command = "talk"
            this.talkTo(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(6))) { // command = "look"
            this.look();
        } else { // If command is not recognized.
            System.out.println("Sorry, I don't understand \"" + commands[0] + "\"");
        }
    }

    public void movePlayer(String direction) {
        int currentLocationDirectionValue; // value of N,E,S,W for current location, that corresponds to Locations
        // object's index in arrayList<> map.

        // Converts user input to valid direction value that program will
        // recognize.
        direction = direction.toLowerCase();
        Locations currentLocation = user.getCharacterLocation();
        boolean locked, small;
        switch (direction) {
            case "north", "n" -> {
                currentLocationDirectionValue = currentLocation.getNorthernLocation();
                direction = "north";
                locked = currentLocation.getNorth().isLocked();
                small = currentLocation.getNorth().isSmall();
            }
            case "east", "e" -> {
                currentLocationDirectionValue = currentLocation.getEasternLocation();
                direction = "east";
                locked = currentLocation.getEast().isLocked();
                small = currentLocation.getEast().isSmall();
            }
            case "south", "s" -> {
                currentLocationDirectionValue = currentLocation.getSouthernLocation();
                direction = "south";
                locked = currentLocation.getSouth().isLocked();
                small = currentLocation.getSouth().isSmall();
            }
            case "west", "w" -> {
                currentLocationDirectionValue = currentLocation.getWesternLocation();
                direction = "west";
                locked = currentLocation.getWest().isLocked();
                small = currentLocation.getWest().isSmall();
            }
            default -> {
                System.out.println(direction + " is not a valid direction.");
                return; // Not a valid direction input. Return method and do not call updateLocation().
            }
        }

        // If user entered valid direction, and exit in that direction is not locked/small,
        // updateLocation() will change user's current location.
        if (locked) {
            System.out.println("You try to open the door, but it is locked.");
            return;
        } else if (small) {
            System.out.println("The door is tiny, barely big enough for a mouse. You'll never fit.");
            return;
        } else {
            updateLocation(direction, currentLocationDirectionValue);
        }
    }


    // If characterLocation direction value equal -1, there
    // is no exit in that direction.
    private void updateLocation(String direction, int currentLocationDirectionValue) {
        // If currentLocationDirectionValue == -1, that means that there is no exit in that direction.
        // Prints message letting use know they can't go that way.
        if (currentLocationDirectionValue == -1) {
            System.out.println("You go " + direction + ", but you hit a dead end.");
        } else { //Otherwise, update location to location stored in currentLocationDirectionValue.
            user.setCharacterLocation(map.get(currentLocationDirectionValue));
            System.out.println("You go " + direction + " and you are now in the "
                    + user.getCharacterLocation().getName() + ".");
            // Calls enterNewLocation which will print information about user's new location/
            enterNewLocation(user.getCharacterLocation());
        }
    }

    private void enterNewLocation(Locations newLocation) {
        System.out.println(newLocation.getDescription());
        // Print each item in newLocation inventory (if inventory is not-empty)
        if (!newLocation.getLocationInventory().isEmpty()) {
            System.out.println("You can see the following items: ");
            for (String item : newLocation.getLocationInventory()) {
                System.out.println("    -" + item);
            }
        }
    }

    private void useItem(String item) {
        // Check to see if item is current in user's inventory
        if (!user.itemInInventory(item)) {
            System.out.println("Cannot use " + item + " because it is not in your inventory.");
            return;
        }
        // Call relevant function based on value of 'item' parameter.
        if (item.equalsIgnoreCase("potion")) {
            user.shrink();
            System.out.println("You drank the " + item + ". You feel strange and everything around you starts looking"
                    + " much larger,");
        } else if (item.equalsIgnoreCase("cake")) {
            user.grow();
            System.out.println("You ate the " + item + ". You feel strange and everything around you starts looking"
                    + " much smaller.");
        } else if (item.equalsIgnoreCase("lamp")) {
            user.getCharacterLocation().lightsOn();
            System.out.println("You can now see your surroundings.");
    } else {
            System.out.println("Sorry, you cannot use " + item + " now.");
        }
    }

    private void dropItem(String item) {
        Locations currentLoc = user.getCharacterLocation();
        // Check to see if item is currently held in user's inventory.
        if (user.itemInInventory(item)) {
            // If so, remove it from user inventory and add it to location inventory.
            currentLoc.getLocationInventory().add((item));
            user.getInventory().remove(item);
            System.out.println("You have dropped the " + item);
        } else {
            // Else, let user know that item is not currently in their inventory.
            System.out.println("You cannot drop the " + item + " because it is not in your inventory.");
        }
    }

    private void takeItem(String item) {
        Locations currentLoc = user.getCharacterLocation();
        // Check to see if item is held in user's current location's inventory
        if (currentLoc.itemInInventory(item)) {
            // If so, remove it from location inventory and add it to user inventory.
            user.getInventory().add(item);
            currentLoc.getLocationInventory().remove(item);
            System.out.println(item + " has been added to your inventory");
        } else {
            // Else, let user know that item is not available in this location.
            System.out.println("Cannot add the " + item + " to your inventory. It is not present in this location.");
        }
    }

    private void talkTo(String character) {
    }

    private void look() {
        System.out.println(user.getCharacterLocation().getDescription());
    }

}
