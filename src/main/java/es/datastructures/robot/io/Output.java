package es.datastructures.robot.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import es.datastructures.robot.api.Results;
import es.datastructures.robot.impl.Algorithm;

public class Output {

    private Output() {
        // Used to avoid particular instantiation of this class
    }

    public static void writeToStdOut(Results results) {
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );
        try {
            System.out.println("Number of coins which are solution for the problem and values: ");
            bw.write(
                    String.join(System.lineSeparator(),
                            results.getPositions().stream().map(Algorithm.Position::toString)
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
        if (!file.canWrite()) {
            throw new IOException("Unable to load results as not having permissions in " + file.getAbsolutePath());
        }
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file)
            );
            bw.write(
                    String.join(System.lineSeparator(),
                            results.getPositions().stream().map(Algorithm.Position::toString)
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
