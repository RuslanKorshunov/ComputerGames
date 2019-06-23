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
    private static final Logger logger=LogManager.getLogger(AbstractDAO.class);

    protected ProxyConnection connection;

    public AbstractDAO() throws ConnectionException
    {
        this.connection=ConnectionPool.getInstance().getConnection();
    }

    public abstract T findBy(Object ... values) throws DAOException;

    public abstract List<T> find(Object...values) throws DAOException;

    public abstract void update(String tableName, String column, String newValue, String id) throws DAOException;

    public abstract void create(T entity) throws DAOException;

    public abstract double findAverageValue(Object ... values) throws DAOException;

    public abstract long findSize(Object ... values) throws DAOException;

    public abstract void delete(Object ... values) throws DAOException;

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
                logger.info("statement's closed.");
            }
            catch (SQLException e)
            {
                logger.warn("statement couldn't be closed.", e);
            }
        }
    }
}