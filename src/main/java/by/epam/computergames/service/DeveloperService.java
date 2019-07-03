package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.DeveloperDao;
import by.epam.computergames.entity.Developer;

import java.util.List;

public class DeveloperService extends AbstractService {
    @Override
    public List<Developer> find(Object... values) throws ConnectionException, DaoException {
        List<Developer> developers;
        AbstractDao dao = null;
        try {
            dao = new DeveloperDao();
            developers = dao.find();
        } finally {
            returnConnection(dao);
        }

        return developers;
    }
}