package ca.mcmaster.se2aa4.mazerunner.Command;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Observer.Observer;
import ca.mcmaster.se2aa4.mazerunner.Observer.Recorder;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.Position;

public class MoveForwardCommand implements Command { //Class to represent the move forward command

    private Explorer explorer; //The explorer that will execute the command
    private Maze maze; //The maze that the explorer is in

    private List<Observer> observers = new ArrayList<>(); //List to keep track of observers


    public MoveForwardCommand(Explorer explorer, Maze maze) { //Constructor to set the explorer and maze
        this.explorer = explorer;
        this.maze = maze;
    }

    @Override
    public void execute() { //Method to execute the move forward command
        Position currentPosition = ((Path) explorer.getPath()).getCurrentPosition(); //Get the current position of the explorer
        Direction direction = ((Path) explorer.getPath()).getCurrentDirection(); //Get the current direction of the explorer
        Position newPosition = currentPosition.move(direction); //Move the explorer in the current direction

        if (maze.isValidMove(newPosition)) { //If the move is valid, update the position of the explorer
            ((Path) explorer.getPath()).updatePosition(newPosition);
        } else {
            System.out.println("Invalid move!"); //If the move is invalid, print an error message
            System.exit(1);
        }

        notifyObservers('F'); //Notify observers of the move
    }

    public Position getCurrentPosition() { //Method to get the current position of the explorer
        return ((Path) explorer.getPath()).getCurrentPosition(); //Return the current position of the explorer
    }

    public Direction getCurrentDirection() { //Method to get the current direction of the explorer
        return ((Path) explorer.getPath()).getCurrentDirection(); //Return the current direction of the explorer
    }

    public void addObserver(Recorder recorder) { //Method to add an observer to the command
        observers.add(recorder);
    }

    public void notifyObservers(char action){
        for (Observer observer : observers) { //Loop through all observers
            observer.update(action); //Notify each observer of the action taken
        }
    }
}
