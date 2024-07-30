package es.datastructures.robot.io;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import es.datastructures.robot.api.Elements;


public class Input {

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
           System.err.println("Errors occurred at parsing at file: " + e.getMessage());
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
            System.out.println("Provide number of rows: ");
            nRows = Elements.readInt(br.readLine());
            System.out.println("Provide number of columns: ");
            nColumns = Elements.readInt(br.readLine());
            System.out.println("Provide states matrix: ");
            states = new Elements.States[nRows][nColumns];
            for (int i = 0; i < nRows; i++) {
                Elements.States[] rowStates = Elements.readStates(br.readLine());
                for (int j = 0; j < nColumns; j++) {
                    states[i][j] = rowStates[j];
                }
            }
            System.out.println("Reading from stdin finished");
            br.close();
        }  catch (IOException e) {
            states = new Elements.States[][]{{Elements.States.GOAL}};
            System.err.println("Some errors occurred at entering values: " + e.getMessage());
        }
        return Elements.get(
                nRows,
                nColumns,
                states
        );
    }

}
