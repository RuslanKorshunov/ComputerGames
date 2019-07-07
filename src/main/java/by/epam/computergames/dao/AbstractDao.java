package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.connection.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T> {
    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    protected WrapperConnection connection;

    public AbstractDao() throws DaoException {
        try
        {
            this.connection = ConnectionPool.getInstance().getConnection();
        }
        catch (ConnectionException e)
        {
            throw new DaoException(e);
        }
    }

    public abstract T findBy(Object... values) throws DaoException;

    public abstract List<T> find(Object... values) throws DaoException;

    public abstract void update(Object ... values) throws DaoException;

    public abstract void create(T entity) throws DaoException;

    public abstract double findAverageValue(Object... values) throws DaoException;

    public abstract long findSize(Object... values) throws DaoException;

    public abstract void delete(Object... values) throws DaoException;

    public void returnConnection() throws ConnectionException {
        ConnectionPool.getInstance().returnConnection(connection);
    }

    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                logger.info("statement's closed.");
            } catch (SQLException e) {
                logger.warn("statement couldn't be closed.", e);
            }
        }
    }
}