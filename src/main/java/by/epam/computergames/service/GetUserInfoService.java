package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;

public class GetUserInfoService extends AbstractService<User>
{
    @Override
    public User find(Object ... values) throws ConnectionException,
                                                        DAOException
    {
        String login=(String) values[0];
        Role role=(Role) values[1];
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