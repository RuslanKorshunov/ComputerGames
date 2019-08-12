package by.epam.computergames.command;

import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchGamesCommand extends InnerCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(SearchGamesCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER.getValue());
        String yearFrom = String.valueOf(session.getAttribute(RequestParameter.YEAR_FROM.getValue()));
        String yearTo = String.valueOf(session.getAttribute(RequestParameter.YEAR_TO.getValue()));
        String genre = String.valueOf(session.getAttribute(RequestParameter.GENRE.getValue()));
        String developer = String.valueOf(session.getAttribute(RequestParameter.DEVELOPER.getValue()));

        String command = request.getParameter(RequestParameter.COMMAND.getValue());

        GameParameter gameParameter = new GameParameter(pageNumber, yearFrom, yearTo, genre, developer, command);

        try {
            List<PictureDelivery> deliveries = findPictureDelivery(gameParameter);
            request.setAttribute(RequestParameter.LIST.getValue(), RequestParameter.GAMES.getValue());
            request.setAttribute(RequestParameter.GAMES.getValue(), deliveries);
            request.setAttribute(RequestParameter.PAGE_NUMBER.getValue(), gameParameter.getPageNumber());
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
        } catch (ServiceException e) {
            logger.error(e);
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
        }
        return router;
    }
}