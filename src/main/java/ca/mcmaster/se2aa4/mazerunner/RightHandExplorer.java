package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Command.*;
import ca.mcmaster.se2aa4.mazerunner.Observer.*;

public class RightHandExplorer implements Explorer {
    private final Path path;
    private final Maze maze;
    private final Executor executor;
    private final MoveForwardCommand moveForward;
    private final TurnRightCommand turnRight;
    private final TurnLeftCommand turnLeft;
    private final Recorder recorder;
    private final int maxSteps;

    public RightHandExplorer(Maze maze) {
        this.maze = maze;
        this.path = new Path();
        this.executor = new Executor();
        this.recorder = new Recorder();
        
        // Initialize commands
        this.moveForward = new MoveForwardCommand(this, maze);
        this.turnRight = new TurnRightCommand(this, maze);
        this.turnLeft = new TurnLeftCommand(this, maze);
        
        // Register observers
        this.moveForward.addObserver(recorder);
        this.turnRight.addObserver(recorder);
        this.turnLeft.addObserver(recorder);
        
        // Set initial state
        Direction initialDirection = new Direction("EAST");
        this.path.setCurrentDirection(initialDirection);
        this.path.updatePosition(maze.getEntry());
        this.maxSteps = maze.getGrid().length * maze.getGrid()[0].length * 2; // Prevent infinite loops
    }

    @Override
    public boolean explore() {
        int steps = 0;
        
        while (!isAtExit() && steps < maxSteps) {
            steps++;
            
            if (canMoveRight()) {
                path.addInstructions("R");
                executor.executeCommand(turnRight);
                path.addInstructions("F");
                executor.executeCommand(moveForward);
            } 
            else if (canMoveForward()) {
                path.addInstructions("F");
                executor.executeCommand(moveForward);
            } 
            else if (canMoveLeft()) {
                path.addInstructions("L");
                executor.executeCommand(turnLeft);
                path.addInstructions("F");
                executor.executeCommand(moveForward);
            } 
            else {
                // Turn around (180° - two right turns)
                path.addInstructions("R");
                path.addInstructions("R");
                executor.executeCommand(turnRight);
                executor.executeCommand(turnRight);
            }
        }
        
        return isAtExit();
    }

    private boolean canMoveForward() {
        Position nextPos = path.getCurrentPosition().move(path.getCurrentDirection());
        return maze.isPath(nextPos);
    }

    private boolean canMoveRight() {
        Direction rightDir = new Direction(path.getCurrentDirection().getCurrentDirection());
        rightDir.turnRight();
        Position rightPos = path.getCurrentPosition().move(rightDir);
        return maze.isPath(rightPos);
    }

    private boolean canMoveLeft() {
        Direction leftDir = new Direction(path.getCurrentDirection().getCurrentDirection());
        leftDir.turnLeft();
        Position leftPos = path.getCurrentPosition().move(leftDir);
        return maze.isPath(leftPos);
    }

    private boolean isAtExit() {
        return (path.getCurrentPosition().getX() == maze.getExit().getX() && path.getCurrentPosition().getY() == maze.getExit().getY());
    }

    // Called by commands to update state
    public void updatePosition(Position newPosition) {
        path.updatePosition(newPosition);
    }

    public void updateDirection(Direction newDirection) {
        path.setCurrentDirection(newDirection);
    }

    // Getters for commands
    public Position getCurrentPosition() {
        return path.getCurrentPosition();
    }

    public Direction getCurrentDirection() {
        return path.getCurrentDirection();
    }

    // Path access methods
    public String getCanonicalPath() {
        return recorder.getCanonicalPath();
    }

    public String getFormattedPath() {
        return recorder.getFormattedPath();
    }

    @Override
    public Path getPath() {
        return path;
    }
}

/*
package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Command.*;

public class RightHandExplorer implements Explorer {
    private final Path path;
    private final Maze maze;
    private Position currentPosition;
    private final Direction currentDirection;
    private final int maxSteps;
    private int steps;
    
    public RightHandExplorer(Maze maze) {
        this.maze = maze;
        this.currentDirection = new Direction("EAST");
        this.path = new Path();
        this.path.setCurrentDirection(currentDirection);
        this.currentPosition = maze.getEntry();
        this.path.updatePosition(currentPosition);
        this.maxSteps = maze.getGrid().length * maze.getGrid()[0].length * 2;
        this.steps = 0;
    }

    @Override
    public boolean explore() {
        while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY())) {
            if (steps++ > maxSteps) {
                return false; // Prevent infinite loops
            }

            // Try right first
            Direction rightDir = new Direction(currentDirection.getCurrentDirection());
            rightDir.turnRight();
            Position rightPos = currentPosition.move(rightDir);
            
            if (maze.isPath(rightPos)) {
                turnRight();
                moveForward();
                continue;
            }

            // Then try forward
            Position forwardPos = currentPosition.move(currentDirection);
            if (maze.isPath(forwardPos)) {
                moveForward();
                continue;
            }

            // Then try left
            Direction leftDir = new Direction(currentDirection.getCurrentDirection());
            leftDir.turnLeft();
            Position leftPos = currentPosition.move(leftDir);
            
            if (maze.isPath(leftPos)) {
                turnLeft();
                moveForward();
                continue;
            }

            // Finally turn around if all else fails
            turnAround();
        }
        return true;
    }

    private void turnRight() {
        currentDirection.turnRight();
        path.addInstructions("R");
    }

    private void turnLeft() {
        currentDirection.turnLeft();
        path.addInstructions("L");
    }

    private void moveForward() {
        currentPosition = currentPosition.move(currentDirection);
        path.updatePosition(currentPosition);
        path.addInstructions("F");
    }

    private void turnAround() {
        turnRight();
        turnRight();
    }

    @Override
    public Path getPath() {
        return path;
    }
}
*/
/*
public class RightHandExplorer implements Explorer {
    private Path path;
    private Maze maze;
    private Executor executor;
    private MoveForwardCommand moveForwardCommand; //Command to move forward
    private TurnLeftCommand turnLeftCommand; //Command to turn left
    private TurnRightCommand turnRightCommand; //Command to turn right
    private Direction direction; //Direction of the explorer
    private Position currentPosition; //Current position of the explorer

    public RightHandExplorer(Maze maze) { // Constructor to set the path and commands
        this.maze = maze;
        this.direction = new Direction("EAST"); //Set the initial direction of the explorer to EAST
        this.path = new Path();
        this.path.setCurrentDirection(new Direction("EAST"));
        this.path.updatePosition(maze.getEntry());
        this.executor = new Executor();
        this.moveForwardCommand = new MoveForwardCommand(this, maze);
        this.turnLeftCommand = new TurnLeftCommand(this, maze);
        this.turnRightCommand = new TurnRightCommand(this, maze);
        this.currentPosition = maze.getEntry(); //Set the initial position of the explorer to the entry of the maze
    }

    private boolean canMoveForward() { //Method to check if the explorer can move forward
        Position nextPos = this.currentPosition.move(this.direction);
        return maze.isPath(nextPos);
    }

    private boolean canMoveRight() {
        Direction rightDir = new Direction(direction.getCurrentDirection());
        rightDir.turnRight();
        Position rightPos = currentPosition.move(rightDir);
        return maze.isPath(rightPos);
    }

    private boolean canMoveLeft() {
        Direction leftDir = new Direction(direction.getCurrentDirection());
        leftDir.turnLeft();
        Position leftPos = currentPosition.move(leftDir);
        return maze.isPath(leftPos);
    
    }

    private boolean isAtExit() {
        return currentPosition.equals(maze.getExit());
    }

    @Override
    public boolean explore() { //Method to explore the maze 
        Position currentPosition = maze.getEntry(); 
        Direction currentDirection = new Direction("EAST");

<<<<<<< HEAD
        while (!currentPosition.equals(maze.getExit())){
        
            // Try right first
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());
            rightDirection.turnRight();
=======
        while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY()))  { //While the explorer has not reached the exit
            executor.executeCommand(turnRightCommand); //Execute the move forward command
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());  //Create a new direction to the right of the current direction
>>>>>>> e5b413ce2321d4a8b82c41dd1493938f67e60d99
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition)) {
                path.addInstructions("R");
<<<<<<< HEAD
                currentDirection = rightDirection;
                path.addInstructions("F");
                currentPosition = currentPosition.move(currentDirection);
                continue;
            }
            
            // Then try forward
            Position forwardPosition = currentPosition.move(currentDirection);
            if (maze.isPath(forwardPosition)) {
                path.addInstructions("F");
                currentPosition = forwardPosition;
                continue;
            }
            
            // Then try left
            Direction leftDirection = new Direction(currentDirection.getCurrentDirection());
            leftDirection.turnLeft();
            Position leftPosition = currentPosition.move(leftDirection);
            
            if (maze.isPath(leftPosition)) {
                path.addInstructions("L");
                currentDirection = leftDirection;
                path.addInstructions("F");
                currentPosition = currentPosition.move(currentDirection);
                continue;
            }
            
            // Finally turn around if all else fails
            currentDirection.turnRight();
            currentDirection.turnRight();
            path.addInstructions("R");
            path.addInstructions("R");

        }
        
        return true;

        /*while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY()))  { //While the explorer has not reached the exit
            executor.executeCommand(turnRightCommand);
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());  //Create a new direction to the right of the current direction
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition)) { //If the right position is empty, turn right and move forward
                path.addInstructions("R");
                executor.executeCommand(moveForwardCommand);
                currentPosition = moveForwardCommand.getCurrentPosition(); //Update the current position of the explorer
                currentDirection = rightDirection; //Update the current direction of the explorer
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection); //If the right position is not empty, move forward

                if(maze.isPath(forwardPosition)) { //If the forward position is empty, move forward
                    executor.executeCommand(moveForwardCommand);
                    currentPosition = moveForwardCommand.getCurrentPosition(); //Update the current position of the explorer
                    path.addInstructions("F");
                } else { //If the forward position is not empty, turn left and move forward
                    executor.executeCommand(turnLeftCommand);

                    Direction leftDirection = new Direction(currentDirection.getCurrentDirection());
                    Position leftPosition = currentPosition.move(leftDirection); //Create a new direction to the left of the current direction

                    if(maze.isPath(leftPosition)) { //If the left position is empty, turn left and move forward
                        path.addInstructions("L");
                        executor.executeCommand(moveForwardCommand);
                        currentPosition = moveForwardCommand.getCurrentPosition(); //Update the current position of the explorer
                        currentDirection = leftDirection; //Update the current direction of the explorer
                        path.addInstructions("F");
                    } else { //If the forward and left positions are not empty, turn around and move forward
                        executor.executeCommand(turnRightCommand);
                        executor.executeCommand(turnRightCommand);
                        path.addInstructions("R");
                        path.addInstructions("R");
=======
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
>>>>>>> e5b413ce2321d4a8b82c41dd1493938f67e60d99
                    }
                }
            }
        }     
       return true;*/
       /*
    }

    public Path getPath() { //Method to get the path taken by the explorer
        return path;
    }

    /*public void setPath(Path path) { //Method to set the path taken by the explorer
        this.path = path;
    }*/
//}*/*/
