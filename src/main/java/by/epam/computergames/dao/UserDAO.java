package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User>
{
    public UserDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public User findBy(String login) throws DAOException
    {
        User user=new User();
        PreparedStatement statement=null;
        try
        {
            final String QUERY="SELECT users.password, users.type, userinfo.name, userinfo.surname FROM users JOIN userinfo ON users.login=userinfo.login WHERE users.login='"+login+"'";
            statement=connection.prepareStatement(QUERY);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                user.setLogin(login);
                String password=rs.getString(1);
                user.setPassword(password);
                switch (rs.getInt(2))
                {
                    case 1:
                        user.setRole(Role.ADMIN);
                        break;
                    case 2:
                        user.setRole(Role.USER);
                        default:
                            throw new DAOException("Data in database has invalid value.");
                }
                String name=rs.getString(3);
                user.setName(name);
                String surname=rs.getString(4);
                user.setSurname(surname);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("UserDAO can't get data from database due to an internal error.");
        }
        finally
        {
            closeStatement(statement);
        }
        return user;
    }

    @Override
    public void returnConnection() throws ConnectionException {
        super.returnConnection();
    }
}