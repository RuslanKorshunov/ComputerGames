package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException, IncorrectDataException//TODO здесь должен быть уровень логики
    {
        Router router=new Router();
        PageEnum page=PageEnum.LOGIN_PAGE;
        router.setTarget(page.getPath());
        router.setRedirect();
        return router;
    }
}