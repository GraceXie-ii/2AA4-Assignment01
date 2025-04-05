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

    public String getCanonicalPath(){
        String[] actions = movementLog.split(" ");
        StringBuilder canonicalPath = new StringBuilder();
        int count = 1;
        for (int i = 0; i < actions.length; i++) {
            if (i < actions.length - 1 && actions[i].equals(actions[i + 1])) {
                count++;
            } else {
                if (count > 1) {
                    canonicalPath.append(count).append(actions[i]).append(" ");
                    count = 1;
                } else {
                    canonicalPath.append(actions[i]).append(" ");
                }
            }
        }
        return canonicalPath.toString().trim();
    }

    public String getFormattedPath(){
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
