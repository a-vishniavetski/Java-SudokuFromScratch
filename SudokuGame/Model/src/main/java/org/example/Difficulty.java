package org.example;

public enum Difficulty {
    EASY,
    NORMAL,
    HARD;

    public int getFieldsToErase() {
        return switch (this) {
            case EASY -> 5;
            case NORMAL -> 10;
            case HARD -> 15;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Łatwy";
            case NORMAL -> "Normalny";
            case HARD -> "Trudny";
        };
    }
}