package by.epam.computergames.command;

import by.epam.computergames.entity.Developer;
import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.DeveloperService;
import by.epam.computergames.service.ServiceException;
import by.epam.computergames.warehouse.GameWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetChangeGamePageCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(GetChangeGamePageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String idGame = request.getParameter(RequestParameter.ID.getValue());

        GameWarehouse gameWarehouse = GameWarehouse.getInstance();
        try {
            Game game = gameWarehouse.get(idGame);
            String name = game.getName();
            request.setAttribute(RequestParameter.GAME_NAME.getValue(), name);
            String developer = game.getDeveloper();
            request.setAttribute(RequestParameter.DEVELOPER.getValue(), developer);
            String year = game.getYear();
            request.setAttribute(RequestParameter.YEAR.getValue(), year);
            String information = game.getInformation();
            request.setAttribute(RequestParameter.ABOUT.getValue(), information);
            AbstractService service=new DeveloperService();
            List<Developer> developers=(List<Developer>) service.find();
            request.setAttribute(RequestParameter.DEVELOPERS.getValue(), developers);
            request.setAttribute(RequestParameter.ID.getValue(), idGame);
            PageName pageName = PageName.CHANGE_GAME_PAGE;
            router.setTarget(pageName);
        } catch (IncorrectDataException | ServiceException e) {
            logger.error(e);
            AbstractCommand command = new GetGameCommand();
            router = command.execute(request);
        }
        return router;
    }
}
