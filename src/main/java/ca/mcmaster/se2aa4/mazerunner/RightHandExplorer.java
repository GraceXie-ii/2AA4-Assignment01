package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Command.Executor;
import ca.mcmaster.se2aa4.mazerunner.Command.MoveForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnRightCommand;

public class RightHandExplorer implements Explorer {
    private Path path;
    private Maze maze;
    private Executor executor;
    private MoveForwardCommand moveForwardCommand; //Command to move forward
    private TurnLeftCommand turnLeftCommand; //Command to turn left
    private TurnRightCommand turnRightCommand; //Command to turn right

    public RightHandExplorer() { // Constructor to set the path and commands
        this.path = new Path();
        this.maze = maze;
        this.executor = new Executor();
        this.moveForwardCommand = new MoveForwardCommand(this, maze);
        this.turnLeftCommand = new TurnLeftCommand(this, maze);
        this.turnRightCommand = new TurnRightCommand(this, maze);
    }

    @Override
    public boolean explore(Maze maze) { //Method to explore the maze 
        Position currentPosition = maze.getEntry(); 
        Direction currentDirection = new Direction("EAST");

        while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY()))  { //While the explorer has not reached the exit
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());  //Create a new direction to the right of the current direction
            rightDirection.turnRight();
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition)) { //If the right position is empty, turn right and move forward
                currentDirection.turnRight();
                path.addInstructions("R");
                currentPosition = rightPosition;
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection); //If the right position is not empty, move forward
                Direction leftDirection = new Direction(currentDirection.getCurrentDirection());
                leftDirection.turnLeft();
                Position leftPosition = currentPosition.move(leftDirection);
                
                if (maze.isPath(forwardPosition)) { //If the forward position is empty, move forward
                    currentPosition = forwardPosition;
                    path.addInstructions("F");
                } else if (maze.isPath(leftPosition)) { //If the forward position is not empty but the left position is empty, turn left and move forward
                    currentDirection.turnLeft();
                    path.addInstructions("L");
                    currentPosition = leftPosition;
                    path.addInstructions("F");
                } else { //If the forward and left positions are not empty, turn around and move forward
                    currentDirection.turnRight();
                    currentDirection.turnRight();
                    path.addInstructions("R");
                    path.addInstructions("R");
                }
            }
        }     
       return true;
    }

    public Path getPath() { //Method to get the path taken by the explorer
        return path;
    }
}