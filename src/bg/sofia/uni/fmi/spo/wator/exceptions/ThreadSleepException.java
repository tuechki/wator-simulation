package bg.sofia.uni.fmi.spo.wator.exceptions;

public class ThreadSleepException extends RuntimeException{

    public ThreadSleepException(String message) {
        super("Sleep interruption wasn't successful in thread " + message);
    }
}
