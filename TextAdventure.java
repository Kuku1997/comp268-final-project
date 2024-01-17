import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TextAdventure {
    public static void main(String[] args) {
        System.out.println("You find yourself blah blah blah.");

        // Creates new instance of Control class and calls control.start()
        Control control = new Control();
        control.start();
        // Message to be displayed once player finishes or quits game.
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("        Thank you for playing!           ");
        System.out.println("-----------------------------------------");
    }

    static ArrayList<String> scanFiles(String fileToRead) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileToRead))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
}
