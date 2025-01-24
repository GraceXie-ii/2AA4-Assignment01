package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private Position position;
    private Direction direction;
    private Path path;
    private Maze maze;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getEntry();
        this.direction = new Direction("EAST");
        this.path = new Path();
    }

    public boolean explore() {
        while (!position.equals(maze.getExit())) {
            Position nextPosition = position.move(direction);

            if (isValidPosition(nextPosition)) {
                position = nextPosition;
                path.addInstructions("F");
            } else {
                // If we hit a wall, stop exploration for now
                return false;
            }
        }
        return true;
    }

    public void turnLeft() {
        direction.turnLeft();
        path.addInstructions("L");
    }

    public void turnRight() {
        direction.turnRight();
        path.addInstructions("R");
    }

    private boolean isValidPosition(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return x >= 0 && x < maze.getGrid().length && y >= 0 && y < maze.getGrid()[0].length && maze.isPath(x, y);
    }

    public Path getPath() {
        return path;
    }
}