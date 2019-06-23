package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Review;
import by.epam.computergames.entity.ReviewParameter;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetReviewsPageCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(GetReviewPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        String idGame=request.getParameter(RequestConst.ID.getValue());
        String pageNumber=request.getParameter(RequestConst.PAGE_NUMBER.getValue());
        String command=request.getParameter(RequestConst.COMMAND.getValue());
        ReviewParameter reviewParameter=new ReviewParameter();
        reviewParameter.setIdGame(idGame);
        reviewParameter.setPageNumber(pageNumber);
        reviewParameter.setCommand(command);
        AbstractService service=null;
        try
        {
            service=new ReviewService();
            List<Review> reviews=service.findAll(reviewParameter);
            reviews.forEach(review -> System.out.println(review));
            request.setAttribute(RequestConst.ID.getValue(), idGame);
            request.setAttribute(RequestConst.REVIEWS.getValue(), reviews);
            Page page=Page.REVIEWS_PAGE;
            request.setAttribute(RequestConst.PAGE_NUMBER.getValue(), reviewParameter.getPageNumber());
            router.setTarget(page.getPath());
        }
        catch(IncorrectDataException| ConnectionException| DAOException e)
        {
            logger.error(e);
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }

        return router;
    }
}
