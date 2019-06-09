package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Mark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MarkDAO extends AbstractDAO<Mark>
{
    private static final String SELECT_MARKS_QUERY="select * from marks where " +
                                                    "idGame=? " +
                                                    "and login=?";
    private static final String UPDATE_MARKS_QUERY="update marks set mark=? " +
                                                    "where idGame=? " +
                                                    "and login=?";
    private static final String INSERT_INTO_MARKS_QUERY="insert into marks values (?, ?, ?)";
    private static final String AVG_QUERY="select round(avg(mark),1) from marks where idGame=?";

    public MarkDAO() throws ConnectionException {
        super();
    }

    @Override
    public void create(Mark entity) throws DAOException
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
                statement.setLong(2, entity.getIdGame());
                statement.setString(3, entity.getLogin());
                statement.executeUpdate();
            }
            else
            {
                statement=connection.prepareStatement(INSERT_INTO_MARKS_QUERY);
                statement.setLong(1, entity.getIdGame());
                statement.setString(2, entity.getLogin());
                statement.setInt(3, entity.getMark());
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            //TODO LOG
            throw new DAOException("MarkDAO can't add data in database due to an internal error.", e);
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
            throw new DAOException("MarkDAO can't match average rating due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
        return averageMark;
    }

    @Override
    public Mark findBy(String id) throws DAOException {
        return null;
    }

    @Override
    public List<Mark> find(Object... values) throws DAOException {
        return null;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DAOException {
    }

    @Override
    public long findSize() throws DAOException {
        return 0;
    }
}
