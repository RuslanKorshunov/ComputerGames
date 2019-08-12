package by.epam.computergames.service;

import by.epam.computergames.command.CommandName;
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
    public Review find(Object... values) throws ServiceException {
        Review review = (Review) values[0];

        try {
            String idGame = review.getIdGame();
            String login = review.getLogin();
            if (idGame == null || !NumberValidator.validate(idGame)) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
            }

            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                review = ((ReviewDao) dao).findBy(idGame, login);
            } finally {
                dao.close();
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }

        return review;
    }

    @Override
    public void add(Review review) throws ServiceException {
        String idGame = review.getIdGame();

        try {
            if (idGame == null || !NumberValidator.validate(idGame)) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
            }
            String login = review.getLogin();
            if (!LoginValidator.validate(login)) {
                throw new IncorrectDataException("login has invalid value " + "\"" + login + "\"");
            }
            String mark = review.getMark();
            if (!NumberValidator.validate(mark)) {
                throw new IncorrectDataException("mark has invalid value " + "\"" + mark + "\"");
            }
            int markInt = Integer.parseInt(mark);
            if (markInt >= 1 && markInt <= 10) {
                AbstractDao dao = null;
                try {
                    dao = new ReviewDao();
                    dao.create(review);
                } finally {
                    dao.close();
                }
            } else {
                throw new IncorrectDataException("entity can't be less than 0 and more than 10.");
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> findAll(Object... values) throws ServiceException {
        ReviewParameter parameter = (ReviewParameter) values[0];
        List<Review> reviews;

        try {
            if (parameter.getIdGame() == null || !NumberValidator.validate(parameter.getIdGame())) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + parameter.getIdGame() + "\"");
            }
            if (parameter.getPageNumber() == null || !NumberValidator.validate(parameter.getPageNumber())) {
                throw new IncorrectDataException("pageNumber has invalid value " + "\"" + parameter.getPageNumber() + "\"");
            }
            int pageNumber = checkCommand(parameter);

            long first = pageNumber * NUMBER_OF_ENTITIES;
            long size = findSize(parameter.getIdGame());
            if (first >= size && pageNumber != FIRST_PAGE_INDEX) {
                pageNumber--;
                first = pageNumber * NUMBER_OF_ENTITIES;
            }
            parameter.setPageNumber(pageNumber);


            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                reviews = dao.find(parameter.getIdGame(), first);
            } finally {
                dao.close();
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }

        return reviews;
    }

    @Override
    public void delete(Review review) throws ServiceException {
        try {
            AbstractDao dao = null;
            try {
                dao = new ReviewDao();
                dao.delete(review);
            } finally {
                dao.close();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private long findSize(String idGame) throws DaoException {
        Long size;

        AbstractDao dao = null;
        try {
            dao = new ReviewDao();
            size = dao.findSize(idGame);
        } finally {
            dao.close();
        }

        return size;
    }

    private int checkCommand(ReviewParameter reviewParameter) {
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