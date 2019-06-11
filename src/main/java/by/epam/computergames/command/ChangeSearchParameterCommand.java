package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.EntityConst;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeSearchParameterCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        String pageNumber=request.getParameter(RequestConst.PAGE_NUMBER.getValue());
        String yearFrom=request.getParameter(RequestConst.YEAR_FROM.getValue());
        if(yearFrom.equals(""))
        {
            yearFrom=EntityConst.DEFAULT_YEAR_FROM;
        }
        String yearTo=request.getParameter(RequestConst.YEAR_TO.getValue());
        if(yearTo.equals(""))
        {
            yearTo=EntityConst.DEFAULT_YEAR_TO;
        }
        String genre=request.getParameter(RequestConst.GENRE.getValue());
        String developer=request.getParameter(RequestConst.DEVELOPER.getValue());
        String command=request.getParameter(RequestConst.COMMAND.getValue());

        GameParameter gameParameter=new GameParameter(pageNumber, yearFrom, yearTo, genre, developer, command);

        try
        {
            List<PictureDelivery> deliveries=InnerCommand.findPictureDelivery(gameParameter);
            request.setAttribute(RequestConst.LIST.getValue(), RequestConst.GAMES.getValue());
            request.setAttribute(RequestConst.GAMES.getValue(), deliveries);
            request.setAttribute(RequestConst.PAGE_NUMBER.getValue(), gameParameter.getPageNumber());

            HttpSession session=request.getSession();
            session.setAttribute(RequestConst.YEAR_FROM.getValue(), yearFrom);
            session.setAttribute(RequestConst.YEAR_TO.getValue(), yearTo);
            session.setAttribute(RequestConst.GENRE.getValue(), genre);
            session.setAttribute(RequestConst.DEVELOPER.getValue(), developer);

            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        catch (ConnectionException|DAOException|IncorrectDataException|CryptologistException e)
        {
            Page page=Page.SEARCH_PAGE;
            router.setTarget(page.getPath());
        }

        return router;
    }
}
