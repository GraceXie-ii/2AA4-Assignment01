package ca.mcmaster.se2aa4.mazerunner.Command; //Package for the command classes

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze;

public class TurnLeftCommand implements Command { //Class to represent the turn Left command

    private Explorer explorer; //The explorer that will execute the command
    private Maze maze; //The maze that the explorer is in

    public TurnLeftCommand(Explorer explorer, Maze maze) { //Constructor to set the explorer and maze
        this.explorer = explorer;
        this.maze = maze;
    }

    @Override
    public void execute() { //Method to execute the turn Left command
        Direction currentDirection = explorer.getPath().getCurrentDirection(); //Get the current direction of the explorer
        currentDirection.turnLeft(); //Turn the explorer to the Left
        explorer.getPath().setCurrentDirection(currentDirection); //Update the direction of the explorer
    }
}