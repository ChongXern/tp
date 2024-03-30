package customexceptions;

public class IncompletePromptException extends Exception {
    public static final String[] INSTRUCTIONS = {
        "add-inflow", "add-outflow", "delete-inflow", "delete-outflow", "quit"};

    public IncompletePromptException(String line) {
        super(setMessage(line));
    }

    private static String setMessage(String prompt){
        String message = "Sorry, prompt input is unknown.";
        for(String instr: IncompletePromptException.INSTRUCTIONS){
            if(instr.equals(prompt)){
                message = "Sorry, your prompt appears incomplete. Could you finish your sentence?";
                break;
            } else if (instr.contains(prompt)){
                message = "Please prompt again with correct spelling.";
                break;
            }
        }
        return message;
    }
}
