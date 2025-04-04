package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Position;

public class MoveForwardCommand implements Command { //Class to represent the move forward command

    private Explorer explorer; //The explorer that will execute the command
    private Maze maze; //The maze that the explorer is in


    public MoveForwardCommand(Explorer explorer, Maze maze) { //Constructor to set the explorer and maze
        this.explorer = explorer;
        this.maze = maze;
    }

    @Override
    public void execute() { //Method to execute the move forward command
        Position currentPosition = explorer.getPath().getCurrentPosition(); //Get the current position of the explorer
        Direction direction = explorer.getPath().getCurrentDirection(); //Get the current direction of the explorer
        Position newPosition = currentPosition.move(direction); //Move the explorer in the current direction

        if (maze.isValidMove(newPosition)) { //If the move is valid, update the position of the explorer
            explorer.getPath().updatePosition(newPosition);
        } else {
            System.out.println("Invalid move!"); //If the move is invalid, print an error message
        }
    }
}
