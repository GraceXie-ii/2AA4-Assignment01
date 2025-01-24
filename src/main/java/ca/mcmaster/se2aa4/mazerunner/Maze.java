package ca.mcmaster.se2aa4.mazerunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Maze {
    private char[][] grid;
    private Position entry, exit;

    public Maze(String filePath) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Maze file is empty.");
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        grid = new char[rows][cols];

        for(int i = 0; i < rows; i++){
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

    public boolean isPath(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != '#';
    }

    public char[][] getGrid() {
        return grid;
    }

    public Position getEntry(){
        return entry;
    }

    public Position getExit(){
        return exit;
    }
}
