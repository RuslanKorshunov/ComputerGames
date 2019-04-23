package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeUserParameterDAO extends AbstractDAO
{
    public ChangeUserParameterDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public void update(String tableName, String column, String newValue, String login) throws DAOException
    {
        PreparedStatement statement=null;
        try
        {
            final String QUERY="update "+tableName+" set "+column+"='"+newValue+"' where login='"+login+"'";
            statement=connection.prepareStatement(QUERY);
        }
        catch(SQLException e)
        {
            throw new DAOException("ChangeUserParameterDAO can't update data in database due to an internal error.");
        }
        finally
        {
            closeStatement(statement);
        }
    }
}