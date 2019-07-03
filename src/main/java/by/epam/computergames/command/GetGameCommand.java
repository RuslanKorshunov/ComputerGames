package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.Genre;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.AverageRatingService;
import by.epam.computergames.warehouse.GameWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetGameCommand implements AbstractCommand {
    private static final Logger logger = LogManager.getLogger(GetGameCommand.class);

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
            Genre genre = game.getGenre();
            String genreValue = genre.getValue();
            request.setAttribute(RequestParameter.GENRE.getValue(), genreValue);
            String picture = request.getContextPath() + "/img/" + game.getPicture();
            request.setAttribute(RequestParameter.PICTURE.getValue(), picture);
            request.setAttribute(RequestParameter.ID.getValue(), idGame);
            AbstractService service = new AverageRatingService();
            double rating = (double) service.find(idGame);
            request.setAttribute(RequestParameter.RATING.getValue(), rating);
            int year = game.getYear();
            request.setAttribute(RequestParameter.YEAR.getValue(), year);
            PageName pageName = PageName.GAME_PAGE;
            router.setTarget(pageName);
        } catch (IncorrectDataException | ConnectionException | DaoException | CryptologistException e) {
            logger.error(e);
            PageName pageName = PageName.MAIN_PAGE;
            router.setTarget(pageName);
        }

        return router;
    }
}