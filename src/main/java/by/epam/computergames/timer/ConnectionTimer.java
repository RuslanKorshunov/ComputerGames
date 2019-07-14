package by.epam.computergames.timer;

import by.epam.computergames.exception.IncorrectDataException;

import java.util.Timer;
import java.util.TimerTask;

public class ConnectionTimer {
    private static ConnectionTimer instance;
    private Timer timer;

    public ConnectionTimer() {
        timer = new Timer();
    }

    public static ConnectionTimer getInstance() {
        if (instance==null) {
            instance = new ConnectionTimer();
        }
        return instance;
    }

    public void schedule(TimerTask task, long delay, long period) throws IncorrectDataException {
        if (instance==null) {
            throw new IncorrectDataException("ConnectionTimer isn't initialized");
        }
        timer.schedule(task, delay, period);
    }

    public void cancel() throws IncorrectDataException {
        if (instance==null) {
            throw new IncorrectDataException("ConnectionTimer isn't initialized");
        }
        timer.cancel();
    }
}