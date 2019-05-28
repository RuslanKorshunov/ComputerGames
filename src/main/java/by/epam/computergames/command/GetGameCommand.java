package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.Genre;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.FindAverageRatingService;
import by.epam.computergames.warehouse.GameWarehouse;

import javax.servlet.http.HttpServletRequest;

public class GetGameCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();

        String idGameValue=request.getParameter(ConstEnum.ID.getValue());
        long idGame=Long.parseLong(idGameValue);

        GameWarehouse gameWarehouse=GameWarehouse.getInstance();
        try
        {
            Game game=gameWarehouse.get(idGame);
            String name=game.getName();
            request.setAttribute(ConstEnum.GAME_NAME.getValue(), name);
            String developer=game.getDeveloper();
            request.setAttribute(ConstEnum.DEVELOPER.getValue(), developer);
            Genre genre=game.getGenre();
            String genreValue=genre.getValue();
            request.setAttribute(ConstEnum.GENRE.getValue(), genreValue);
            String picture=request.getContextPath()+"/img/"+game.getPicture();
            request.setAttribute(ConstEnum.PICTURE.getValue(), picture);
            request.setAttribute(ConstEnum.ID.getValue(), idGame);
            AbstractService service =new FindAverageRatingService();
            double rating=(double)service.find(idGame);
            request.setAttribute(ConstEnum.RATING.getValue(), rating);
            int year=game.getYear();
            request.setAttribute(ConstEnum.YEAR.getValue(), year);
            Page page=Page.GAME_PAGE;
            router.setTarget(page.getPath());
        }
        catch (IncorrectDataException|ConnectionException|DAOException|CryptologistException e)
        {
            //TODO log
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }

        return router;
    }
}