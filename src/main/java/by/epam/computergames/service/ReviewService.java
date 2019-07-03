package by.epam.computergames.service;

import by.epam.computergames.command.CommandName;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.ReviewDao;
import by.epam.computergames.entity.Review;
import by.epam.computergames.entity.ReviewParameter;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.LoginValidator;
import by.epam.computergames.validator.NumberValidator;

import java.util.List;

public class ReviewService extends AbstractService<Review> {
    private static final int NUMBER_OF_ENTITIES = 2;
    private static final int FIRST_PAGE_INDEX = 0;

    @Override
    public Review find(Object... values) throws ConnectionException, DaoException, IncorrectDataException {
        Review review = (Review) values[0];

        String idGame = review.getIdGame();
        String login = review.getLogin();
        if (idGame == null || !NumberValidator.validate(idGame)) {
            throw new IncorrectDataException("idGame has invalid value.");
        }

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            review = ((ReviewDao) dao).findBy(idGame, login);
        } finally {
            returnConnection(dao);
        }

        return review;
    }

    @Override
    public void add(Review entity) throws IncorrectDataException, ConnectionException, DaoException {
        String idGame = entity.getIdGame();
        if (idGame == null || !NumberValidator.validate(idGame)) {
            throw new IncorrectDataException("idGame " + idGame + " has invalid value.");
        }
        String login = entity.getLogin();
        if (!LoginValidator.validate(login)) {
            throw new IncorrectDataException("login " + login + " has invalid value.");
        }
        String mark = entity.getMark();
        if (!NumberValidator.validate(mark)) {
            throw new IncorrectDataException("mark " + mark + " has invalid value.");
        }
        int markInt = Integer.parseInt(mark);
        if (markInt >= 1 && markInt <= 10) {
            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                dao.create(entity);
            } finally {
                returnConnection(dao);
            }
        } else {
            throw new IncorrectDataException("entity can't be less than 0 and more than 10.");
        }
    }

    @Override
    public List<Review> findAll(Object... values) throws IncorrectDataException, ConnectionException, DaoException {
        ReviewParameter parameter = (ReviewParameter) values[0];
        if (parameter.getIdGame() == null || !NumberValidator.validate(parameter.getIdGame())) {
            throw new IncorrectDataException("idGame has invalid value.");
        }
        if (parameter.getPageNumber() == null || !NumberValidator.validate(parameter.getPageNumber())) {
            throw new IncorrectDataException("pageNumber has invalid value.");
        }
        int pageNumber = checkCommand(parameter);

        long first = pageNumber * NUMBER_OF_ENTITIES;
        long size = findSize(parameter.getIdGame());
        if (first >= size && pageNumber != FIRST_PAGE_INDEX) {
            pageNumber--;
            first = pageNumber * NUMBER_OF_ENTITIES;
        }
        parameter.setPageNumber(pageNumber);
        List<Review> reviews;

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            reviews = dao.find(parameter.getIdGame(), first);
        } finally {
            returnConnection(dao);
        }

        return reviews;
    }

    @Override
    public void delete(Object... values) throws DaoException, ConnectionException {
        Review review = (Review) values[0];

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            dao.delete(review);
        } finally {
            returnConnection(dao);
        }
    }

    private long findSize(String idGame) throws ConnectionException, DaoException {
        Long size;

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            size = dao.findSize(idGame);
        } finally {
            returnConnection(dao);
        }

        return size;
    }

    private int checkCommand(ReviewParameter reviewParameter){//todo может, вынести в отдельный класс
        String command = reviewParameter.getCommand();
        int pageNumber = Integer.parseInt(reviewParameter.getPageNumber());
        if (command.equals(CommandName.FORWARD_REVIEWS.getValue())) {
            pageNumber++;
        }
        if (command.equals(CommandName.BACKWARD_REVIEWS.getValue()) && pageNumber != FIRST_PAGE_INDEX) {
            pageNumber--;
        }
        return pageNumber;
    }
}