package es.uned.lsi.datastructures.dynamicchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.uned.lsi.datastructures.dynamicchange.api.Elements;
import es.uned.lsi.datastructures.dynamicchange.impl.Algorithm;
import es.uned.lsi.datastructures.dynamicchange.api.Results;

public class AlgorithmTest {

    private int numberCoins;
    private Integer [] coinValues;
    private int retValue;

    @Before
    public void beforeClass() {
        this.numberCoins = 3;
        this.coinValues = new Integer[]{1, 6, 10};
        this.retValue = 12;
    }

    @Test
    public void testFirst() {
        Elements input = Elements.get(
                numberCoins, coinValues, retValue
        );
        Results results = Algorithm.run(input, false);
        Assert.assertEquals(results.getNumOutputCoins(), 2);
        Assert.assertEquals(results.getOutputCoinValues(), new Integer[]{6, 6});

    }

}
