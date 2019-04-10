package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.connection.WrapperConnection;
import by.epam.computergames.exception.IncorrectDataException;

public abstract class AbstractDAO
{
    private WrapperConnection connection;

    public AbstractDAO() throws ConnectionException
    {
        this.connection=ConnectionPool.getConnection();
    }

    public void returnConnection() throws IncorrectDataException
    {
        ConnectionPool.returnConnection(connection);
    }
}
