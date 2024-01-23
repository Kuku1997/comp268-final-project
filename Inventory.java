import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public int getSize(){
        return this.inventory.size();
    }

    public boolean isEmpty() {
        return this.inventory.isEmpty();
    }

    public boolean containsItem(String item) {
        return this.inventory.contains(item);
    }

    public void add(String item) {
        this.inventory.add(item);
    }

    public void remove(String item) {
        this.inventory.remove(item);
    }

    public void listInventory() {
        for (String item : this.inventory) {
            System.out.println("    -" + item);
        }
    }
}