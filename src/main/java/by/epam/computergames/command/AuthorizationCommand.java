package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.entity.EntityConst;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String login = request.getParameter(RequestParameter.LOGIN.getValue());
        String password = request.getParameter(RequestParameter.PASSWORD.getValue());

        AbstractService service = new UserService();
        try {
            User user = (User) service.find(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(RequestParameter.LOGIN.getValue(), login);
            session.setAttribute(RequestParameter.ROLE.getValue(), user.getRole().getId());
            session.setAttribute(RequestParameter.NAME.getValue(), user.getName());
            session.setAttribute(RequestParameter.SURNAME.getValue(), user.getSurname());
            session.setAttribute(RequestParameter.SEX.getValue(), user.getSex().getValue());
            session.setAttribute(RequestParameter.EMAIL.getValue(), user.getEmail());

            session.setAttribute(RequestParameter.YEAR_FROM.getValue(), EntityConst.DEFAULT_YEAR_FROM);
            session.setAttribute(RequestParameter.YEAR_TO.getValue(), EntityConst.DEFAULT_YEAR_TO);
            session.setAttribute(RequestParameter.GENRE.getValue(), EntityConst.DEFAULT_ID_GENRE);
            session.setAttribute(RequestParameter.DEVELOPER.getValue(), EntityConst.DEFAULT_ID_DEVELOPER);
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
            logger.info(user.getRole() + " " + user.getName() + " " + user.getSurname() + " with login " + user.getLogin() + " logs in system.");
        } catch (IncorrectDataException | CryptologistException | DaoException | ConnectionException e) {
            logger.error(e);
            PageName pageName = PageName.AUTHORIZATION_PAGE;
            router.setTarget(pageName);
        }
        return router;
    }
}