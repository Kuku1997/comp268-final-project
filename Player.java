import java.util.ArrayList;

public class Player extends Characters{

    private int score;
    private final Inventory inventory;

    public Player(String name, String description, Locations characterLocation) {
        super(name, description, characterLocation);
        this.score = 0;
        this.inventory = new Inventory();
        this.inventory.add("small key");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean itemInInventory(String item){
        return this.inventory.containsItem(item);
    }


    public void grow() {
    }

    public void shrink() {
    }
}
