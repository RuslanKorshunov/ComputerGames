package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Review;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetReviewCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(SetReviewCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router;
        HttpSession session=request.getSession();
        String login=(String)session.getAttribute(RequestConst.LOGIN.getValue());

        String comment=request.getParameter(RequestConst.COMMENT.getValue());
        String mark=request.getParameter(RequestConst.MARK.getValue());
        String idGame=request.getParameter(RequestConst.ID.getValue());

        Review review=new Review();
        review.setIdGame(idGame);
        review.setLogin(login);
        review.setMark(mark);
        review.setComment(comment);
        AbstractService service=new ReviewService();
        try
        {
            service.add(review);
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        }
        catch (ConnectionException|DAOException|IncorrectDataException|CryptologistException e)
        {
            logger.error(e);
            AbstractCommand command=new GetReviewPageCommand();
            router=command.execute(request);
        }

        return router;
    }
}
