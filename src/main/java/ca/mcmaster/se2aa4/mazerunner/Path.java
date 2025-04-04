package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> instructions;
    private Position currentPosition;
    private Direction currentDirection;

    public Path() { //Constructor to initialize the list of instructions
        this.instructions = new ArrayList<>();
    }

    public void addInstructions(String instruction) { //Method to add an instruction to the list
        instructions.add(instruction);
    }

    public List<String> getInstructions() { //Method to get the list of instructions
        return instructions;
    }

    public String canonicalPath(){ //Method to format the canonical path
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < instructions.size(); i++) { //Loop through the list of instructions
            char c = instructions.get(i).charAt(0); //Get the first character of the instruction
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) == c) { //If the last character of the string is the same as the current character append it
                sb.append(c);
            } else { //If the last character of the string is not the same as the current character append a space and the current character
                sb.append(' ');
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String formatPath() { //Method to format the formatted path
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < instructions.size(); i++) { //Loop through the list of instructions
            if (instructions.get(i).equals(instructions.get(i - 1))) { //If the current instruction is the same as the previous instruction increment the count
                count++;
            } else {
                if (count > 1) { //If the count is greater than 1 append the count and the instruction
                    sb.append(count).append(instructions.get(i - 1)).append(' ');
                } else {
                    sb.append(instructions.get(i - 1)).append(' '); //If the count is not greater than 1 append the instruction
                }
                count = 1;
            }
        }

        if (count > 1) { //If the count is greater than 1 append the count and the last instruction
            sb.append(count).append(instructions.get(instructions.size() - 1));
        } else {
            sb.append(instructions.get(instructions.size() - 1));
        }

        return sb.toString().trim();
    }

    public void updatePosition(Position position) { // Update the current position
        this.currentPosition = position;
    }

    public Position getCurrentPosition() { // Get the current position
        return currentPosition;
    }

    public void setCurrentDirection(Direction direction) { // Set the current direction
        this.currentDirection = direction;
    }

    public Direction getCurrentDirection() { // Get the current direction
        return currentDirection;
    }
}
