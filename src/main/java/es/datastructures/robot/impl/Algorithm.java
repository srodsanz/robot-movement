package es.datastructures.robot.impl;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.datastructures.robot.api.Elements;
import es.datastructures.robot.api.Results;


public class Algorithm {

    private static final Logger logger = LoggerFactory.getLogger(Algorithm.class);

    public static class Position {
        private final int width;
        private final int height;

        Position(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public String toString() {
            return String.format("(%s, %s)", this.width+1, this.height+1);
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

    }

    // Pass by reference
    private static void searchGoal(AlgorithmContext algorithmCtx,
                                   Position position,
                                   List<Position> solution,
                                   boolean hasTrace) {

        int positionWidth = position.getWidth();
        int positionHeight = position.getHeight();

        algorithmCtx.invertExplored(positionWidth, positionHeight);

        if (hasTrace) {
            System.out.println("Searching neighbors in position " + position);
        }

        if (algorithmCtx.getPosition(positionWidth, positionHeight) == Elements.States.GOAL) {
            solution.add(position);
            algorithmCtx.accomplishSuccessfulSearch();
        } else {
            List<Position> candidateNeighbors = getNeighbors(algorithmCtx, position);
            while (!algorithmCtx.isSuccessfulSearch() && !candidateNeighbors.isEmpty()) {
                Position positionCandidate = candidateNeighbors.remove(0);
                int positionWidthCandidate = positionCandidate.getWidth();
                int positionHeightCandidate = positionCandidate.getHeight();
                if (!algorithmCtx.isExplored(positionWidthCandidate, positionHeightCandidate)) {
                    searchGoal(algorithmCtx, positionCandidate, solution, hasTrace);
                }
            }
            if (algorithmCtx.isSuccessfulSearch()) {
                solution.add(position);
            }
        }
    }

    private static List<Position> getNeighbors(AlgorithmContext algorithmCtx,
                              Position position) {

        List<Position> candidateNeighbors = new ArrayList<>();
        Position positionAux;

        int positionWidth = position.getWidth();
        int positionHeight = position.getHeight();
        int widthBound = algorithmCtx.getWidthShape();
        int heightBound = algorithmCtx.getHeightShape();


        if (positionWidth < widthBound - 1) {
            if (algorithmCtx.getPosition(positionWidth+1, positionHeight) != Elements.States.NARROW) {
                positionAux = new Position(positionWidth+1, positionHeight);
                candidateNeighbors.add(positionAux);
            }
        }
        if (positionWidth > 0) {
            if (algorithmCtx.getPosition(positionWidth-1, positionHeight) != Elements.States.NARROW) {
                positionAux = new Position(positionWidth-1, positionHeight);
                candidateNeighbors.add(positionAux);
            }
        }
        if (positionHeight < heightBound - 1) {
            if (algorithmCtx.getPosition(positionWidth, positionHeight+1) != Elements.States.NARROW) {
                positionAux = new Position(positionWidth, positionHeight+1);
                candidateNeighbors.add(positionAux);
            }
        }
        if (positionHeight > 0) {
            if (algorithmCtx.getPosition(positionWidth, positionHeight-1) != Elements.States.NARROW) {
                positionAux = new Position(positionWidth, positionHeight-1);
                candidateNeighbors.add(positionAux);
            }
        }
        return candidateNeighbors;
    }

    public static Results run(Elements input, boolean hasTrace) {

        Elements.States[][] buildingMatrix = input.getStatesMatrix();
        int widthBound = input.getNumRows(); int heightBound = input.getNumColumns();

        AlgorithmContext algorithmContext = new AlgorithmContext(buildingMatrix,
                widthBound, heightBound);

        List<Position> solution = new ArrayList<>();
        Position position = new Position(0, 0);

        searchGoal(algorithmContext, position, solution, hasTrace);

        return Results.get(solution, algorithmContext.isSuccessfulSearch());
    }


}
