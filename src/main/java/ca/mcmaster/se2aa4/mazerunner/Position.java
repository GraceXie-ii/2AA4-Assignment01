package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Position move(Direction direction) {
        switch (direction.getCurrentDirection()) {
            case "NORTH":
                return new Position(x- 1, y);
            case "SOUTH":
                return new Position(x + 1, y);
            case "EAST":
                return new Position(x, y + 1);
            case "WEST":
                return new Position(x, y - 1);
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
}
