package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
//import java.util.logging.ConsoleHandler;
//import java.util.logging.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner\n");

        Options options = new Options();
        options.addOption("i", true, "input file");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                String inputFile = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file" + inputFile + "\n");
                
                Maze maze = new Maze(inputFile);                
                Explorer explorer = new Explorer(maze);

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.info("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.info("PASS ");
                        }
                    }
                    logger.info(System.lineSeparator());
                }

                logger.info("**** Computing path\n");
                if (explorer.explore()) {
                    logger.info("PATH FOUND: {}", explorer.getPath());
                } else {
                    logger.info("PATH NOT FOUND\n");
                }
                logger.info("** End of MazeRunner\n");

                reader.close();
            } else {
                logger.error("Input file not provided. Use -i flag to specify the input file. \n");
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\ \n");
        }
    }
}
