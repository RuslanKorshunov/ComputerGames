package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchGamesCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(SearchGamesCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        HttpSession session=request.getSession();

        String pageNumber=request.getParameter(RequestConst.PAGE_NUMBER.getValue());
        String yearFrom=String.valueOf(session.getAttribute(RequestConst.YEAR_FROM.getValue()));
        String yearTo=String.valueOf(session.getAttribute(RequestConst.YEAR_TO.getValue()));
        String genre=String.valueOf(session.getAttribute(RequestConst.GENRE.getValue()));
        String developer=String.valueOf(session.getAttribute(RequestConst.DEVELOPER.getValue()));

        String command=request.getParameter(RequestConst.COMMAND.getValue());

        GameParameter gameParameter=new GameParameter(pageNumber, yearFrom, yearTo, genre, developer, command);

        try
        {
            List<PictureDelivery> deliveries=InnerCommand.findPictureDelivery(gameParameter);
            request.setAttribute(RequestConst.LIST.getValue(), RequestConst.GAMES.getValue());
            request.setAttribute(RequestConst.GAMES.getValue(), deliveries);
            request.setAttribute(RequestConst.PAGE_NUMBER.getValue(), gameParameter.getPageNumber());
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        catch (IncorrectDataException|DAOException|ConnectionException|CryptologistException e)
        {
            logger.error(e);
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        return router;
    }
}
