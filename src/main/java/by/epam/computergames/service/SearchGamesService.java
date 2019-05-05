package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.GameDAO;
import by.epam.computergames.entity.Game;

import java.util.List;

public class SearchGamesService extends AbstractService<Game>
{
    private static final int NUMBER_OF_ENTITIES=8;

    @Override
    public List<Game> findEntities(Object... values) throws ConnectionException, DAOException
    {
        int page=(Integer) values[0];
        int idFirst=page*NUMBER_OF_ENTITIES;
        AbstractDAO dao=new GameDAO();
        List<Game> games=dao.find(idFirst, NUMBER_OF_ENTITIES);
        return games;
    }
}
