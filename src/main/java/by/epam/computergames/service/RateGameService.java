package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.MarkDAO;
import by.epam.computergames.entity.Mark;
import by.epam.computergames.exception.IncorrectDataException;

public class RateGameService extends AbstractService<Mark>
{
    @Override
    public void add(Mark entity) throws IncorrectDataException, ConnectionException, DAOException
    {
        int mark=entity.getMark();
        if(mark>=1 && mark<=10)
        {
            AbstractDAO dao=null;
            try
            {
                dao=new MarkDAO();
                dao.create(entity);
            }
            finally
            {
                returnConnection(dao);
            }
        }
        else
        {
            throw new IncorrectDataException("entity can't be less than 0 and more than 10.");
        }
    }
}
