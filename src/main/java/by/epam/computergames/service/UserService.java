package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.LoginValidator;
import by.epam.computergames.validator.PasswordValidator;

public class UserService
{
    public User find(String login, String password) throws IncorrectDataException,
                                                            ConnectionException,
                                                            DAOException
    {
        if(!LoginValidator.validate(login))
        {
            throw new IncorrectDataException("login has invalid value.");
        }
        if(!PasswordValidator.validate(password))
        {
            throw new IncorrectDataException("password has invalid value.");
        }
        AbstractDAO dao=new UserDAO();
        User user=(User)dao.findBy(login);
        dao.returnConnection();
        return user;
    }
}