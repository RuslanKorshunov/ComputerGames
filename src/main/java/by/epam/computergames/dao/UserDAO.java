package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.User;
import by.epam.computergames.entity.UserEnum;
import by.epam.computergames.exception.IncorrectDataException;

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
    public User findById(String login) throws ConnectionException, IncorrectDataException
    {
        User user=new User();
        PreparedStatement statement=null;
        try
        {
            final String query="SELECT password, type FROM users WHERE login='"+login+"'";
            statement=connection.prepareStatement(query);//todo насколько это обоснованно?
            ResultSet rs=statement.executeQuery(query);
            while (rs.next())
            {
                user.setLogin(login);
                String password=rs.getString(1);
                user.setPassword(password);
                UserEnum userType;
                switch (rs.getInt(2))
                {
                    case 1:
                        userType=UserEnum.ADMIN;
                        break;
                    case 2:
                        userType=UserEnum.USER;
                        default:
                            throw new IncorrectDataException("Data in database has invalid value.");
                }
                user.setType(userType);
            }
        }
        catch(SQLException e)
        {
            throw new ConnectionException("");//TODO может, придумать другое исключение???
        }
        finally
        {
            closeStatement(statement);
        }
        return user;
    }

    @Override
    public void returnConnection() throws IncorrectDataException, ConnectionException {
        super.returnConnection();
    }
}