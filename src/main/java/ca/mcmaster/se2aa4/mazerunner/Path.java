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

    public void clearInstructions() {
        instructions.clear();
    }

    public String canonicalPath(){
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < instructions.size(); i++) {
            char c = instructions.get(i).charAt(0);
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) == c) {
                sb.append(c);
            } else {
                sb.append(' ');
                sb.append(c);
            }
        }

        return sb.toString();

    }

    public String formatPath() {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < instructions.size(); i++) {
            if (instructions.get(i).equals(instructions.get(i - 1))) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count).append(instructions.get(i - 1)).append(' ');
                } else {
                    sb.append(instructions.get(i - 1)).append(' ');
                }
                count = 1;
            }
        }

        if (count > 1) {
            sb.append(count).append(instructions.get(instructions.size() - 1));
        } else {
            sb.append(instructions.get(instructions.size() - 1));
        }

        return sb.toString().trim();
    }
}
