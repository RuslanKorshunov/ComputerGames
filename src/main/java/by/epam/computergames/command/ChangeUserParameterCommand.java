package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeUserParameterCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(ChangeUserParameterCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router;
        HttpSession session=request.getSession();

        String login=(String) session.getAttribute(RequestConst.LOGIN.getValue());
        String commandString=request.getParameter(RequestConst.COMMAND.getValue());

        AbstractCommand getUserInfoCommand=new GetUserInfoCommand();
        try
        {
            CommandConst command= CommandConst.valueOf(commandString.toUpperCase());
            String newValue=null;
            switch (command)
            {
                case CHANGE_NAME:
                    newValue=request.getParameter(RequestConst.NEW_NAME_FORM.getValue());
                    break;
                case CHANGE_SURNAME:
                    newValue=request.getParameter(RequestConst.NEW_SURNAME_FORM.getValue());
                    break;
                case CHANGE_PASSWORD:
                    newValue=request.getParameter(RequestConst.NEW_PASSWORD_FORM.getValue());
                    break;
                case CHANGE_SEX:
                    newValue=request.getParameter(RequestConst.NEW_SEX_FORM.getValue());
                    break;
                case CHANGE_EMAIL:
                    newValue=request.getParameter(RequestConst.NEW_EMAIL_FORM.getValue());
            }

            AbstractService service=new UserService();
            service.change(login, command, newValue);
            switch (command)
            {
                case CHANGE_NAME:
                    session.setAttribute(RequestConst.NAME.getValue(), newValue);
                    break;
                case CHANGE_SURNAME:
                    session.setAttribute(RequestConst.SURNAME.getValue(), newValue);
                    break;
            }
            router=getUserInfoCommand.execute(request);
        }
        catch (IncorrectDataException|DAOException|ConnectionException e)
        {
            logger.error(e);
            router=getUserInfoCommand.execute(request);
        }

        return router;
    }
}