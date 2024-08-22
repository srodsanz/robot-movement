package es.datastructures.robot;

import es.datastructures.robot.api.Elements;
import es.datastructures.robot.api.Results;
import es.datastructures.robot.impl.Position;
import es.datastructures.robot.impl.Algorithm;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static es.datastructures.robot.api.Elements.States.*;

public class AlgorithmTest {

    private int numRows;
    private int numColumns;
    private Elements.States[][] states;
    private boolean hasTrace;

    @Before
    public void beforeClass() {
        this.numRows = 5;
        this.hasTrace = false;
        this.numColumns = 6;
        this.states = new Elements.States[][]{
                {FREE, FREE, NARROW, FREE, FREE, NARROW},
                {FREE, NARROW, FREE, FREE, FREE, NARROW},
                {FREE, FREE, FREE, NARROW, FREE, FREE},
                {FREE, FREE, NARROW, FREE, FREE, FREE},
                {FREE, FREE, FREE, FREE, GOAL, FREE}
        };

    }

    @Test
    public void testFirst() {
        Elements input = Elements.get(
                numRows, numColumns, states
        );
        Results results = Algorithm.run(input, hasTrace);
        Position lastPosition = results.getPositions().get(0);
        Assert.assertTrue(results.hasSolution());
        Assert.assertEquals(lastPosition.getHeight(), 4);
        Assert.assertEquals(lastPosition.getWidth(), 4);
    }

}
