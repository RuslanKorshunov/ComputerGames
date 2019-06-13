package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.ReviewDAO;
import by.epam.computergames.entity.Review;
import by.epam.computergames.exception.IncorrectDataException;

public class ReviewService extends AbstractService<Review>
{
    @Override
    public Review find(Object... values) throws ConnectionException, DAOException
    {
        Review review=(Review)values[0];

        long idGame=review.getIdGame();
        String login=review.getLogin();

        AbstractDAO dao=null;
        try
        {
            dao=new ReviewDAO();
            review=((ReviewDAO) dao).findBy(idGame, login);
        }
        finally
        {
            returnConnection(dao);
        }

        return review;
    }

    @Override
    public void add(Review entity) throws IncorrectDataException, ConnectionException, DAOException
    {
        int mark=entity.getMark();
        if(mark>=1 && mark<=10)
        {
            AbstractDAO dao=null;
            try
            {
                dao=new ReviewDAO();
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
