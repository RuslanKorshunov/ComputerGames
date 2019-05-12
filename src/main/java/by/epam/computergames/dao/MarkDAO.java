package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Mark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarkDAO extends AbstractDAO<Mark>
{
    public MarkDAO() throws ConnectionException {
        super();
    }

    @Override
    public void create(Mark entity) throws DAOException
    {
        PreparedStatement statement=null;
        try
        {
            String query="select * from marks where " +
                    "idGame="+entity.getIdGame()+" " +
                    "and login='"+entity.getLogin()+"'";
            statement=connection.prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            if(rs.next())
            {
                query="update marks set mark="+entity.getMark()+" " +
                        "where idGame="+entity.getIdGame()+" " +
                        "and login='"+entity.getLogin()+"'";
                statement=connection.prepareStatement(query);
                statement.executeUpdate();
            }
            else
            {
                query="insert into marks values ("+entity.getIdGame()+
                        ", '"+entity.getLogin()+
                        "', "+entity.getMark()+")";
                statement=connection.prepareStatement(query);
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            //TODO LOG
            throw new DAOException("MarkDAO can't add data in database due to an internal error.");
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
            final String QUERY="select round(avg(mark),1) from marks where idGame="+id;
            statement=connection.prepareStatement(QUERY);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                averageMark=rs.getDouble(1);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("MarkDAO can't match average rating due to an internal error.");
        }
        finally
        {
            closeStatement(statement);
        }
        return averageMark;
    }
}
