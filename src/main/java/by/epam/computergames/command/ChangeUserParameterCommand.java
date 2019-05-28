package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ChangeUserParameterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeUserParameterCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router;
        HttpSession session=request.getSession();
        GetUserInfoCommand getUserInfoCommand=new GetUserInfoCommand();
        try
        {
            String login=(String) session.getAttribute(ConstEnum.LOGIN.getValue());
            String commandString=request.getParameter(ConstEnum.COMMAND.getValue());
            CommandEnum command=CommandEnum.valueOf(commandString.toUpperCase());
            String newValue=null;
            switch (command)
            {
                case CHANGE_NAME:
                    newValue=request.getParameter(ConstEnum.NEW_NAME_FORM.getValue());
                    break;
                case CHANGE_SURNAME:
                    newValue=request.getParameter(ConstEnum.NEW_SURNAME_FORM.getValue());
                    break;
                case CHANGE_PASSWORD:
                    newValue=request.getParameter(ConstEnum.NEW_PASSWORD_FORM.getValue());
                    break;
                case CHANGE_SEX:
                    newValue=request.getParameter(ConstEnum.NEW_SEX_FORM.getValue());
                    break;
                case CHANGE_EMAIL:
                    newValue=request.getParameter(ConstEnum.NEW_EMAIL_FORM.getValue());
            }

            AbstractService service=new ChangeUserParameterService();
            service.change(login, command, newValue);
            switch (command)
            {
                case CHANGE_NAME:
                    session.setAttribute(ConstEnum.NAME.getValue(), newValue);
                    break;
                case CHANGE_SURNAME:
                    session.setAttribute(ConstEnum.SURNAME.getValue(), newValue);
                    break;
            }
            router=getUserInfoCommand.execute(request);
        }
        catch (IncorrectDataException|DAOException|ConnectionException e)
        {
            //todo лог
            router=getUserInfoCommand.execute(request);
        }

        return router;
    }
}