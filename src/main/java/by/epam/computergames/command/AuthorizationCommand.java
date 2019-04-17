package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException, IncorrectDataException
    {
        Router router=new Router();

        ConstEnum constEnum=ConstEnum.LOGIN;
        String login=request.getParameter(constEnum.getValue());
        constEnum=ConstEnum.PASSWORD;
        String password=request.getParameter(constEnum.getValue());

        AbstractDAO dao=new UserDAO();
        User user=(User)dao.findById(login);
        dao.returnConnection();

        if(user.getLogin()==null || !user.getPassword().equals(password))
        {
            router.setTarget("jsp/login_page.jsp");
        }
        else
        {
            //TODO это десь делать?
            HttpSession session=request.getSession();
            constEnum=ConstEnum.LOGIN;
            session.setAttribute(constEnum.getValue(), login);
            constEnum=ConstEnum.ROLE;
            session.setAttribute(constEnum.getValue(), user.getType());

            router.setTarget("jsp/main_page.jsp");
        }

        return router;
    }
}