package ca.mcmaster.se2aa4.mazerunner;

public abstract class Explorer {
    private Position position;
    private Direction direction;
    private Path path;

    public Explorer(Maze maze){
        this.position = maze.getEntry();
        this.direction = new Direction("EAST");
        this.path = new Path();
    }

    public void moveForward(){
        //Move forward in a direction
    }

    public void turnLeft(){
        direction.turnLeft();
    }

    public void turnRight(){
        direction.turnRight();
    }
}
