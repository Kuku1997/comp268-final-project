import java.util.ArrayList;

public class Actions {
    ArrayList<String> actionList;

    public Actions() {
        actionList = TextAdventure.scanFiles("actions.txt");
    }

    public String get(int element){
        return actionList.get(element);
    }


}
