package es.uned.lsi.datastructures.robotmovement.impl;


import es.uned.lsi.datastructures.robotmovement.api.Elements;
import es.uned.lsi.datastructures.robotmovement.api.Results;
import es.uned.lsi.datastructures.robotmovement.io.Output;

public class Algorithm {


    public static Results run(Elements problem, boolean hasTrace) {
        Integer[][] minCoinNumbers = computeNumMinCoins(problem);
        Integer[] choiceCoins = computeCoins(problem, minCoinNumbers);
        Results results = Results.get(
                choiceCoins.length,
                choiceCoins
        );
        if (hasTrace) {
            Output.writeToStdOut(results);
        }
       return results;
    }

}
