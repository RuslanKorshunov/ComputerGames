package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.EntityConst;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.AuthorizationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(AuthorizationCommand.class);

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

            session.setAttribute(RequestConst.YEAR_FROM.getValue(), EntityConst.DEFAULT_YEAR_FROM);
            session.setAttribute(RequestConst.YEAR_TO.getValue(), EntityConst.DEFAULT_YEAR_TO);
            session.setAttribute(RequestConst.GENRE.getValue(), EntityConst.DEFAULT_ID_GENRE);
            session.setAttribute(RequestConst.DEVELOPER.getValue(), EntityConst.DEFAULT_ID_DEVELOPER);
            Page page = Page.MAIN_PAGE;
            router.setTarget(page.getPath());
            logger.info(user.getRole()+" "+user.getName()+" "+user.getSurname()+" with login "+user.getLogin()+" logs in system.");
        }
        catch (IncorrectDataException|CryptologistException|DAOException|ConnectionException e)
        {
            logger.error(e);
            Page page = Page.AUTHORIZATION_PAGE;
            router.setTarget(page.getPath());
        }
        return router;
    }
}