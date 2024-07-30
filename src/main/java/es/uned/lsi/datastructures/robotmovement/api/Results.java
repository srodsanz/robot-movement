package es.uned.lsi.datastructures.robotmovement.api;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private static Results results;
    private Pair<Integer, Integer>[] positions;

    Results (Pair<Integer, Integer>[] positions) {
        this.positions = positions;
    }

    Results(Integer[] positionRows, Integer[] positionColumns) {
        assert positionRows.length == positionColumns.length;
        List<Pair<Integer, Integer>> positions = new ArrayList<>();
        for (int i = 0; i < positionRows.length; i++) {
            positions.add(
                    new Pair<Integer, Integer>(positionRows[i], positionColumns[i])
            );
        }
        this.positions = positions.toArray(new Pair[positionRows.length]);
    }


    public static Results get(Integer[] positionRows, Integer[] positionColumns) {

        if (results == null) {
            results = new Results(positionRows, positionColumns);
        }
        return results;
    }

    public static Results get(Pair<Integer, Integer>[] positions) {

        if (results == null) {
            results = new Results(positions);
        }
        return results;
    }


    public static boolean hasSolution(Results results) {
        return results.positions.length > 0;
    }
}
