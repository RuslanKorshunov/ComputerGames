package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public class GetMainPageCommand implements AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setTarget(PageName.MAIN_PAGE);
        return router;
    }
}