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
            executor.executeCommand(turnRightCommand); //Execute the move forward command
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());  //Create a new direction to the right of the current direction
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition)) { //If the right position is empty, turn right and move forward
                currentDirection.turnRight();
                path.addInstructions("R");
                executor.executeCommand(moveForwardCommand); //Execute the move forward command
                currentPosition = moveForwardCommand.getNewPosition();
                currentDirection = rightDirection; //Update the current direction to the right direction
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection); //If the right position is not empty, move forward
                
                if (maze.isPath(forwardPosition)) { //If the forward position is empty, move forward
                    executor.executeCommand(moveForwardCommand);
                    currentPosition = moveForwardCommand.getNewPosition();
                    path.addInstructions("F");
                } else{
                    executor.executeCommand(turnLeftCommand); //If the forward position is not empty, turn left and move forward
                    Direction leftDirection = new Direction(currentDirection.getCurrentDirection()); //Create a new direction to the left of the current direction
                    Position leftPosition = currentPosition.move(leftDirection); //Get the left position
                    if(maze.isPath(leftPosition)){
                        path.addInstructions("L");
                        executor.executeCommand(moveForwardCommand); //Execute the move forward command
                        currentPosition = moveForwardCommand.getNewPosition();
                        currentDirection = leftDirection;
                        path.addInstructions("F");
                    }else{
                        executor.executeCommand(turnRightCommand);
                        executor.executeCommand(turnRightCommand); //If the left position is not empty, turn right twice to go back to the original direction
                        path.addInstructions("RR");
                    }
                }
            }
        }     
       return true;
    }

    public Path getPath() { //Method to get the path taken by the explorer
        return path;
    }
}