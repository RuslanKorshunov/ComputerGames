package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public class GetUserInfoCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        Page page = Page.USER_PAGE;
        router.setTarget(page.getPath());

        return router;
    }
}