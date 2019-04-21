package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.LoginValidator;
import by.epam.computergames.validator.PasswordValidator;

public class UserService
{
    public User find(String login, String password) throws ConnectionException,
                                                                    IncorrectDataException
    {
        User user=null;
        if(LoginValidator.validate(login) && PasswordValidator.validate(password))
        {
            AbstractDAO dao=new UserDAO();
            user=(User)dao.findBy(login);
            dao.returnConnection();
        }
        return user;
    }
}