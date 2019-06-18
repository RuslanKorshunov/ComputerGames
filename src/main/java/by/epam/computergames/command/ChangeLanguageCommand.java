package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements AbstractCommand
{
    private static final String EN_US="en_US";
    private static final String RU_RU="ru_RU";

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        HttpSession session=request.getSession();

        String lang=(String)session.getAttribute(RequestConst.LANG.getValue());
        if(lang.equals(EN_US))
        {
            session.setAttribute(RequestConst.LANG.getValue(), RU_RU);
        }
        else
        {
            session.setAttribute(RequestConst.LANG.getValue(), EN_US);
        }
        Page page=Page.MAIN_PAGE;
        router.setTarget(page.getPath());
        return router;
    }
}
