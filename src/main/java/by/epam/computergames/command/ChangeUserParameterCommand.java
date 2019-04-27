package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.ChangeUserParameterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeUserParameterCommand implements AbstractCommand
{
    private ChangeUserParameterService service;

    @Override
    public Router execute(HttpServletRequest request) throws DAOException,
                                                                ConnectionException
    {
        HttpSession session=request.getSession();
        String login=(String) session.getAttribute(ConstEnum.LOGIN.getValue());
        String commandString=request.getParameter(ConstEnum.COMMAND.getValue());
        CommandEnum command=CommandEnum.valueOf(commandString.toUpperCase());//TODO нужно ли это проверять?
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

        AbstractCommand getUserInfoCommand=new GetUserInfoCommand();
        Router router;
        service=new ChangeUserParameterService();
        try
        {
            service.change(login, command, newValue);
            router=getUserInfoCommand.execute(request);
        }
        catch (IncorrectDataException e)
        {
            router=getUserInfoCommand.execute(request);
        }

        return router;
    }
}