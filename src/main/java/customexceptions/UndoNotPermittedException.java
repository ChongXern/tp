package customexceptions;

public class UndoNotPermittedException extends Exception {
    private boolean didUndoTimerRunout;
    private boolean isNotUndoable;
    private static final String PAST_PERMITTED_TIME_MESSAGE = "Sorry, unable to undo. 10 seconds have passed";
    private static final String UNDOABLE_MESSAGE = "Sorry, the previous command cannot be undone.";
    private static final String DEFAULT_MESSAGE = "Sorry, unable to undo previous action.";

    public UndoNotPermittedException(boolean didUndoTimerRunout, boolean isNotUndoable) {
        this.didUndoTimerRunout = didUndoTimerRunout;
        this.isNotUndoable = isNotUndoable;
    }

    @Override
    public String getMessage() {
        if (isNotUndoable) {
            return UNDOABLE_MESSAGE;
        }
        if (didUndoTimerRunout) {
            return PAST_PERMITTED_TIME_MESSAGE;
        }
        return DEFAULT_MESSAGE;
    }
}
