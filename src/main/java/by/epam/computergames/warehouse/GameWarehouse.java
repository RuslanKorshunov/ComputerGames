package by.epam.computergames.warehouse;

import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameWarehouse
{
    private static GameWarehouse instance;
    private Map<Long, Game> games;


    private GameWarehouse()
    {
        games=new HashMap<>();
    }

    public static GameWarehouse getInstance()
    {
        if(instance==null)
        {
            instance=new GameWarehouse();
        }
        return instance;
    }

    public void put(long id, Game game) throws IncorrectDataException
    {
        if(instance!=null)
        {
            Lock lock=new ReentrantLock();
            lock.lock();
            try
            {
                if(!games.containsKey(id))
                {
                    games.put(id, game);
                }
            }
            finally
            {
                lock.unlock();
            }
        }
        else
        {
            throw new IncorrectDataException("GameWarehause isn't initialized.");
        }
    }

    public Game get(String id) throws IncorrectDataException
    {
        if(id==null || !NumberValidator.validate(id))
        {
            throw new IncorrectDataException("id "+id+" has invalid value.");
        }
        long idLong=Long.valueOf(id);
        Game game;
        if(instance!=null)
        {
            Lock lock=new ReentrantLock();
            lock.lock();
            try
            {
                if(games.containsKey(idLong))
                {
                    game=games.get(idLong);
                }
                else
                {
                    throw new IncorrectDataException("Game with id "+id+" doesn't exist.");
                }
            }
            finally
            {
                lock.unlock();
            }
        }
        else
        {
            throw new IncorrectDataException("GameWarehause isn't initialized.");
        }
        return game;
    }
}
