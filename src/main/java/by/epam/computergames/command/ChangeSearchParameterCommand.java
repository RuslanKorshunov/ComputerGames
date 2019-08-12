package by.epam.computergames.command;

import by.epam.computergames.entity.EntityConst;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeSearchParameterCommand extends InnerCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(ChangeSearchParameterCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER.getValue());
        String yearFrom = request.getParameter(RequestParameter.YEAR_FROM.getValue());
        if (yearFrom.equals("")) {
            yearFrom = EntityConst.DEFAULT_YEAR_FROM;
        }
        String yearTo = request.getParameter(RequestParameter.YEAR_TO.getValue());
        if (yearTo.equals("")) {
            yearTo = EntityConst.DEFAULT_YEAR_TO;
        }
        String genre = request.getParameter(RequestParameter.GENRE.getValue());
        String developer = request.getParameter(RequestParameter.DEVELOPER.getValue());
        String command = request.getParameter(RequestParameter.COMMAND.getValue());

        GameParameter gameParameter = new GameParameter(pageNumber, yearFrom, yearTo, genre, developer, command);

        try {
            List<PictureDelivery> deliveries = findPictureDelivery(gameParameter);
            request.setAttribute(RequestParameter.LIST.getValue(), RequestParameter.GAMES.getValue());
            request.setAttribute(RequestParameter.GAMES.getValue(), deliveries);
            request.setAttribute(RequestParameter.PAGE_NUMBER.getValue(), gameParameter.getPageNumber());

            HttpSession session = request.getSession();
            session.setAttribute(RequestParameter.YEAR_FROM.getValue(), yearFrom);
            session.setAttribute(RequestParameter.YEAR_TO.getValue(), yearTo);
            session.setAttribute(RequestParameter.GENRE.getValue(), genre);
            session.setAttribute(RequestParameter.DEVELOPER.getValue(), developer);

            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
        } catch (ServiceException e) {
            logger.error(e);
            AbstractCommand getSearchPageCommand = new GetSearchPageCommand();
            router = getSearchPageCommand.execute(request);
        }

        return router;
    }
}