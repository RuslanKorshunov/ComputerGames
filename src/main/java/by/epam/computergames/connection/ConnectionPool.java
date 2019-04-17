package by.epam.computergames.connection;

import by.epam.computergames.creator.ConnectionBuilder;
import by.epam.computergames.exception.IncorrectDataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool
{
    private static final int SIZE=20;
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionsFree;
    private BlockingQueue<Connection> connectionsUses=new LinkedBlockingQueue<>(SIZE);
    
    private ConnectionPool() throws ConnectionException
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionBuilder builder=new ConnectionBuilder();
            builder.create(SIZE);
            connectionsFree=builder.getConnections();
        }
        catch (SQLException e)
        {
            throw new ConnectionException("DriverManager can't register Driver.");
        }
    }

    public static Connection getConnection() throws ConnectionException
    {
        if(instance==null)
        {
            instance=new ConnectionPool();
        }
        Connection connection=null;
        try
        {
            connection=instance.connectionsFree.take();
            instance.connectionsUses.remove(connection);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionException("ConnectionPool cant' give connection.");
        }
        return connection;
    }

    public static void returnConnection(Connection connection) throws IncorrectDataException
    {
        if(instance==null)
        {
            throw new IncorrectDataException("ConnectionPool isn't initialized.");
        }
        if(connection==null)
        {
            throw new IncorrectDataException("Connection is null.");
        }
        instance.connectionsUses.remove(connection);
        instance.connectionsFree.add(connection);
    }

    public void checkConnections() throws IncorrectDataException, ConnectionException
    {
        if(instance==null)
        {
            throw new IncorrectDataException("ConnectionPool isn't initialized.");
        }
        int sizeFree=instance.connectionsFree.size();
        int sizeUses=instance.connectionsUses.size();
        if(sizeFree+sizeUses!=SIZE)//TODO это так делается???
        {
            ConnectionBuilder builder=new ConnectionBuilder();
            builder.create(Math.abs(SIZE-(sizeFree+sizeUses)));
            instance.connectionsFree.addAll(builder.getConnections());
        }
    }
}