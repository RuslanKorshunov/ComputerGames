package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)//TODO здесь должен быть уровень логики
    {
        HttpSession session=request.getSession();
        System.out.println((String) session.getAttribute("login"));
        Router router=new Router();
        PageEnum page=PageEnum.INDEX;
        router.setTarget(page.getPath());
        router.setRedirect();
        session.invalidate();
        return router;
    }
}