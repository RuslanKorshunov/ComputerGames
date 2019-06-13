package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Review;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetReviewCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router;
        HttpSession session=request.getSession();
        String login=(String)session.getAttribute(RequestConst.LOGIN.getValue());

        String comment=request.getParameter(RequestConst.COMMENT.getValue());
        String mark=request.getParameter(RequestConst.MARK.getValue());//todo мне это не нравится
        int markInt=Integer.parseInt(mark);
        String idGame=request.getParameter(RequestConst.ID.getValue());
        long idGameLong=Long.parseLong(idGame);//todo мне это не нравится

        try
        {
            Review review=new Review();
            review.setIdGame(idGameLong);
            review.setLogin(login);
            review.setMark(markInt);
            review.setComments(comment);
            AbstractService service=new ReviewService();
            service.add(review);
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        }
        catch (ConnectionException|DAOException|IncorrectDataException|CryptologistException e)
        {
            //TODO log
            AbstractCommand command=new GetReviewPageCommand();
            router=command.execute(request);
        }

        return router;
    }
}
