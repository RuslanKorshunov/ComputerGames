package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.MarkDAO;

public class FindAverageRatingService extends AbstractService
{
    @Override
    public Double find(Object... values) throws ConnectionException,
                                                DAOException
    {
        long idGame=(long)values[0];
        double averageRating=0;
        if(idGame>=1 && idGame<=10)
        {
            AbstractDAO dao=null;
            try
            {
                dao=new MarkDAO();
                averageRating=dao.findAverageValue(idGame);
            }
            finally
            {
                returnConnection(dao);
            }

        }

        return averageRating;
    }
}
