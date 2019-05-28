package by.epam.computergames.connection;

import by.epam.computergames.exception.IncorrectDataException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool
{
    private static final int DEFAULT_POOL_SIZE=20;
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> connectionsFree;
    private BlockingQueue<ProxyConnection> connectionsUses=new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
    
    private ConnectionPool() throws ConnectionException
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionBuilder builder=new ConnectionBuilder();
            builder.create(DEFAULT_POOL_SIZE);
            connectionsFree=builder.getConnections();
        }
        catch (SQLException e)
        {
            throw new ConnectionException("DriverManager can't register Driver.", e);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionException
    {
        if(instance==null)
        {
            instance=new ConnectionPool();
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ConnectionException
    {
        ProxyConnection connection;
        try
        {
            connection=instance.connectionsFree.take();
            instance.connectionsUses.add(connection);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionException("ConnectionPool cant' give connection.", e);
        }
        return connection;
    }

    public void returnConnection(ProxyConnection connection) throws ConnectionException
    {
        if(instance==null)
        {
            throw new ConnectionException("ConnectionPool isn't initialized.");
        }
        if(connection==null)
        {
            throw new ConnectionException("Connection is null.");
        }
        if(instance.connectionsUses.contains(connection))
        {
            instance.connectionsUses.remove(connection);
            instance.connectionsFree.add(connection);
        }
    }

    public void checkConnections() throws IncorrectDataException, ConnectionException//TODO переделать
    {
        if(instance==null)
        {
            throw new IncorrectDataException("ConnectionPool isn't initialized.");
        }
        int sizeFree=instance.connectionsFree.size();
        int sizeUses=instance.connectionsUses.size();
        if(sizeFree+sizeUses!= DEFAULT_POOL_SIZE)//TODO это так делается???
        {
            ConnectionBuilder builder=new ConnectionBuilder();
            builder.create(Math.abs(DEFAULT_POOL_SIZE -(sizeFree+sizeUses)));
            instance.connectionsFree.addAll(builder.getConnections());
        }
    }
}