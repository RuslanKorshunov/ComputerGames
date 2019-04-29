package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public class GoBackCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        router.setTarget(Page.MAIN_PAGE.getPath());
        return router;
    }
}
