package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.connection.ProxyConnection;
import by.epam.computergames.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDAO<T>
{
    private static final Logger logger= LogManager.getLogger(AbstractDAO.class);

    protected ProxyConnection connection;

    public AbstractDAO() throws ConnectionException
    {
        this.connection=ConnectionPool.getInstance().getConnection();
    }

    public abstract T findById(String id) throws ConnectionException, IncorrectDataException;

    public void returnConnection() throws IncorrectDataException, ConnectionException
    {
        ConnectionPool.getInstance().returnConnection(connection);
    }

    protected void closeStatement(PreparedStatement statement)
    {
        if(statement!=null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                logger.warn("statement couldn't be closed.");
            }
        }
    }
}