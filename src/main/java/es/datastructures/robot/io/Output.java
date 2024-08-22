package es.datastructures.robot.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.datastructures.robot.api.Results;
import es.datastructures.robot.impl.Algorithm;
import es.datastructures.robot.impl.Position;


public class Output {

    private static final Logger logger = LoggerFactory.getLogger(Algorithm.class);

    private Output() {
        // Used to avoid particular instantiation of this class
    }

    public static void writeToStdOut(Results results) {
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );
        try {
            logger.info("Visited positions ordered from the end to the start: ");
            bw.write(
                    String.join(System.lineSeparator(),
                            results.getPositions().stream().map(Position::toString)
                                            .toArray(String[]::new)
                            )
            );
            bw.write(System.lineSeparator());
            bw.flush();
        } catch (IOException ioe) {
            logger.error("Unable to write results in stdout due to: " + ioe.getMessage());
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
                            results.getPositions().stream().map(Position::toString)
                                    .toArray(String[]::new)
                    )
            );
            bw.write(System.lineSeparator());
            bw.close();

        } catch (IOException e) {
            logger.error("Unable to write results due to: " + e.getMessage());
        }
    }



}
