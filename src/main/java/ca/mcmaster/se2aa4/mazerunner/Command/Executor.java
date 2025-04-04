package ca.mcmaster.se2aa4.mazerunner.Command;

import java.util.Stack;

public class Executor {
    private Stack<Command> commandHistory = new Stack<>(); //Stack to keep track of executed commands

    public void executeCommand(Command command) { //Method to execute a command
        command.execute(); //Execute the command
    }

    public void resetCommand(){
        commandHistory.clear(); //Clear the command history stack
    }
}
