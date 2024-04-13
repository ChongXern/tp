package customexceptions;

import command.BaseCommand;
import user.InactivityTimer;

public class UndoNotPermittedException extends Exception {
    private boolean didUndoTimerRunout;
    private boolean isUndoable;
    private static final String PAST_PERMITTED_TIME_MESSAGE = "Sorry, unable to undo. 10 seconds have passed";
    private static final String UNDOABLE_MESSAGE = "Sorry, the previous command cannot be undone.";
    private static final String DEFAULT_MESSAGE = "Sorry, unable to undo previous action.";

    public UndoNotPermittedException(boolean didUndoTimerRunout, boolean canUndo) {
        this.didUndoTimerRunout = didUndoTimerRunout;
        isUndoable = canUndo;
    }

    @Override
    public String getMessage() {
        if (isUndoable) {
            return UNDOABLE_MESSAGE;
        }
        if (didUndoTimerRunout) {
            return PAST_PERMITTED_TIME_MESSAGE;
        }
        return DEFAULT_MESSAGE;
    }
}
