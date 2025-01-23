package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private char[][] grid;
    private Position entry, exit;

    public Maze(String mazeFile){
        //Load maze from file
    }

    public boolean isPath(int x, int y){
        //True if the position is not a dead end
        return grid[x][y] == ' ';
    }

    public Position getEntry(){
        return entry;
    }

    public Position getExt(){
        return exit;
    }
}
