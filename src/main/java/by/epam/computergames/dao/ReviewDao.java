package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao extends AbstractDao<Review> {
    private static final int NUMBER_OF_ENTITIES = 2;
    private static final String SELECT_MARKS_QUERY = "select mark, comment from reviews where " +
            "idGame=? " +
            "and login=?";
    private static final String SELECT_REVIEWS_QUERY = "select userinfo.login, userinfo.name as name, " +
            "userinfo.surname as surname, " +
            "mark, comment from reviews " +
            "join userinfo on userinfo.login=reviews.login " +
            "where idGame=? limit ?, ?";
    private static final String UPDATE_MARKS_QUERY = "update reviews set mark=?, comment=? " +
            "where idGame=? " +
            "and login=?";
    private static final String INSERT_INTO_MARKS_QUERY = "insert into reviews values (?, ?, ?, ?)";
    private static final String AVG_QUERY = "select round(avg(mark),1) from reviews where idGame=?";
    private static final String FIND_SIZE_QUERY = "select count(idGame) from reviews where idGame=?";
    private static final String DELETE_QUERY = "delete from reviews where idGame=? and login=?";

    public ReviewDao() throws ConnectionException {
        super();
    }

    @Override
    public void create(Review entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_MARKS_QUERY);
            statement.setString(1, entity.getIdGame());
            statement.setString(2, entity.getLogin());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                statement = connection.prepareStatement(UPDATE_MARKS_QUERY);
                statement.setString(1, entity.getMark());
                statement.setString(2, entity.getComment());
                statement.setString(3, entity.getIdGame());
                statement.setString(4, entity.getLogin());
                statement.executeUpdate();
            } else {
                statement = connection.prepareStatement(INSERT_INTO_MARKS_QUERY);
                statement.setString(1, entity.getIdGame());
                statement.setString(2, entity.getLogin());
                statement.setString(3, entity.getMark());
                statement.setString(4, entity.getComment());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("ReviewDao can't add data in database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public double findAverageValue(Object... values) throws DaoException {
        String idGame = (String) values[0];
        double averageMark = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(AVG_QUERY);
            statement.setString(1, idGame);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                averageMark = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new DaoException("ReviewDao can't match average rating due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
        return averageMark;
    }

    @Override
    public Review findBy(Object... values) throws DaoException {
        String idGame = (String) values[0];
        String login = (String) values[1];
        Review review = new Review();
        review.setIdGame(idGame);
        review.setLogin(login);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_MARKS_QUERY);
            statement.setString(1, idGame);
            statement.setString(2, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String mark = rs.getString(1);
                review.setMark(mark);
                String comment = rs.getString(2);
                review.setComment(comment);
            }
        } catch (SQLException e) {
            throw new DaoException("ReviewDao can't read from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }

        return review;
    }

    @Override
    public List<Review> find(Object... values) throws DaoException {
        String idGame = (String) values[0];
        long first = (long) values[1];
        long last = first + NUMBER_OF_ENTITIES;

        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_REVIEWS_QUERY);
            statement.setString(1, idGame);
            statement.setLong(2, first);
            statement.setLong(3, last);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setIdGame(idGame);
                String login = rs.getString(1);
                review.setLogin(login);
                String name = rs.getString(2);
                review.setName(name);
                String surname = rs.getString(3);
                review.setSurname(surname);
                String mark = rs.getString(4);
                review.setMark(mark);
                String comment = rs.getString(5);
                review.setComment(comment);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException("ReviewDao can't read from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
        return reviews;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DaoException {
    }

    @Override
    public long findSize(Object... values) throws DaoException {
        String idGame = (String) values[0];
        long size = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_SIZE_QUERY);
            statement.setString(1, idGame);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                size = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("GameDao can't find size from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
        return size;
    }

    @Override
    public void delete(Object... values) throws DaoException {
        Review review = (Review) values[0];
        String idGame = review.getIdGame();
        String login = review.getLogin();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, idGame);
            statement.setString(2, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("ReviewDao can't delete review from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
    }
}