package by.epam.computergames.connection;

import by.epam.computergames.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnection
{
    private Connection connection;

    private static final String URL="jdbc:mysql://localhost:3306/games";
    private static final String USER="root";
    private static final String PASSWORD="12345";

    public CustomConnection() throws ConnectionException
    {
        try
        {
            connection= DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e)
        {
            throw new ConnectionException("Connection couldn't be created.");
        }
    }
}
