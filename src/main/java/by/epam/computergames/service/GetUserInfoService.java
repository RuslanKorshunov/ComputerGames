package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;

public class GetUserInfoService
{
    public User find(String login, Role role) throws ConnectionException,
                                                        DAOException
    {
        User user=new User();
        user.setLogin(login);
        if(role == Role.GUEST)
        {
            user.setRole(role);
        }
        else
        {
            AbstractDAO dao=new UserDAO();
            user=(User) dao.findBy(login);
            dao.returnConnection();
        }
        return user;
    }
}