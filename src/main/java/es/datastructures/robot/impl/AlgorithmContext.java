package es.datastructures.robot.impl;

import es.datastructures.robot.api.Elements;

import java.util.Arrays;

class AlgorithmContext {

    private final Boolean[][] exploredMatrix;
    private final Elements.States[][] buildingMatrix;

    private boolean successfulSearch;

    AlgorithmContext(Elements.States[][] buildingMatrix,
                     int widthBound, int heightBound) {

        assert buildingMatrix.length == widthBound;
        assert buildingMatrix[0].length == heightBound;

        Boolean[][] exploredMatrix = new Boolean[widthBound][heightBound];

        for (Boolean[] matrix : exploredMatrix) {
            Arrays.fill(matrix, false);
        }

        this.exploredMatrix = exploredMatrix;
        this.buildingMatrix = buildingMatrix;
        this.successfulSearch = false;
    }

    void invertExplored(int width, int height) {
        this.exploredMatrix[width][height] = !this.exploredMatrix[width][height];
    }

    boolean isExplored(int width, int height) {
        return this.exploredMatrix[width][height];
    }

    int getWidthShape() {
        return this.buildingMatrix.length;
    }

    int getHeightShape() {
        return this.buildingMatrix[0].length;
    }

    Elements.States getPosition(int width, int height) {
        return buildingMatrix[width][height];
    }

    void accomplishSuccessfulSearch() {
        this.successfulSearch = true;
    }

    boolean isSuccessfulSearch() {
        return this.successfulSearch;
    }


}

