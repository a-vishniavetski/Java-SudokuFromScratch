package org.example;

public enum Difficulty {
    EASY(15),
    NORMAL(25),
    HARD(35);

    private final int value;

    public int getFieldsToErase() {
        return this.value;
    }

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Łatwy";
            case NORMAL -> "Normalny";
            case HARD -> "Trudny";
        };
    }

    Difficulty(int value) {
        this.value = value;
    }
}