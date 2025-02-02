package ca.mcmaster.se2aa4.mazerunner;

public class RightHandExplorer implements Explorer {
    private Path path = new Path();

    @Override
    public boolean explore(Maze maze) {
        System.out.println("Exploring maze using right hand rule");

        Position currentPosition = maze.getEntry();
        Direction currentDirection = new Direction("EAST");
        System.out.println("Current position: " + currentPosition.getX() + " " + currentPosition.getY());

        while (currentPosition.getX() != maze.getExit().getX() && currentPosition.getY() != maze.getExit().getY()) {
            
            Direction rightDirection = currentDirection;
            rightDirection.turnRight();
            Position rightPosition = currentPosition.move(rightDirection);
            if (maze.isPath(rightPosition.getX(), rightPosition.getY())) {
                currentDirection.turnRight();
                path.addInstructions("R");
                currentPosition = currentPosition.move(currentDirection);
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection);
                Direction leftDirection = currentDirection;
                rightDirection.turnLeft();
                Position leftPosition = currentPosition.move(leftDirection);
                if (maze.isPath(forwardPosition.getX(), forwardPosition.getY())) {
                    currentPosition = currentPosition.move(currentDirection);
                    path.addInstructions("F");
                } else if (maze.isPath(leftPosition.getX(), leftPosition.getY())) {
                    currentDirection.turnLeft();
                    path.addInstructions("L");
                    currentPosition = currentPosition.move(currentDirection);
                    path.addInstructions("F");
                } else {
                    currentDirection.turnRight();
                    currentDirection.turnRight();
                    path.addInstructions("R");
                    path.addInstructions("R");
                }
            }
        }
        return true;
    }

    public Path getPath() {
        return path;
    }
}
