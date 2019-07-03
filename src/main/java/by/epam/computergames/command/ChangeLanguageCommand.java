package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements AbstractCommand {
    private static final String EN_US = "en_US";
    private static final String RU_RU = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String lang = (String) session.getAttribute(RequestParameter.LANG.getValue());
        if (lang.equals(EN_US)) {
            session.setAttribute(RequestParameter.LANG.getValue(), RU_RU);
        } else {
            session.setAttribute(RequestParameter.LANG.getValue(), EN_US);
        }
        PageName pageName = PageName.MAIN_PAGE;
        router.setTarget(pageName);
        return router;
    }
}