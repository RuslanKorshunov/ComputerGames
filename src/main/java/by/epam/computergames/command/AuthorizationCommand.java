package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router = new Router();

        String login = request.getParameter(RequestConst.LOGIN.getValue());
        String password = request.getParameter(RequestConst.PASSWORD.getValue());

        AbstractService service = new AuthorizationService();
        try
        {
            User user = (User)service.find(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(RequestConst.LOGIN.getValue(), login);
            session.setAttribute(RequestConst.ROLE.getValue(), user.getRole());
            session.setAttribute(RequestConst.NAME.getValue(), user.getName());
            session.setAttribute(RequestConst.SURNAME.getValue(), user.getSurname());
            Page page = Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        catch (IncorrectDataException|CryptologistException|DAOException|ConnectionException e)
        {
            Page page = Page.LOGIN_PAGE;
            router.setTarget(page.getPath());
        }
        return router;
    }
}