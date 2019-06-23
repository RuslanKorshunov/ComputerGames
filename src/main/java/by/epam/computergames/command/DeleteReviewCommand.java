package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Review;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(DeleteReviewCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router;

        String idGame=request.getParameter(RequestConst.ID.getValue());
        String login=request.getParameter(RequestConst.LOGIN.getValue());

        Review review=new Review();
        review.setIdGame(idGame);
        review.setLogin(login);

        AbstractService service=new ReviewService();
        try
        {
            service.delete(review);
            AbstractCommand command=new GetReviewsPageCommand();
            router=command.execute(request);
        }
        catch (ConnectionException| DAOException e)
        {
            logger.error(e);
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        }

        return router;
    }
}
