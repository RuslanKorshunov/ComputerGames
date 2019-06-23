package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.ReviewDAO;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

public class AverageRatingService extends AbstractService
{
    @Override
    public Double find(Object... values) throws ConnectionException,
                                                DAOException,
                                                IncorrectDataException
    {
        String idGame=(String) values[0];
        if(idGame==null || !NumberValidator.validate(idGame))
        {
            throw new IncorrectDataException("idGame has invalid value");
        }
        double averageRating=0;

        AbstractDAO dao=null;
        try
        {
            dao=new ReviewDAO();
            averageRating=dao.findAverageValue(idGame);
        }
        finally
        {
            returnConnection(dao);
        }
        return averageRating;
    }
}
