package ca.mcmaster.se2aa4.mazerunner;

public interface Explorer {

    public boolean explore(Maze maze);

    public Path getPath();
}