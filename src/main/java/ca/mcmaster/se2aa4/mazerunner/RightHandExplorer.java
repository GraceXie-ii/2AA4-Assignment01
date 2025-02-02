package ca.mcmaster.se2aa4.mazerunner;

public class RightHandExplorer implements Explorer {
    private Path path = new Path();

    @Override
    public boolean explore(Maze maze) {
       Position currentPosition = maze.getEntry();
       Direction currentDirection = new Direction("EAST");
       
       while (!(currentPosition.getX() == maze.getExit().getX() && currentPosition.getY() == maze.getExit().getY()))  {
            Direction rightDirection = new Direction(currentDirection.getCurrentDirection());
            rightDirection.turnRight();
            Position rightPosition = currentPosition.move(rightDirection);
            
            if (maze.isPath(rightPosition) && !rightPosition.equals(currentPosition)) {
                currentDirection.turnRight();
                path.addInstructions("R");
                currentPosition = rightPosition;
                path.addInstructions("F");
            } else {
                Position forwardPosition = currentPosition.move(currentDirection);
                Direction leftDirection = new Direction(currentDirection.getCurrentDirection());
                leftDirection.turnLeft();
                Position leftPosition = currentPosition.move(leftDirection);
                
                if (maze.isPath(forwardPosition) && !forwardPosition.equals(currentPosition)) {
                    currentPosition = forwardPosition;
                    path.addInstructions("F");
                } else if (maze.isPath(leftPosition) && !leftPosition.equals(currentPosition)) {
                    currentDirection.turnLeft();
                    path.addInstructions("L");
                    currentPosition = leftPosition;
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