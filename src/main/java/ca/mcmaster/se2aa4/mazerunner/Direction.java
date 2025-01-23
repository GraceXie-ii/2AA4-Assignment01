package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    private static final String[] DIRECTIONS = {"NORTH", "EAST", "SOUTH", "WEST"};
    private int currentIndex;

    public Direction(String direction){
        for(int i = 0; i < DIRECTIONS.length; i++){
            if(DIRECTIONS[i].equalsIgnoreCase(direction)){
                this.currentIndex = i;
            }
        }
    }

    public void turnLeft(){
        currentIndex = (currentIndex + 3) % 4;
    }

    public void turnRight(){
        currentIndex = (currentIndex + 1) % 4;
    }

    public String getCurrentDirection(){
        return DIRECTIONS[currentIndex];
    }
}
