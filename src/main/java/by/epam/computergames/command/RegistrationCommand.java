package by.epam.computergames.command;

import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ServiceException;
import by.epam.computergames.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {

        Router router = new Router();
        String login = request.getParameter(RequestParameter.LOGIN.getValue());
        String password = request.getParameter(RequestParameter.PASSWORD.getValue());
        String email = request.getParameter(RequestParameter.EMAIL.getValue());
        String name = request.getParameter(RequestParameter.NAME.getValue());
        String surname = request.getParameter(RequestParameter.SURNAME.getValue());
        String sex = request.getParameter(RequestParameter.SEX.getValue());
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        if (sex == null) {
            user.setSex(Sex.THIRD);
        } else {
            user.setSex(Sex.valueOf(sex.toUpperCase()));
        }

        AbstractService service = new UserService();
        try {
            service.add(user);
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);

            HttpSession session = request.getSession();
            session.setAttribute(RequestParameter.LOGIN.getValue(), login);
            session.setAttribute(RequestParameter.ROLE.getValue(), user.getRole());
            session.setAttribute(RequestParameter.NAME.getValue(), user.getName());
            session.setAttribute(RequestParameter.SURNAME.getValue(), user.getSurname());
        } catch (ServiceException e) {
            logger.error(e);
            PageName pageName = PageName.REGISTRATION_PAGE;
            router.setTarget(pageName);
        }

        return router;
    }
}