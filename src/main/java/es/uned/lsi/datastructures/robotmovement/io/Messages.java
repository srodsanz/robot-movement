package es.uned.lsi.datastructures.robotmovement.io;

public class Messages {

    private Messages() {}

    public enum Formatters {
        HEADER, INPUT_FILE, OUTPUT_FILE, TRACE_MESSAGE, HELP_MESSAGE, SYNTAX, NO_SOLUTION
    }

    public static String getMessage(Formatters formatter) {
        switch (formatter) {
            case HEADER:
                return "An application implementing robot movement algorithm";
            case SYNTAX:
                return "robot-movement [-t][-h] <FICHERO_ENTRADA> <FICHERO_SALIDA>";
            case INPUT_FILE:
                return "Input path to file containing problem parameters";
            case OUTPUT_FILE:
                return "Output path to store results in raw text";
            case TRACE_MESSAGE:
                return "Show the trace of the dynamic change algorithm";
            case HELP_MESSAGE:
                return "Show command syntax as help command";
            case NO_SOLUTION:
                return "No solution available";
            default:
                return "";
        }
    }

    private static String helpMessage() {
        String msg =  getMessage(Formatters.HEADER) + " \n"
        + getMessage(Formatters.SYNTAX) + " \n"
        + "<FICHERO_ENTRADA> " + getMessage(Formatters.INPUT_FILE) + " \n"
        + "<FICHERO_SALIDA> " + getMessage(Formatters.OUTPUT_FILE) + " \n"
        + "-h: " + getMessage(Formatters.HELP_MESSAGE) + " \n"
        + "-t: " + getMessage(Formatters.TRACE_MESSAGE) + " \n";

        return msg;
    }

    public static String help() {
        return helpMessage();
    }

}