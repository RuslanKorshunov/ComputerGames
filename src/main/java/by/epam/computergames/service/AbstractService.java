package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class AbstractService<T> {
    private static final Logger logger = LogManager.getLogger(AbstractService.class);

    public T find(Object... values) throws ServiceException{
        return null;
    }

    public List<T> findAll(Object... values) throws ServiceException {
        return null;
    }

    public void change(Object... values) throws ServiceException {
    }

    public void add(T entity) throws ServiceException{
    }

    public void delete(Object... values) throws ServiceException{
    }

    protected void returnConnection(AbstractDao dao) {
        try {
            if (dao != null) {
                dao.returnConnection();
            }
        } catch (ConnectionException e) {
            logger.warn("Service couldn't return connection.");
        }
    }
}