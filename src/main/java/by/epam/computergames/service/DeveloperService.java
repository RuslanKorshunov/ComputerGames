package by.epam.computergames.service;

import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.DeveloperDao;
import by.epam.computergames.entity.Developer;

import java.util.List;

public class DeveloperService extends AbstractService {
    @Override
    public List<Developer> find(Object... values) throws ServiceException {
        List<Developer> developers;
        try {
            AbstractDao dao = null;
            try {
                dao = new DeveloperDao();
                developers = dao.find();
            } finally {
                returnConnection(dao);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return developers;
    }
}