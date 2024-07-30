package es.uned.lsi.datastructures.robotmovement.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Arrays;

import es.uned.lsi.datastructures.robotmovement.api.Results;

public class Output {

    private Output() {}

    public static void writeToStdOut(Results results) {
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );
        try {
            System.out.println("Number of coins which are solution for the problem and values: ");
            bw.write(String.valueOf(results.getNumOutputCoins()));
            bw.write(System.lineSeparator());
            bw.write(
                    String.join(" ",
                            Arrays.stream(results.getOutputCoinValues()).map(String::valueOf)
                                    .toArray(String[]::new)
                            )
            );
            bw.write(System.lineSeparator());
            bw.flush();
        } catch (IOException ioe) {
            System.err.println("Unable to write results in stdout due to: " + ioe.getMessage());
        }
    }

    public static void writeToFile(Results results, File file) throws IOException {
        if (file.exists()) {
            throw new IOException("Unable to load results as file already exists in" + file.getAbsolutePath());
        }
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file)
            );
            bw.write(String.valueOf(results.getNumOutputCoins()));
            bw.write(System.lineSeparator());
            bw.write(
                    String.join(" ", Arrays.stream(results.getOutputCoinValues())
                            .map(String::valueOf)
                            .toArray(String[]::new)
                    )
            );
            bw.write(System.lineSeparator());
            bw.close();

        } catch (IOException e) {
            System.err.println("Unable to write results due to: " + e.getMessage());
        }
    }



}
