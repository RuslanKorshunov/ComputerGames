package by.epam.computergames.command;

import by.epam.computergames.entity.Review;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import by.epam.computergames.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetReviewCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(SetReviewCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(RequestParameter.LOGIN.getValue());

        String comment = request.getParameter(RequestParameter.COMMENT.getValue());
        String mark = request.getParameter(RequestParameter.MARK.getValue());
        String idGame = request.getParameter(RequestParameter.ID.getValue());

        Review review = new Review();
        review.setIdGame(idGame);
        review.setLogin(login);
        review.setMark(mark);
        review.setComment(comment);
        AbstractService service = new ReviewService();
        try {
            service.add(review);
            AbstractCommand command = new GetGameCommand();
            router = command.execute(request);
        } catch (ServiceException e) {
            logger.error(e);
            AbstractCommand command = new GetReviewPageCommand();
            router = command.execute(request);
        }

        return router;
    }
}