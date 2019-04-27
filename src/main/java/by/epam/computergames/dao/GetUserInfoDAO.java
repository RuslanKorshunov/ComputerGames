package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserInfoDAO extends AbstractDAO<User>
{
    public GetUserInfoDAO() throws ConnectionException
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
            final String QUERY="select users.login, users.password, " +
                    "users.type, userinfo.name, " +
                    "userinfo.surname, userinfo.sex, " +
                    "userinfo.email " +
                    "from userinfo join users " +
                    "on users.login=userinfo.login where users.login='"+login+"'";
            statement=connection.prepareStatement(QUERY);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                user.setLogin(login);
                String password=rs.getString(2);
                user.setPassword(password);
                switch (rs.getInt(3))
                {
                    case 1:
                        user.setRole(Role.ADMIN);
                        break;
                    case 2:
                        user.setRole(Role.USER);
                    default:
                        throw new DAOException("Data in database has invalid value.");
                }
                String name=rs.getString(4);
                user.setName(name);
                String surname=rs.getString(5);
                user.setSurname(surname);
                switch (rs.getString(6))
                {
                    case "male":
                        user.setSex(Sex.MALE);
                        break;
                    case "female":
                        user.setSex(Sex.FEMALE);
                        break;
                    case "third":
                        user.setSex(Sex.THIRD);
                        break;
                    default:
                        throw new DAOException("Data in database has invalid value.");
                }
                String email=rs.getString(7);
                user.setEmail(email);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("GetUserInfoDAO can't get data from database due to an internal error.");
        }
        finally
        {
            closeStatement(statement);
        }
        return user;
    }
}