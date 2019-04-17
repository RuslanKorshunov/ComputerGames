package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.exception.IncorrectDataException;

import java.sql.Connection;

public abstract class AbstractDAO<T>
{
    protected Connection connection;

    public AbstractDAO() throws ConnectionException
    {
        this.connection=ConnectionPool.getConnection();
    }

    public abstract T findById(String id) throws ConnectionException, IncorrectDataException;

    public void returnConnection() throws IncorrectDataException
    {
        ConnectionPool.returnConnection(connection);
    }
}