package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Router router=new Router();
        Page page= Page.INDEX;
        router.setTarget(page.getPath());
        router.setRedirect();
        session.invalidate();
        return router;
    }
}