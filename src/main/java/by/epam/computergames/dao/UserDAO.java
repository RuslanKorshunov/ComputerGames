package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.User;
import by.epam.computergames.entity.UserEnum;
import by.epam.computergames.exception.IncorrectDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try
        {
            Statement statement=connection.createStatement();
            String query="SELECT password, type FROM users WHERE login='"+login+"'";
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
            System.out.println(e);
            throw new ConnectionException("");//TODO может, придумать другое исключение???
        }
        return user;
    }

    @Override
    public void returnConnection() throws IncorrectDataException {
        super.returnConnection();
    }
}
