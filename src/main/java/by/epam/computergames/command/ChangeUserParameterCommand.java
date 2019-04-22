package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.ChangeUserParameterService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserParameterCommand implements AbstractCommand
{
    private ChangeUserParameterService service;

    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException, IncorrectDataException
    {
        Router router=new Router();

        return router;
    }
}