package by.epam.computergames.entity;

public enum Sex {
    MALE("male"),
    FEMALE("female"),
    THIRD("third");

    private String value;

    Sex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}