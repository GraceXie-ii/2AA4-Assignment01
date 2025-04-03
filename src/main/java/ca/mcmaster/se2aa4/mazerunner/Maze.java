package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Maze {
    private char[][] grid;
    private Position entry, exit;

    public Maze(String filePath) throws IOException{ //Constructor for the maze, IOException is thrown if the file is not found
        List<String> lines = Files.readAllLines(Paths.get(filePath)); //Read the lines of the file
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Maze file is empty.");
        }

        int rows = lines.size(); //Get the number of rows and columns in the maze
        int cols = lines.get(0).length();

        grid = new char[rows][cols]; //Create a 2D array to represent the maze

        for(int i = 0; i < rows; i++){ //Fill the 2D array with the characters from the file
            String line = lines.get(i);
            for(int j = 0; j < cols; j++){
                grid[i][j] = line.charAt(j);
                if(grid[i][j] == ' ' && j == 0){
                    entry = new Position(i, j);
                }
                if(j == cols - 1 && grid[i][j] == ' '){
                    exit = new Position(i, j);
                }
            }
        }
    }

    public boolean isPath(Position position) { //Method to check if a position is empty
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != '#';
    }

    public char[][] getGrid() { //Method to get the grid of the maze
        return grid;
    }

    public Position getEntry(){ //Method to get the entry and exit positions of the maze
        return entry;
    }

    public Position getExit(){
        return exit;
    }
}
