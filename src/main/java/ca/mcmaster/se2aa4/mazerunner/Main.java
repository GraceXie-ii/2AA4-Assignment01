package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner\n");

        Options options = new Options();
        options.addOption("i", true, "input file"); //Sets up a -i flad to be used later
        options.addOption("p", true, "check if path is correct"); //Sets up a -p flag to be used later

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) { //Checks if the -i flag is used
                String inputFile = cmd.getOptionValue("i"); //Gets the value of the -i flag
                logger.info("**** Reading the maze from file" + inputFile + "\n");
                
                Maze maze = new Maze(inputFile);//Construcs a new maze object and then an explorer object             
                Explorer explorer = new RightHandExplorer(maze);

                BufferedReader reader = new BufferedReader(new FileReader(inputFile)); //Reads the maze from the input file
                String line;
                while ((line = reader.readLine()) != null) { //Prints the maze
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.info("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.info("PASS ");
                        }
                    }
                    logger.info(System.lineSeparator());
                }

                if(cmd.hasOption("p")){ //Checks if the -p flag is used
                    String userInput = cmd.getOptionValue("p");//Gets the value of the -p flag

                    if(explorer.explore()){ //Checks if the path is correct
                        String formattedPath = ((RightHandExplorer) explorer).getFactorizedPath(); //Gets the formatted path
                        String canonPath = ((RightHandExplorer) explorer).getCanonicalPath(); //Gets the canonical path
                        if(userInput.equals(formattedPath) || userInput.equals(canonPath)){ //Allows the user to input the correct formatted path or canonical path
                            System.out.println("correct path");
                        }else{
                            System.out.println("incorrect path");
                        }
                    }else{
                        logger.info("PATH NOT FOUND");
                    }
                }else{ //If the -p flag is not used then the program will compute the path
                    logger.info("**** Computing path\n");
                    if (explorer.explore()) {
                        logger.info("PATH FOUND: {}", ((Path) explorer.getPath()).canonicalPath());
                        System.out.print(((Path) explorer.getPath()).formatPath()); //OUtput the formatted path
                    } else {
                        logger.info("PATH NOT FOUND");
                    }
                    logger.info("\n** End of MazeRunner\n");
                }
                reader.close(); //Closes the reader to prevent memory leaks
            }else {
                logger.error("Input file not provided. Use -i flag to specify the input file. \n");
            } 
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\ \n");
        }
    }
}
