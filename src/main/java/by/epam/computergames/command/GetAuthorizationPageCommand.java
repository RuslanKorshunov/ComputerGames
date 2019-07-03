package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public class GetAuthorizationPageCommand implements AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setTarget(PageName.AUTHORIZATION_PAGE);
        return router;
    }
}