package by.epam.computergames.connection;

import by.epam.computergames.exception.IncorrectDataException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool
{
    private static final int SIZE=20;
    private static ConnectionPool instance;
    private BlockingQueue<WrapperConnection> connectionsFree=new LinkedBlockingQueue<WrapperConnection>(SIZE);
    private BlockingQueue<WrapperConnection> connectionsUses=new LinkedBlockingQueue<WrapperConnection>(SIZE);
    
    private ConnectionPool() throws ConnectionException
    {
        for(int index=0; index<SIZE; index++)//TODO как я понял, это нужно вынести в класс ConnectionCreator
        {
            connectionsFree.add(new WrapperConnection());
        }
    }

    public static WrapperConnection getConnection() throws ConnectionException
    {
        if(instance==null)
        {
            instance=new ConnectionPool();
        }
        WrapperConnection connection=null;
        try
        {
            connection=instance.connectionsUses.take();
            instance.connectionsFree.remove(connection);
        }
        catch (InterruptedException e)
        {
            //TODO пробросить исключение?
        }
        return connection;
    }

    public static void returnConnection(WrapperConnection connection) throws IncorrectDataException
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
            for(int index=sizeFree+sizeUses; index<SIZE; index++)
            {
                instance.connectionsFree.add(new WrapperConnection());
            }
        }
    }
}