package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;

import java.util.List;

public abstract class AbstractService<T>
{
    public T find(Object ... values) throws IncorrectDataException,
                                            ConnectionException,
                                            DAOException,
                                            CryptologistException
    {
        return null;
    }

    public List<T> findEntities(Object ... values)  throws IncorrectDataException,
                                            ConnectionException,
                                            DAOException,
                                            CryptologistException
    {
        return null;
    }

    public void change(Object ... values) throws IncorrectDataException,
                                                    ConnectionException,
                                                    DAOException
    {};
    public void add(T entity) throws IncorrectDataException,
                                        ConnectionException,
                                        DAOException,
                                        CryptologistException
    {};
}