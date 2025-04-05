package ca.mcmaster.se2aa4.mazerunner;

public interface Explorer { //Interface for the explorer

    public boolean explore(); //Method to explore the maze

    public Path getPath(); //Method to get the path taken by the explorer
}