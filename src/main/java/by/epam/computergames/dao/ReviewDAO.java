package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReviewDAO extends AbstractDAO<Review>
{
    private static final String SELECT_MARKS_QUERY="select mark, comment from reviews where " +
                                                    "idGame=? " +
                                                    "and login=?";
    private static final String UPDATE_MARKS_QUERY="update reviews set mark=?, comment=? " +
                                                    "where idGame=? " +
                                                    "and login=?";
    private static final String INSERT_INTO_MARKS_QUERY="insert into reviews values (?, ?, ?, ?)";
    private static final String AVG_QUERY="select round(avg(mark),1) from reviews where idGame=?";

    public ReviewDAO() throws ConnectionException {
        super();
    }

    @Override
    public void create(Review entity) throws DAOException
    {
        PreparedStatement statement=null;
        try
        {
            statement=connection.prepareStatement(SELECT_MARKS_QUERY);
            statement.setLong(1, entity.getIdGame());
            statement.setString(2, entity.getLogin());
            ResultSet rs=statement.executeQuery();
            if(rs.next())
            {
                statement=connection.prepareStatement(UPDATE_MARKS_QUERY);
                statement.setInt(1, entity.getMark());
                statement.setString(2, entity.getComment());
                statement.setLong(3, entity.getIdGame());
                statement.setString(4, entity.getLogin());
                statement.executeUpdate();
            }
            else
            {
                statement=connection.prepareStatement(INSERT_INTO_MARKS_QUERY);
                statement.setLong(1, entity.getIdGame());
                statement.setString(2, entity.getLogin());
                statement.setInt(3, entity.getMark());
                statement.setString(4, entity.getComment());
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            //TODO LOG
            throw new DAOException("ReviewDAO can't add data in database due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
    }

    @Override
    public double findAverageValue(long id) throws DAOException
    {
        double averageMark=0;
        PreparedStatement statement=null;
        try
        {
            statement=connection.prepareStatement(AVG_QUERY);
            statement.setLong(1, id);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                averageMark=rs.getDouble(1);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("ReviewDAO can't match average rating due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
        return averageMark;
    }

    @Override
    public Review findBy(Object... values) throws DAOException
    {
        long idGame=(long)values[0];
        String login=(String)values[1];
        Review review=new Review();
        review.setIdGame(idGame);
        review.setLogin(login);
        PreparedStatement statement=null;
        try
        {
            statement=connection.prepareStatement(SELECT_MARKS_QUERY);
            statement.setLong(1, idGame);
            statement.setString(2, login);
            ResultSet rs=statement.executeQuery();
            if(rs.next())
            {
                int mark=rs.getInt(1);
                review.setMark(mark);
                String comment=rs.getString(2);
                review.setComments(comment);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("ReviewDao can't read from database due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }

        return review;
    }

    @Override
    public List<Review> find(Object... values) throws DAOException {
        return null;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DAOException {
    }

    @Override
    public long findSize(Object... values) throws DAOException {
        return 0;
    }
}
