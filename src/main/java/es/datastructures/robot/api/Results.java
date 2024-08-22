package es.datastructures.robot.api;

import java.util.List;

import es.datastructures.robot.impl.Position;

public class Results {

    private final List<Position> positions;
    private final Boolean successfulSearch;
    private static Results results;


    private Results(List<Position> positions, boolean successfulSearch) {
        this.positions = positions;
        this.successfulSearch = successfulSearch;
    }

    public static Results get(List<Position> positions, boolean successfulSearch) {

        if (results == null) {
            results = new Results(positions, successfulSearch);
        }
        return results;
    }

    public boolean hasSolution() {
        return this.successfulSearch;
    }

    public List<Position> getPositions() {
        return this.positions;
    }


}
