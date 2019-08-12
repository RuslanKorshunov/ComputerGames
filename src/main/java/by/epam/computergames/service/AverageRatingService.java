package by.epam.computergames.service;

import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.ReviewDao;
import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

public class AverageRatingService extends AbstractService {
    @Override
    public Double find(Object... values) throws ServiceException {
        Game game = (Game) values[0];
        double averageRating;
        try {
            if (game == null) {
                throw new IncorrectDataException("game can't be null");
            }
            String idGame = game.getIdGame();
            if (idGame == null || !NumberValidator.validate(idGame)) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
            }
            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                averageRating = dao.findAverageValue(idGame);
            } finally {
                dao.close();
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
        return averageRating;
    }
}