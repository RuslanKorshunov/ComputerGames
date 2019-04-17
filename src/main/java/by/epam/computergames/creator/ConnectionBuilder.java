package by.epam.computergames.creator;

import by.epam.computergames.connection.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionBuilder
{
    private static final Logger logger= LogManager.getLogger(ConnectionBuilder.class);
    private static final String URL="jdbc:mysql://localhost:3306/CompGames";
    private static final String USER="root";
    private static final String PASSWORD="12345";

    private BlockingQueue<Connection> connections;

    public void create(int size) throws ConnectionException
    {
        connections =new LinkedBlockingQueue<>(size);
        try
        {
            for(int index=0; index<size; index++)
            {
                Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
                connections.add(connection);
            }
        }
        catch (SQLException e)
        {
            throw new ConnectionException("ConnectionBuilder can't create connections.");
        }
    }

    public BlockingQueue<Connection> getConnections()
    {
        return connections;
    }
}