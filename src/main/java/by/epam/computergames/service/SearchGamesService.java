package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.GameDAO;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GamesDelivery;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.warehouse.GameWarehouse;

import java.util.List;

public class SearchGamesService extends AbstractService
{
    private static final int NUMBER_OF_ENTITIES=8;
    private static final int FIRST_PAGE_INDEX=0;

    @Override
    public GamesDelivery find(Object... values) throws ConnectionException,
                                                            DAOException,
                                                            IncorrectDataException
    {
        GamesDelivery delivery=new GamesDelivery();

        int pageNumber=(Integer) values[0];
        long idFirst=pageNumber*NUMBER_OF_ENTITIES;
        long size=findSize();
        if(idFirst>size && pageNumber!=FIRST_PAGE_INDEX)
        {
            pageNumber--;
            idFirst=pageNumber*NUMBER_OF_ENTITIES;
        }
        AbstractDAO dao=null;
        List<Game> games;
        try
        {
            dao=new GameDAO();
            games=dao.find(idFirst, NUMBER_OF_ENTITIES);
        }
        finally
        {
            returnConnection(dao);
        }

        GameWarehouse warehouse=GameWarehouse.getInstance();
        for(Game game:games)
        {
            warehouse.put(game.getIdGame(), game);
        }

        delivery.setPageNumber(pageNumber);
        delivery.setGames(games);

        return delivery;
    }

    private long findSize() throws ConnectionException, DAOException
    {
        Long size;

        AbstractDAO dao=null;
        try
        {
            dao=new GameDAO();
            size=dao.findSize();
        }
        finally
        {
            returnConnection(dao);
        }

        return size;
    }
}
