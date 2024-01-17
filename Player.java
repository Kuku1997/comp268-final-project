import java.util.ArrayList;

public class Player extends Characters{

    private int score;
    private final ArrayList<String> inventory = new ArrayList<>();

    public Player(String name, String description, Locations characterLocation) {
        super(name, description, characterLocation);
        this.score = 0;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public boolean itemInInventory(String item){
        return this.inventory.contains(item);
    }


    public void grow() {
    }

    public void shrink() {
    }
}
