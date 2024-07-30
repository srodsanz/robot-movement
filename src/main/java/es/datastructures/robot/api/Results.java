package es.datastructures.robot.api;

import java.util.List;

import es.datastructures.robot.impl.Algorithm;

public class Results {

    private final List<Algorithm.Position> positions;
    private final Boolean successfulSearch;

    private static Results results;


    private Results(List<Algorithm.Position> positions, boolean successfulSearch) {
        this.positions = positions;
        this.successfulSearch = successfulSearch;
    }

    public static Results get(List<Algorithm.Position> positions, boolean successfulSearch) {

        if (results == null) {
            results = new Results(positions, successfulSearch);
        }
        return results;
    }

    public boolean hasSolution() {
        return this.successfulSearch;
    }

    public List<Algorithm.Position> getPositions() {
        return this.positions;
    }


}
