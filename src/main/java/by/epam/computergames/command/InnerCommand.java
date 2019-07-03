package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.GameService;

import java.util.ArrayList;
import java.util.List;

class InnerCommand {
    static List<PictureDelivery> findPictureDelivery(GameParameter gameParameter) throws IncorrectDataException,
            ConnectionException,
            DaoException {
        AbstractService service = new GameService();
        List<Game> games = service.findAll(gameParameter);
        List<PictureDelivery> deliveries = new ArrayList<>();
        games.forEach(game ->
        {
            PictureDelivery delivery = new PictureDelivery();
            long idGame = game.getIdGame();
            delivery.setId(idGame);
            String picture = game.getPicture();
            delivery.setPicture(picture);
            deliveries.add(delivery);
        });
        return deliveries;
    }
}