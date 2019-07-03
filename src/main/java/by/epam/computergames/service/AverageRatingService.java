package by.epam.computergames.service;

import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.ReviewDao;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

public class AverageRatingService extends AbstractService {
    @Override
    public Double find(Object... values) throws ServiceException {
        String idGame = (String) values[0];
        double averageRating = 0;
        try {
            if (idGame == null || !NumberValidator.validate(idGame)) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
            }
            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                averageRating = dao.findAverageValue(idGame);
            } finally {
                returnConnection(dao);
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
        return averageRating;
    }
}