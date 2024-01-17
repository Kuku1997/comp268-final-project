public class Characters {
    private final String name;
    private final String description;
    private Locations characterLocation;

    public Characters(String name, String description, Locations characterLocation) {
        this.name = name;
        this.description = description;
        this.characterLocation = characterLocation;
    }

    public void talk(){}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Locations getCharacterLocation() {
        return characterLocation;
    }

    public void setCharacterLocation(Locations characterLocation) {
        this.characterLocation = characterLocation;
    }
}
