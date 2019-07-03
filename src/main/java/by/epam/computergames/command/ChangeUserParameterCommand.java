package by.epam.computergames.command;

import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ServiceException;
import by.epam.computergames.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeUserParameterCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(ChangeUserParameterCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();

        String login = (String) session.getAttribute(RequestParameter.LOGIN.getValue());
        String commandString = request.getParameter(RequestParameter.COMMAND.getValue());

        AbstractCommand getUserInfoCommand = new GetUserInfoCommand();
        try {
            CommandName command = CommandName.valueOf(commandString.toUpperCase());
            String newValue = null;
            switch (command) {
                case CHANGE_NAME:
                    newValue = request.getParameter(RequestParameter.NEW_NAME_FORM.getValue());
                    break;
                case CHANGE_SURNAME:
                    newValue = request.getParameter(RequestParameter.NEW_SURNAME_FORM.getValue());
                    break;
                case CHANGE_PASSWORD:
                    newValue = request.getParameter(RequestParameter.NEW_PASSWORD_FORM.getValue());
                    break;
                case CHANGE_SEX:
                    newValue = request.getParameter(RequestParameter.NEW_SEX_FORM.getValue());
                    break;
                case CHANGE_EMAIL:
                    newValue = request.getParameter(RequestParameter.NEW_EMAIL_FORM.getValue());
            }

            AbstractService service = new UserService();
            service.change(login, command, newValue);
            switch (command) {
                case CHANGE_NAME:
                    session.setAttribute(RequestParameter.NAME.getValue(), newValue);
                    break;
                case CHANGE_SURNAME:
                    session.setAttribute(RequestParameter.SURNAME.getValue(), newValue);
                    break;
            }
            router = getUserInfoCommand.execute(request);
        } catch (ServiceException e) {
            logger.error(e);
            router = getUserInfoCommand.execute(request);
        }

        return router;
    }
}