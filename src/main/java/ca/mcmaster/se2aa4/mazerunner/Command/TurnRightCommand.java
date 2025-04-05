package ca.mcmaster.se2aa4.mazerunner.Command; //Package for the command classes

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Explorer;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.Position;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.se2aa4.mazerunner.Observer.*;

public class TurnRightCommand implements Command { //Class to represent the turn right command

    private Explorer explorer; //The explorer that will execute the command
    private Maze maze; //The maze that the explorer is in
    private List<Observer> observers = new ArrayList<>(); //List to keep track of observers


    public TurnRightCommand(Explorer explorer, Maze maze) { //Constructor to set the explorer and maze
        this.explorer = explorer;
        this.maze = maze;
    }

    @Override
    public void execute() { //Method to execute the turn right command
        Direction currentDirection = ((Path) explorer.getPath()).getCurrentDirection(); //Get the current direction of the explorer
        currentDirection.turnRight(); //Turn the explorer to the right
        ((Path) explorer.getPath()).setCurrentDirection(currentDirection); //Update the direction of the explorer
        notifyObservers('R'); //Notify observers of the move
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