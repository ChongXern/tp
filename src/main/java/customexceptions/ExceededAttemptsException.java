package customexceptions;

public class ExceededAttemptsException extends Exception {
    private boolean canTryAgain;
    public ExceededAttemptsException(boolean canTryAgain) {
        this.canTryAgain = canTryAgain;
    }
    public boolean isCanTryAgain() {
        return this.canTryAgain;
    }
}
