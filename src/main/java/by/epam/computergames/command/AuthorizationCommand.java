package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand
{
    private UserService userService;

    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException, IncorrectDataException
    {
        Router router=new Router();

        ConstEnum constEnum=ConstEnum.LOGIN;
        String login=request.getParameter(constEnum.getValue());
        constEnum=ConstEnum.PASSWORD;
        String password=request.getParameter(constEnum.getValue());

        userService=new UserService();

        if(userService.checkUser(login, password))
        {
            //TODO это десь делать?
            HttpSession session=request.getSession();
            constEnum=ConstEnum.LOGIN;
            session.setAttribute(constEnum.getValue(), login);
            constEnum=ConstEnum.ROLE;
            session.setAttribute(constEnum.getValue(), userService.getUser().getType());
            PageEnum page=PageEnum.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        else
        {
            PageEnum page=PageEnum.LOGIN_PAGE;
            router.setTarget(page.getPath());
        }

        return router;
    }
}