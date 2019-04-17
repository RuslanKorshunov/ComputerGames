package by.epam.computergames.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class WrapperConnection
{
    private static final Logger logger= LogManager.getLogger(WrapperConnection.class);

    private Connection connection;

    private static final String URL="jdbc:mysql://localhost:3306/games";
    private static final String USER="root";
    private static final String PASSWORD="12345";

    public WrapperConnection() throws ConnectionException
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

    public ResultSet execute(String query) throws ConnectionException//TODO вынести в отдельный класс
    {
        if(query==null)//TODO проверка на NULL
        {

        }
        ResultSet resultSet;
        Statement statement=null;
        try
        {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
        }
        catch (SQLException e)
        {
            throw new ConnectionException();
        }
        finally
        {
            if(statement!=null)
            {
                try
                {
                    statement.close();
                }
                catch(SQLException e)
                {
                    logger.warn("Statement couldn't closed.");
                }
            }
        }
        return resultSet;
    }

    public void close() throws ConnectionException
    {
        try
        {
            connection.close();
        }
        catch(SQLException e)
        {
            throw  new ConnectionException("Connection couldn't be closed.");
        }
    }
}
