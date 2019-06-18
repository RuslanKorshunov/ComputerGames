package by.epam.computergames.command;

import by.epam.computergames.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(LogOutCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Role role=(Role)session.getAttribute(RequestConst.ROLE.getValue());
        String name=(String)session.getAttribute(RequestConst.NAME.getValue());
        String surname=(String)session.getAttribute(RequestConst.SURNAME.getValue());
        String login=(String)session.getAttribute(RequestConst.LOGIN.getValue());
        Router router=new Router();
        Page page=Page.INDEX;
        router.setTarget(page.getPath());
        router.setRedirect();
        session.invalidate();
        logger.info(role+" "+name+" "+surname+" with login "+login+" logs out.");
        return router;
    }
}