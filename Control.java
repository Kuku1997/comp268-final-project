import javax.xml.stream.Location;
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
        map.add(new Locations("Meadow", description, -1, -1, 1, -1));
        map.add(new Locations("Bottom of tunnel", description, -1, 2, -1, -1));
        map.add(new Locations("Woods", description, 5, 3, 4, -1));
        map.add(new Locations("House", description, -1, -1, 4, 2));
        map.add(new Locations("Queen's Court", description, -1, 3, -1, -1));
        map.add(new Locations("Tea Party", description, -1, 2, -1, -1));
    }

    private void fillCharacterList() {

    }

    public void readInput() {
        Scanner in = new Scanner(System.in);
        String input = "", output;
        do {
            System.out.print("> ");
            input = in.nextLine();
            // run as long as user does not input the termination phrase "quit/QUIT/Quit"
            if (input.contains("quit") || input.contains("QUIT") ||
            input.contains("Quit")) {
                return;
            }
            output = runCommand(input);
            System.out.println(output);
        } while(true);
    }

    private String runCommand(String input) {
        String command = input.toLowerCase();
        String[] commands = command.split(" ");

        // Determine what command to run based on user input.
        if (commands[0].equals(actions.get(0))) { // command = "go"
            return this.movePlayer(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(1))) { // command = "take"
            return this.takeItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(2))) { // command = "drop"
            return this.dropItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(3)) ||
                commands[0].equals(actions.get(4))) { // command = "eat" or "drink"
            return this.useItem(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(5))) { // command = "talk"
            talkTo(commands[commands.length - 1]);
        } else if (commands[0].equals(actions.get(6))) { // command = "look"
            return this.look();
        }

        // If command is not recognized.
        return "Sorry, I don't understand \"" + commands[0] + "\"";

    }

    public String movePlayer(String direction) {
        int currentLocationDirectionValue; // value of N,E,S,W for current location.
        // Convert user input to valid direction value that program will
        // recognize.
        direction = direction.toLowerCase();
        switch (direction) {
            case "north", "n" -> {
                currentLocationDirectionValue =
                        user.getCharacterLocation().getNorth();
                direction = "north";
            }
            case "east", "e" -> {
                currentLocationDirectionValue =
                        user.getCharacterLocation().getEast();
                direction = "east";
            }
            case "south", "s" -> {
                currentLocationDirectionValue =
                        user.getCharacterLocation().getSouth();
                direction = "south";
            }
            case "west", "w" -> {
                currentLocationDirectionValue =
                        user.getCharacterLocation().getWest();
                direction = "west";
            }
            default -> {
                return direction + " is not a valid direction.";
            }
        };

        // If characterLocation direction value equal -1, there
        // is no exit in that direction.
        if (currentLocationDirectionValue == -1) {
            return "You go " + direction + ", but you hit a dead end.";
        } else {
            user.setCharacterLocation(map.get(currentLocationDirectionValue));
            return "You go " + direction + " and you are now in the " +
                    user.getCharacterLocation().getName() + ".";
        }

    }

    private void talkTo(String character) {
    }

    private String useItem(String item) {
        // Check to see if item is current in user's inventory
        if (!user.itemInInventory(item)) {
            return "Cannot use " + item + " because it is not in your inventory.";
        }
        // Call relevant function based on value of 'item' parameter.
        if (item.equalsIgnoreCase("potion")) {
            user.shrink();
            return "You drank the " + item + ". You feel strange and everything around you starts looking" +
                    "much larger";
        } else if (item.equalsIgnoreCase("cake")) {
            user.grow();
            return "You ate the " + item + ". You feel strange and everything around you starts looking" +
                    "much smaller";
        } else if (item.equalsIgnoreCase("lamp")) {
            user.getCharacterLocation().lightsOn();
            return "You can now see your surroundings.";
        } else {
            return "Sorry, you cannot use " + item + " now.";
        }
    }

    private String dropItem(String item) {
        Locations currentLoc = user.getCharacterLocation();
        // Check to see if item is currently held in user's inventory.
        if (user.itemInInventory(item)) {
            // If so, remove it from user inventory and add it to location inventory.
            currentLoc.getLocationInventory().add((item));
            user.getInventory().remove(item);
            return "You have dropped the " + item;
        } else {
            // Else, let user know that item is not currently in their inventory.
            return "You cannot drop the " + item + " because it is not in your inventory.";
        }
    }

    private String takeItem(String item) {
        Locations currentLoc = user.getCharacterLocation();
        // Check to see if item is held in user's current location's inventory
        if (currentLoc.itemInInventory(item)) {
            // If so, remove it from location inventory and add it to user inventory.
            user.getInventory().add(item);
            currentLoc.getLocationInventory().remove(item);
            return item + " has been added to your inventory";
        } else {
            // Else, let user know that item is not available in this location.
            return "Cannot add the " + item + " to your inventory. It is not present in this location.";
        }
    }

    private String look() {
        return user.getCharacterLocation().getDescription();
    }

    private void enterNewLocation(Locations newLocation) {
        System.out.println(newLocation.getDescription());
        System.out.println("You can see the following items: ");
        // Print each item in newLocation inventory
        for (String item : newLocation.getLocationInventory()){
            System.out.println("    -" + item);
        }
    }

}
