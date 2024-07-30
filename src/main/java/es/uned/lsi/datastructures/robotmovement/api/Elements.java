package es.uned.lsi.datastructures.robotmovement.api;

import java.util.Optional;

public final class Elements {

    public enum State {
        L, T, E
    }

    private static Elements instance;
    private final int rowsGrid;
    private final int columnsGrid;
    private final State[][] gridLabels;

    private Elements(int rowsGrid, State[][] gridLabels,
                     int columnsGrid) {
        this.rowsGrid = rowsGrid;
        this.gridLabels = gridLabels;
        this.columnsGrid = columnsGrid;
    }

    public static Elements get(int rowsGrid, State[][] gridLabels,
                         int columnsGrid) {

        if (instance == null) {
            instance = new Elements(rowsGrid, gridLabels, columnsGrid);
        }
        return instance;
    }

    public static Optional<Integer> readInt(String line) {
        Optional<Integer> parsedInt;
        try {
            parsedInt = Optional.of(
                    Integer.parseInt(line)
            );
        } catch (NumberFormatException nfe) {
            parsedInt = Optional.empty();
        }
        return parsedInt;
    }

    public static State parseState(String s) {
        switch (s) {
            case "L":
                return State.L;
            case "E":
                return State.E;
            case "T":
                return State.T;
            default:
                throw new RuntimeException("String " + s + " not parsed to any state");
        }
    }

    public static Optional<State[][]> readStates(String[] lines) {
        int numLines = lines.length;
        int statesLine = lines[0].split("\\s").length;
        State[][] gridStates = new State[numLines][statesLine];
        Optional<State[][]> optStates;
        try {
            for (int i = 0; i < numLines; i++) {
                String[] charStates = lines[i].split("\\s");
                for (int j = 0; j < statesLine; j++) {
                    gridStates[i][j] = parseState(charStates[j]);
                }
            }
            optStates = Optional.of(gridStates);
        } catch (RuntimeException re) {
            optStates = Optional.empty();
        }
        return optStates;
    }


    public int getRowsGrid() {
        return this.rowsGrid;
    }

    public Integer[] getGridLabels() {
        return this.gridLabels;
    }

    public int getColumnsGrid() {
        return this.columnsGrid;
    }

}



