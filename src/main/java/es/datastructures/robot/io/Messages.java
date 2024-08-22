package es.datastructures.robot.io;

public class Messages {

    private Messages() {
        // Avoid instantiation of the input class
    }

    public enum Formatters {
        HEADER, INPUT_FILE, OUTPUT_FILE, TRACE_MESSAGE, HELP_MESSAGE, SYNTAX, NO_SOLUTION
    }

    public static String getMessage(Formatters formatter) {
        return switch (formatter) {
            case HEADER -> "An application implementing robot movement by backtracking";
            case SYNTAX -> "robot [-t][-h] <INPUT_DATA> <OUTPUT_DATA>";
            case INPUT_FILE -> "Input path to file containing problem parameters";
            case OUTPUT_FILE -> "Output path to store results in raw text";
            case TRACE_MESSAGE -> "Trace the algorithm";
            case HELP_MESSAGE -> "Print this help";
            case NO_SOLUTION -> "There was no path found to the bolt";
        };
    }

    // Print help. The CLI parser does not provide a suitable -or straightforward way- to accomplish this

    private static String helpMessage() {

        return  getMessage(Formatters.HEADER) + " \n"
        + getMessage(Formatters.SYNTAX) + " \n"
        + "<FICHERO_ENTRADA> " + getMessage(Formatters.INPUT_FILE) + " \n"
        + "<FICHERO_SALIDA> " + getMessage(Formatters.OUTPUT_FILE) + " \n"
        + "-h: " + getMessage(Formatters.HELP_MESSAGE) + " \n"
        + "-t: " + getMessage(Formatters.TRACE_MESSAGE) + " \n";
    }

    public static String help() {
        return helpMessage();
    }

}
