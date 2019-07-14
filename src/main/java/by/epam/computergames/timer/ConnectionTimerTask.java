package by.epam.computergames.timer;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class ConnectionTimerTask extends TimerTask {
    private static final Logger logger= LogManager.getLogger(ConnectionTimerTask.class);

    @Override
    public void run() {
        logger.info("Checking connections begins");
        try {
            ConnectionPool.checkConnections();
        } catch (IncorrectDataException | ConnectionException e) {
            logger.warn("Unable to check connections due to a internal error", e);
        }
        logger.info("Checking connections finishes");
    }
}