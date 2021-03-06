package by.epam.computergames.connection;

import by.epam.computergames.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 20;
    private static ConnectionPool instance;
    private static AtomicBoolean isCreated=new AtomicBoolean();
    private BlockingQueue<WrapperConnection> connectionsFree;
    private BlockingQueue<WrapperConnection> connectionsUses;

    private ConnectionPool() throws ConnectionException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionBuilder builder = new ConnectionBuilder();
            connectionsFree = builder.create(DEFAULT_POOL_SIZE);
            connectionsUses = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        } catch (SQLException e) {
            throw new ConnectionException("DriverManager can't register Driver.", e);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionException {
        if (isCreated.compareAndSet(false, true)) {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public WrapperConnection getConnection() throws ConnectionException {
        WrapperConnection connection;
        try {
            connection = instance.connectionsFree.take();
            instance.connectionsUses.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionException("ConnectionPool can't give connection.", e);
        }
        return connection;
    }

    public void returnConnection(WrapperConnection connection) throws ConnectionException {
        if (!isCreated.get()) {
            throw new ConnectionException("ConnectionPool isn't initialized.");
        }
        if (connection == null) {
            throw new ConnectionException("Connection is null.");
        }
        if (instance.connectionsUses.contains(connection)) {
            instance.connectionsUses.remove(connection);
            instance.connectionsFree.add(connection);
        }
    }

    public void destroy() {
        if(isCreated.compareAndSet(true, false)) {
            closeQueue(connectionsFree);
            connectionsFree = null;
            closeQueue(connectionsUses);
            connectionsUses = null;
            instance=null;
        }
    }

    private void closeQueue(BlockingQueue<WrapperConnection> queue) {
        if (!queue.isEmpty()) {
            for (WrapperConnection connection : queue) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    logger.warn("ConnectionPool can't close connection.");
                }
            }
            connectionsFree.remove();
        }
    }

    public static void checkConnections() throws IncorrectDataException, ConnectionException
    {
        if (!isCreated.get()) {
            throw new IncorrectDataException("ConnectionPool isn't initialized.");
        }
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            ConnectionPool instance = getInstance();
            BlockingQueue<WrapperConnection> connections = instance.connectionsUses;
            for (WrapperConnection connection : connections) {
                instance.returnConnection(connection);
            }
        } finally {
            lock.unlock();
        }
    }
}