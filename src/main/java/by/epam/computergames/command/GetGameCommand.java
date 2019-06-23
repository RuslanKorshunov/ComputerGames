package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.Genre;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.AverageRatingService;
import by.epam.computergames.warehouse.GameWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetGameCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(GetGameCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();

        String idGame=request.getParameter(RequestConst.ID.getValue());

        GameWarehouse gameWarehouse=GameWarehouse.getInstance();
        try
        {
            Game game=gameWarehouse.get(idGame);
            String name=game.getName();
            request.setAttribute(RequestConst.GAME_NAME.getValue(), name);
            String developer=game.getDeveloper();
            request.setAttribute(RequestConst.DEVELOPER.getValue(), developer);
            Genre genre=game.getGenre();
            String genreValue=genre.getValue();
            request.setAttribute(RequestConst.GENRE.getValue(), genreValue);
            String picture=request.getContextPath()+"/img/"+game.getPicture();
            request.setAttribute(RequestConst.PICTURE.getValue(), picture);
            request.setAttribute(RequestConst.ID.getValue(), idGame);
            AbstractService service =new AverageRatingService();
            double rating=(double)service.find(idGame);
            request.setAttribute(RequestConst.RATING.getValue(), rating);
            int year=game.getYear();
            request.setAttribute(RequestConst.YEAR.getValue(), year);
            Page page=Page.GAME_PAGE;
            router.setTarget(page.getPath());
        }
        catch (IncorrectDataException|ConnectionException|DAOException|CryptologistException e)
        {
            logger.error(e);
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }

        return router;
    }
}