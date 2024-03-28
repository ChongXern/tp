package customexceptions;

import java.util.Arrays;

public class IncompletePromptException extends Exception {
    public static final String[] INSTRUCTIONS = {
        "add-inflow", "add-outflow", "delete-inflow", "delete-outflow", "quit"};
    private boolean isTypo = false;
    private boolean isIncomplete = false;
    private boolean isUnknown = false;

    public IncompletePromptException(String line) {
        int spaceIndex = line.indexOf(" ");
        String firstWord = (spaceIndex == -1) ? line : line.substring(0, spaceIndex);
        if (Arrays.asList(INSTRUCTIONS).contains(firstWord)) {
            isTypo = false;
            isIncomplete = true;
            isUnknown = false;
        } else {
            checkTypo(firstWord);
        }
    }

    public boolean getIsTypo() {
        return this.isTypo;
    }
    public boolean getIsIncomplete() {
        return this.isIncomplete;
    }
    public boolean getIsUnknown() {
        return this.isUnknown;
    }

    public void checkTypo(String word) {
        for (String instruction : INSTRUCTIONS) {
            if (instruction.contains(word)) {
                if (!isTypo) {
                    isTypo = true;
                    System.out.println("Did you mean: ");
                }
                System.out.print(instruction + " ");
            }
        }
        if (isTypo) {
            this.isIncomplete = true;
            System.out.println("?");
        } else {
            this.isUnknown = true;
        }
    }
}
