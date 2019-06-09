package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.DeveloperDAO;
import by.epam.computergames.entity.Developer;

import java.util.List;

public class SearchDevelopersService extends AbstractService
{
    @Override
    public List<Developer> find(Object... values) throws ConnectionException, DAOException
    {
        List<Developer> developers;
        AbstractDAO dao=null;
        try
        {
            dao=new DeveloperDAO();
            developers=dao.find();
        }
        finally
        {
            returnConnection(dao);
        }

        return developers;
    }
}
