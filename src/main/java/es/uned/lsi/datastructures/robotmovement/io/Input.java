package es.uned.lsi.datastructures.robotmovement.io;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Optional;

import es.uned.lsi.datastructures.robotmovement.api.Elements;
import es.uned.lsi.datastructures.robotmovement.api.Elements.State;

public class Input {

    private Input() {}

    public static Elements readFromFile(File file) throws IOException {
        Optional<Integer> rowsGrid; Optional<Integer> columnsGrid;
        Optional<State[][]> gridStates; String[] gridLines;
        if (!file.canRead() || !file.isFile()) {
            throw new IOException("Not able to read in path: " + file.getAbsolutePath());
        }
       try {
           BufferedReader br = new BufferedReader(
                   new FileReader(file)
           );
           rowsGrid = Elements.readInt(br.readLine());
           columnsGrid = Elements.readInt(br.readLine());
           gridLines = br.lines().toArray(String[]::new);
           gridStates = Elements.readStates(gridLines);
           br.close();
       } catch (IOException e) {
           rowsGrid = Optional.empty();
           columnsGrid = Optional.empty(); gridStates = Optional.empty();
           System.err.println("Errors occurred at parsing at file: " + e.getMessage());
       }
        return Elements.get(
                rowsGrid.orElse(0),
                gridStates.get(),
                columnsGrid.orElse(0)
        );
    }

    public static Elements readFromStdIn() {
        Optional<Integer> nCoins; Optional<Integer> retValue; Optional<Integer[]> coinValues;
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        try {
            System.out.println("Provide number of coins: ");
            nCoins = Elements.readInt(br.readLine());
            System.out.println("Provide values of available coins");
            coinValues = Elements.readAvailableCoins(br.readLine());
            System.out.println("Provide returning value: ");
            retValue = Elements.readInt(br.readLine());
            System.out.println("Reading from stdin finished");
            br.close();
        }  catch (IOException e) {
            nCoins = Optional.empty();
            retValue = Optional.empty(); coinValues = Optional.empty();
            System.err.println("Some errors occurred at entering values: " + e.getMessage());
        }
        return Elements.get(
                nCoins.orElse(0),
                coinValues.orElse(new Integer[]{0}),
                retValue.orElse(0)
        );
    }

}
