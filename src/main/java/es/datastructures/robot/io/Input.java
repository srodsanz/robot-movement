package es.datastructures.robot.io;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.datastructures.robot.api.Elements;
import es.datastructures.robot.impl.Algorithm;

public class Input {

    private static final Logger logger = LoggerFactory.getLogger(Algorithm.class);

    private Input() {
        //Used to avoid particular instantiation of this particular class
    }

    public static Elements readFromFile(File file) throws IOException {
        int nRows = 0;
        int nColumns = 0;
        Elements.States[][] states;
        if (!file.canRead() || !file.isFile()) {
           throw new IOException("Not able to read in path: " + file.getAbsolutePath());
       }
       try {
           BufferedReader br = new BufferedReader(
                   new FileReader(file)
           );
           nRows = Elements.readInt(br.readLine());
           nColumns = Elements.readInt(br.readLine());
           states = new Elements.States[nRows][nColumns];
           for (int i = 0; i < nRows; i++) {
               Elements.States[] rowStates = Elements.readStates(br.readLine());
               for (int j = 0; j < nColumns; j++) {
                   states[i][j] = rowStates[j];
               }
           }
           br.close();
       } catch (IOException e) {
           states = new Elements.States[][]{{Elements.States.GOAL}};
           logger.error("Errors occurred at parsing at file: " + e.getMessage());
       }
       return Elements.get(
                nRows,
                nColumns,
                states
        );
    }

    public static Elements readFromStdIn() {
        int nRows = 0;
        int nColumns = 0; Elements.States[][] states;

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        try {
            logger.info("Provide number of rows: ");
            nRows = Elements.readInt(br.readLine());
            logger.info("Provide number of columns: ");
            nColumns = Elements.readInt(br.readLine());
            logger.info("Provide states matrix: ");
            // Use provided data of rows and columns to allocate static array at compile-time
            states = new Elements.States[nRows][nColumns];
            for (int i = 0; i < nRows; i++) {
                Elements.States[] rowStates = Elements.readStates(br.readLine());
                for (int j = 0; j < nColumns; j++) {
                    states[i][j] = rowStates[j];
                }
            }
            logger.info("Reading from stdin finished");
            br.close();
        }  catch (IOException e) {
            states = new Elements.States[][]{{Elements.States.GOAL}};
            logger.error("Some errors occurred at entering values: " + e.getMessage());
        }
        return Elements.get(
                nRows,
                nColumns,
                states
        );
    }

}
