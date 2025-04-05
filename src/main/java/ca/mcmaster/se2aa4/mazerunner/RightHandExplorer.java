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