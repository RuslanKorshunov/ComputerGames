package by.epam.computergames.command;

import by.epam.computergames.entity.Review;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import by.epam.computergames.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetReviewPageCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(GetReviewPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(RequestParameter.LOGIN.getValue());
        String idGame = request.getParameter(RequestParameter.ID.getValue());
        request.setAttribute(RequestParameter.ID.getValue(), idGame);
        try {
            AbstractService service = new ReviewService();
            Review review = new Review();
            review.setLogin(login);
            review.setIdGame(idGame);
            review = (Review) service.find(review);
            request.setAttribute(RequestParameter.MARK.getValue(), review.getMark());
            request.setAttribute(RequestParameter.COMMENT.getValue(), review.getComment());
            PageName pageName = PageName.REVIEW_PAGE;
            router.setTarget(pageName);
        } catch (ServiceException e) {
            logger.error(e);
            router = new Router();
            PageName pageName = PageName.SEARCH_PAGE;
            router.setTarget(pageName);
        }

        return router;
    }
}