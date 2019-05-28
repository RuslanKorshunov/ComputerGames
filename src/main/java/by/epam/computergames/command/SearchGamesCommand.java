package by.epam.computergames.command;

import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.SearchGamesService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SearchGamesCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        String pageValue=request.getParameter(ConstEnum.PAGE.getValue());
        int page=Integer.parseInt(pageValue);

        AbstractService service=new SearchGamesService();
        try
        {
            List<Game> games=service.findEntities(page);
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
            request.setAttribute(ConstEnum.LIST.getValue(), ConstEnum.GAMES.getValue());
            request.setAttribute(ConstEnum.GAMES.getValue(), deliveries);
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        catch (Exception e)
        {
            //TODO log
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        return router;
    }
}
