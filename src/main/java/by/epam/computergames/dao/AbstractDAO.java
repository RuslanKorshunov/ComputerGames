package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.connection.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T>
{
    private static final Logger logger= LogManager.getLogger(AbstractDAO.class);

    protected ProxyConnection connection;

    public AbstractDAO() throws ConnectionException
    {
        this.connection=ConnectionPool.getInstance().getConnection();
    }

    public T findBy(String id) throws DAOException
    {
        return null;
    }

    public List<T> find(int idFirst, int size) throws DAOException
    {
        return null;
    }

    public void update(String tableName, String column, String newValue, String id) throws DAOException
    { }

    public void create(T entity) throws DAOException
    {}

    public void returnConnection() throws ConnectionException
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