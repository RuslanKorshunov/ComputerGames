package by.epam.computergames.command;

import by.epam.computergames.entity.Game;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.GameService;
import by.epam.computergames.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeGameCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(ChangeGameCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String idGame = request.getParameter(RequestParameter.ID.getValue());
        String gameName = request.getParameter(RequestParameter.GAME_NAME.getValue());
        int idGenre = Integer.parseInt(request.getParameter(RequestParameter.GENRE.getValue()));
        String idDeveloper = request.getParameter(RequestParameter.DEVELOPER.getValue());
        String year = request.getParameter(RequestParameter.YEAR.getValue());
        String about = request.getParameter(RequestParameter.ABOUT.getValue());

        Game game = new Game();
        game.setIdGame(idGame);
        game.setName(gameName);
        game.setGenre(idGenre);
        game.setDeveloper(idDeveloper);
        game.setYear(year);
        game.setInformation(about);

        AbstractService service = new GameService();
        try {
            service.update(game);
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        } catch (ServiceException e) {
            logger.error(e);
            AbstractCommand command=new GetChangeGamePageCommand();
            router=command.execute(request);
        }

        return router;
    }
}