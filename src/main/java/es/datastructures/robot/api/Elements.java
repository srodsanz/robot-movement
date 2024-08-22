package es.datastructures.robot.api;

import es.datastructures.robot.impl.Position;

import java.util.Arrays;


public final class Elements {

    private static Elements instance;
    private final int numRows;
    private final int numColumns;
    private final States[][] states;

    public enum States {
        FREE, NARROW, GOAL;


        public String toString() {
            return switch (this) {
                case FREE -> "L";
                case NARROW -> "E";
                case GOAL -> "T";
            };
        }

        public static States parse(String state) throws StateParseException {
            return switch (state.charAt(0)) {
                case 'L' -> FREE;
                case 'E' -> NARROW;
                case 'T' -> GOAL;
                default -> throw new StateParseException("Not possible to parse input states");
            };
        }
    }

    public static class StateParseException extends RuntimeException {
        public StateParseException(String errorMessage) {
            super(errorMessage);
        }
    }

    private Elements(int numRows, int numColumns, States[][] states) {
        this.states = states;
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    public static Elements get(int numRows, int numColumns, States[][] states) {

        if (instance == null) {
            instance = new Elements(numRows, numColumns, states);
        }
        return instance;
    }

    public static Elements get() {
        return instance;
    }

    public static int readInt(String line) {
        int parsedInt;
        try {
            parsedInt = Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
            parsedInt = 0;
        }
        return parsedInt;
    }

    public static States[] readStates(String line) {
        String[] rawStates = line.split("\\s");
        return Arrays.stream(rawStates).map(Elements.States::parse)
                .toArray(States[]::new);
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public States[][] getStatesMatrix() {return this.states;}


}




