package by.epam.computergames.command;

import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.entity.PictureDelivery;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.GameService;
import by.epam.computergames.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

class InnerCommand {
    static List<PictureDelivery> findPictureDelivery(GameParameter gameParameter) throws ServiceException {
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