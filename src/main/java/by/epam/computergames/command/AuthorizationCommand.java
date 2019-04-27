package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand
{
    private UserService userService;

    @Override
    public Router execute(HttpServletRequest request) throws DAOException, ConnectionException
    {
        Router router = new Router();

        String login = request.getParameter(ConstEnum.LOGIN.getValue());
        String password = request.getParameter(ConstEnum.PASSWORD.getValue());

        userService = new UserService();
        try
        {
            User user = userService.find(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(ConstEnum.LOGIN.getValue(), login);
            session.setAttribute(ConstEnum.ROLE.getValue(), user.getRole());
            session.setAttribute(ConstEnum.NAME.getValue(), user.getName());
            session.setAttribute(ConstEnum.SURNAME.getValue(), user.getSurname());
            PageEnum page = PageEnum.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        catch (IncorrectDataException e)
        {
            PageEnum page = PageEnum.LOGIN_PAGE;
            router.setTarget(page.getPath());
        }
        return router;
    }
}