package es.datastructures.robot;

/*
 * Main entrypoint for robot movement app
 *
 */
import org.apache.commons.cli.*;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.datastructures.robot.api.Elements;
import es.datastructures.robot.api.Results;
import es.datastructures.robot.impl.Algorithm;
import es.datastructures.robot.io.Input;
import es.datastructures.robot.io.Messages;
import es.datastructures.robot.io.Output;

public class Launcher 
{
    public static void main(String[] args) {

        Options opts = new Options();
        final Elements problem;
        final Results results;
        final long t0;
        final long t1;
        Logger logger = LoggerFactory.getLogger(Launcher.class);

        Option trace = new Option("t", "trace", 
            false, 
            Messages.getMessage(Messages.Formatters.TRACE_MESSAGE)
        );

        trace.setRequired(false);
        opts.addOption(trace);

        Option help = new Option("h", "help", 
            false,
            Messages.getMessage(Messages.Formatters.HELP_MESSAGE)
        );
        help.setRequired(false);
        opts.addOption(help);

        CommandLineParser cmdP = new DefaultParser();
        t0 = System.currentTimeMillis();
        // Parse command-line arguments: options and positional arguments

        logger.info("Begin processing of input data to run backtrack algorithm");

        try {
            CommandLine cmd = cmdP.parse(opts, args);
            boolean hasTrace = cmd.hasOption(trace);
            if (cmd.hasOption(help)) {
                String helpMsg = Messages.help();
                System.out.println(helpMsg);
                System.exit(0);
            }
            String[] fileArgs = cmd.getArgs();
            if (fileArgs.length == 0) { // No positional arguments provided
                System.out.println("No positional arguments. Reading from stdin ...");
                problem = Input.readFromStdIn();
                results = Algorithm.run(problem, hasTrace);
                if (results.hasSolution()) {
                    System.out.println("Writing results in stdout: ");
                    Output.writeToStdOut(results);
                } else {
                    System.out.println(Messages.getMessage(Messages.Formatters.NO_SOLUTION));
                }
            } else if (fileArgs.length == 1) { // One positional argument, understood as input file
                System.out.println("Received input file argument");
                File inputFile = new File(fileArgs[0]);
                assert inputFile.isFile() && inputFile.exists();
                problem = Input.readFromFile(inputFile);
                results = Algorithm.run(problem, hasTrace);
                if (results.hasSolution()) {
                    System.out.println("Writing results in stdout: ");
                    Output.writeToStdOut(results);
                } else {
                    System.out.println(Messages.getMessage(Messages.Formatters.NO_SOLUTION));
                }
            } else { // Two arguments provided
                assert fileArgs.length == 2;
                System.out.println("Received input and output file parameters");
                File inputFile = new File(fileArgs[0]);
                File outputFile = new File(fileArgs[1]);
                problem = Input.readFromFile(inputFile);
                results = Algorithm.run(problem, hasTrace);
                if (results.hasSolution()) {
                    System.out.println("Writing results to " + outputFile.getAbsolutePath());
                    Output.writeToFile(results, outputFile);
                } else {
                    System.out.println(Messages.getMessage(Messages.Formatters.NO_SOLUTION));
                }
            }
            t1 = System.currentTimeMillis();
            System.out.println("Finished execution with time = " + ((t1 - t0)) + " miliseconds");

        } catch (Exception e) {
            System.err.println("Some error occurred at runtime: ");
            logger.error("Registered exception: ", e);
        }

    }

}
