package by.epam.computergames.service;

import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class AbstractService<T> {

    public T find(Object... values) throws ServiceException{
        return null;
    }

    public List<T> findAll(Object... values) throws ServiceException {
        return null;
    }

    public void update(Object... values) throws ServiceException {
    }

    public void add(T entity) throws ServiceException{
    }

    public void delete(T entity) throws ServiceException{
    }

    protected void returnConnection(AbstractDao dao) {
        if (dao != null) {
            dao.close();
        }
    }
}