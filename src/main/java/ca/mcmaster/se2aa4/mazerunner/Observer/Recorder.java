package ca.mcmaster.se2aa4.mazerunner.Observer;

public class Recorder implements Observer{
    private String movementLog;
    
    public Recorder(){
        this.movementLog = "";
    }

    @Override
    public void update(char action) {
        movementLog += action + " ";
    }

    public String getCanonicalPath() {
        String[] actions = movementLog.split(" "); // Split the movement log into a list of instructions
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < actions.length; i++) { // Loop through the list of instructions
            char c = actions[i].charAt(0); // Get the first character of the instruction
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) == c) { // If the last character of the string is the same as the current character, append it
                sb.append(c);
            } else { // If the last character of the string is not the same as the current character, append a space and the current character
                sb.append(' ');
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String getFactorizedPath(){
        String[] actions = movementLog.split(" ");
        StringBuilder formattedPath = new StringBuilder();
        int count = 1;
        for (int i = 0; i < actions.length; i++) {
            if (i < actions.length - 1 && actions[i].equals(actions[i + 1])) {
                count++;
            } else {
                if (count > 1) {
                    formattedPath.append(count).append(actions[i]).append(" ");
                    count = 1;
                } else {
                    formattedPath.append(actions[i]).append(" ");
                }
            }
        }
        return formattedPath.toString().trim();
    }
}
