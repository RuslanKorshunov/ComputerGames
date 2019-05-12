package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.GameDAO;
import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.warehouse.GameWarehouse;

import java.util.List;

public class SearchGamesService extends AbstractService<Game>
{
    private static final int NUMBER_OF_ENTITIES=8;

    @Override
    public List<Game> findEntities(Object... values) throws ConnectionException,
                                                            DAOException,
                                                            IncorrectDataException
    {
        int page=(Integer) values[0];
        int idFirst=page*NUMBER_OF_ENTITIES;
        //TODO вернуться к проверке на наличие
        AbstractDAO dao=null;
        List<Game> games;
        try
        {
            dao=new GameDAO();
            games=dao.find(idFirst, NUMBER_OF_ENTITIES);
        }
        finally
        {
            if(dao!=null)
            {
                dao.returnConnection();
            }
        }

        GameWarehouse warehouse=GameWarehouse.getInstance();
        for(Game game:games)
        {
            warehouse.put(game.getIdGame(), game);
        }

        return games;
    }
}
