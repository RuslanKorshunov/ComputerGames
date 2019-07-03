package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.entity.Review;
import by.epam.computergames.entity.ReviewParameter;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetReviewsPageCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(GetReviewPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String idGame = request.getParameter(RequestParameter.ID.getValue());
        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER.getValue());
        String command = request.getParameter(RequestParameter.COMMAND.getValue());
        ReviewParameter reviewParameter = new ReviewParameter();
        reviewParameter.setIdGame(idGame);
        reviewParameter.setPageNumber(pageNumber);
        reviewParameter.setCommand(command);
        AbstractService service = null;
        try {
            service = new ReviewService();
            List<Review> reviews = service.findAll(reviewParameter);
            request.setAttribute(RequestParameter.ID.getValue(), idGame);
            request.setAttribute(RequestParameter.REVIEWS.getValue(), reviews);
            request.setAttribute(RequestParameter.PAGE_NUMBER.getValue(), reviewParameter.getPageNumber());
            PageName pageName = PageName.REVIEWS_PAGE;
            router.setTarget(pageName);
        } catch (IncorrectDataException | ConnectionException | DaoException e) {
            logger.error(e);
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
        }

        return router;
    }
}