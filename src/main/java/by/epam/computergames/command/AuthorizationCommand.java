package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationCommand implements AbstractCommand
{
    private static final String LOGIN="login";
    private static final String PASSWORD="password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ConnectionException,
                                                                                            IncorrectDataException
    {
        String login=request.getParameter(LOGIN);
        String password=request.getParameter(PASSWORD);

        AbstractDAO dao=new UserDAO();
        User user=(User)dao.findById(login);
    }
}