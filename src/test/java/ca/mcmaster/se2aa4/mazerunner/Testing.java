package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class Testing {
    private Maze maze;
    private Explorer explorer;
    private Path path;

    public Testing(){
        try {
            maze = new Maze("./examples/small.maz.txt"); //Entry: (8, 0), Exit:(5, 10)
            explorer = new RightHandExplorer(maze); // initialize the explorer with the maze
        } catch (Exception e) {
            System.out.println("Error initializing maze: " + e.getMessage());
        }
    }

    @Test
    public void testExplorerInitialization() {
       assertEquals(maze.getEntry(), explorer.getPath().getCurrentPosition());
       assertEquals("EAST", explorer.getPath().getCurrentDirection().getCurrentDirection());
    }

    // Test 1: to check if the maze is initialized correctly
    @Test
    public void testMazeInitialization() {
        assertNotNull(maze, "Maze should be initialized successfully.");
    }

    //Test 2: check if the entry and exit position are correct
    @Test
    public void testMazeEntryExit() {
        assertNotNull(maze.getEntry(), "Maze entry should not be null.");
        assertNotNull(maze.getExit(), "Maze exit should not be null.");
        assertEquals(8, maze.getEntry().getX());
        assertEquals(0, maze.getEntry().getY());
        assertEquals(5, maze.getExit().getX());
        assertEquals(10, maze.getExit().getY());
    }

    //Test 3: Check if Canonical path is correct
    @Test
    public void testCanonicalPath() {
        explorer.explore();
        String expectedCanonicalPath = "F R F RR FF R FF R FF RR FFFF R FF R FFFF RR FF R FFFF R FF R FF RR FF L FF L FFFF R FF R FF RR FFFF R FF R FF RR FF R FF R FFFF R FF L FF R FF L F";
        assertEquals(expectedCanonicalPath, explorer.getPath().canonicalPath()); 
    }

    //Test 4: Check if Formatted path is correct
    @Test
    public void testFormattedPath() {
        explorer.explore();
        String expectedFormattedPath = "F R F 2R 2F R 2F R 2F 2R 4F R 2F R 4F 2R 2F R 4F R 2F R 2F 2R 2F L 2F L 4F R 2F R 2F 2R 4F R 2F R 2F 2R 2F R 2F R 4F R 2F L 2F R 2F L F";
        assertEquals(expectedFormattedPath, explorer.getPath().formatPath());
    }

    //Test 5: check -i flag and -p flag
    @Test
    public void testFlags(){
        String[] args = {"-i", "./examples/small.maz.txt", "-p", "F R F 2R 2F R 2F R 2F 2R 4F R 2F R 4F 2R 2F R 4F R 2F R 2F 2R 2F L 2F L 4F R 2F R 2F 2R 4F R 2F R 2F 2R 2F R 2F R 4F R 2F L 2F R 2F L F"};
        Main.main(args); //Run the main method with the args
    }

    //Test 6: check isPath method from Maze class
    @Test
    public void testIsPath() {
        Position position1 = new Position(5, 5); //Create a new position object
        boolean isPath1 = maze.isPath(position1); //Check if the position is empty
        assertEquals(true, isPath1);

        Position position2 = new Position(0, 0); //Create a new position object
        boolean isPath2 = maze.isPath(position2); //Check if the position is empty
        assertEquals(false, isPath2);

        Position position3 = new Position(8, 0); //Create a new position object
        boolean isPath3 = maze.isPath(position3); //Check if the position is empty
        assertEquals(true, isPath3);

        Position position4 = new Position(0, 2); //Create a new position object
        boolean isPath4 = maze.isPath(position4); //Check if the position is empty
        assertEquals(false, isPath4); //Check if the position is not empty
    }
    
    //Test 7: Check if explorer moves correctly
    @Test
    public void testExplorerMovement() {
        explorer.explore(); //Explore the maze
        Position currentPosition = maze.getEntry(); //Get the entry position of the maze
        Direction currentDirection = new Direction("EAST"); //Create a new direction object
        Position newPosition = currentPosition.move(currentDirection); //Move the explorer in the current direction
        assertEquals(8, newPosition.getX());//Check if the new position is correct
        assertEquals(1, newPosition.getY());//Check if the new position is correct
    }
    
    //Test 8: Check if turnRight works correctly
    @Test
    public void testExplorerTurn() {
        explorer.explore(); //Explore the maze
        Direction currentDirection = new Direction("EAST"); //Create a new direction object
        currentDirection.turnRight(); //Turn the explorer right
        assertEquals("SOUTH", currentDirection.getCurrentDirection()); //Check if the direction is correct
    }

    //Test 9: Check if turnLeft works correctly
    @Test
    public void testExplorerTurnLeft() {
        explorer.explore(); //Explore the maze
        Direction currentDirection = new Direction("EAST"); //Create a new direction object
        currentDirection.turnLeft(); //Turn the explorer left
        assertEquals("NORTH", currentDirection.getCurrentDirection()); //Check if the direction is correct
    }

    //Test 10: Check if adding instructions works correctly
    @Test
    public void testAddInstructions(){
        explorer.explore(); //Explore the maze
        Path path = ((Path) explorer.getPath()); //Get the path object
        path.addInstructions("F"); //Add instructions to the path
        assertEquals("F", path.getInstructions().get(path.getInstructions().size() - 1)); //Check if the last instruction is correct
    }
}
