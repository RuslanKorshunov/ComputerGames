package by.epam.computergames.command;

import by.epam.computergames.entity.Game;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.SearchGamesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            List<String> pictures=new ArrayList<>();
            games.forEach(game ->
            {
                System.out.println(game);
                pictures.add(game.getPicture());
            });
            request.setAttribute(ConstEnum.LIST.getValue(), ConstEnum.GAMES.getValue());
            request.setAttribute(ConstEnum.GAMES.getValue(), pictures);
            Page pageMain=Page.MAIN_PAGE;
            router.setTarget(pageMain.getPath());
        }
        catch (Exception e)
        {
            //TODO log
            HttpSession session=request.getSession();
            Page pageIndex=Page.INDEX;
            router.setTarget(pageIndex.getPath());
            router.setRedirect();
            session.invalidate();
        }

        return router;
    }
}
