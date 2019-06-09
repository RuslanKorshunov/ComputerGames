package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GamesDelivery;
import by.epam.computergames.entity.PageDelivery;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.SearchGamesService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SearchGamesCommand implements AbstractCommand
{
    private static final int FIRST_PAGE_INDEX=0;

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        String pageNumber=request.getParameter(RequestConst.PAGE_NUMBER.getValue());
        String yearFrom=request.getParameter(RequestConst.YEAR_FROM.getValue());
        String yearTo=request.getParameter(RequestConst.YEAR_TO.getValue());
        String genre=request.getParameter(RequestConst.GENRE.getValue());
        String developer=request.getParameter(RequestConst.DEVELOPER.getValue());
        String command=request.getParameter(RequestConst.COMMAND.getValue());

        PageDelivery pageDelivery=new PageDelivery();
        pageDelivery.setPageNumber(pageNumber);
        pageDelivery.setYearFrom(yearFrom);
        pageDelivery.setYearTo(yearTo);
        pageDelivery.setIdGenre(genre);
        pageDelivery.setIdDeveloper(developer);
        pageDelivery.setCommand(command);

        AbstractService service=new SearchGamesService();
        try
        {
            GamesDelivery gamesDelivery=(GamesDelivery) service.find(pageDelivery);
            List<Game> games=gamesDelivery.getGames();
            List<PictureDelivery> deliveries=new ArrayList<>();
            games.forEach(game ->
            {
                //TODO вынести в отдельный класс???
                PictureDelivery delivery=new PictureDelivery();
                long idGame=game.getIdGame();
                delivery.setId(idGame);
                String picture=game.getPicture();
                delivery.setPicture(picture);
                deliveries.add(delivery);
            });
            request.setAttribute(RequestConst.LIST.getValue(), RequestConst.GAMES.getValue());
            request.setAttribute(RequestConst.GAMES.getValue(), deliveries);
            request.setAttribute(RequestConst.PAGE_NUMBER.getValue(), pageDelivery.getPageNumber());
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        catch (IncorrectDataException|DAOException|ConnectionException|CryptologistException e)
        {
            //TODO log
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        return router;
    }
}
