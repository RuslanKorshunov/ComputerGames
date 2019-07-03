package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public class GetRegistrationPageCommand implements AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setTarget(PageName.REGISTRATION_PAGE);
        return router;
    }
}