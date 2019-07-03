package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.ReviewDao;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

public class AverageRatingService extends AbstractService {
    @Override
    public Double find(Object... values) throws ConnectionException,
            DaoException,
            IncorrectDataException {
        String idGame = (String) values[0];
        if (idGame == null || !NumberValidator.validate(idGame)) {
            throw new IncorrectDataException("idGame has invalid value");
        }
        double averageRating = 0;

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            averageRating = dao.findAverageValue(idGame);
        } finally {
            returnConnection(dao);
        }
        return averageRating;
    }
}