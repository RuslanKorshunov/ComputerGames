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
    private User user;

    public boolean checkUser(String login, String password) throws ConnectionException,
                                                                    IncorrectDataException
    {
        boolean result=false;
        result=LoginValidator.validate(login);
        result=PasswordValidator.validate(password);
        if(LoginValidator.validate(login) && PasswordValidator.validate(password))
        {
            AbstractDAO dao=new UserDAO();
            user=(User)dao.findById(login);
            dao.returnConnection();

            if(user.getLogin()!=null && user.getPassword().equals(password))
            {
                result=true;
            }
        }
        return result;
    }

    public User getUser()//TODO ???
    {
        return user;
    }
}
