import java.util.ArrayList;

public class Items {
    // ArrayList containing all items available in the game.
    private ArrayList<String> items;

    // Reads list of all available items from items.txt and adds them to this.items.
    public Items(String file) {
        this.items = TextAdventure.scanFiles(file);
    }

    // Used to distribute items from this.items to their respective location/user
    // inventories after the items are read from items.txt.
    public void moveToInventory(int start, int end, Inventory inventory) {
        for (int i = start; i <= end; i++) {
            inventory.add(this.items.get(i));
        }
    }
}