package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> instructions;

    public Path() {
        this.instructions = new ArrayList<>();
    }

    public void addInstructions(String instruction) {
        instructions.add(instruction);
    }

    public List<String> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return String.join(" ", instructions);
    }
}
