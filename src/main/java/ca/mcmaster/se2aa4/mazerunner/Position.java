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

    public void move(Direction direction){
        //Change position based on if moved
    }
}
