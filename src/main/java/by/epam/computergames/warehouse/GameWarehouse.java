package by.epam.computergames.warehouse;

import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameWarehouse {
    private static GameWarehouse instance;
    private static AtomicBoolean isCreated=new AtomicBoolean();
    private Map<Long, Game> games;


    private GameWarehouse() {
        games = new HashMap<>();
    }

    public static GameWarehouse getInstance() {
        if (isCreated.compareAndSet(false, true)) {
            instance = new GameWarehouse();
        }
        return instance;
    }

    public void put(Game game) throws IncorrectDataException {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (!isCreated.get()) {
                throw new IncorrectDataException("GameWarehause isn't initialized");
            }
            if (game == null) {
                throw new IncorrectDataException("game can't be null");
            }
            String idGameStr = game.getIdGame();
            if (idGameStr == null || !NumberValidator.validate(idGameStr)) {
                throw new IncorrectDataException("idGae has invalid value " + "\"" + idGameStr + "\"");
            }
            long idGame = Long.valueOf(idGameStr);
            games.put(idGame, game);
        } finally {
            lock.unlock();
        }

    }

    public Game get(String idGame) throws IncorrectDataException {
        if (!isCreated.get()) {
            throw new IncorrectDataException("GameWarehause isn't initialized");
        }
        if (idGame == null || !NumberValidator.validate(idGame)) {
            throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
        }
        long idGameLong = Long.valueOf(idGame);
        Game game;

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (games.containsKey(idGameLong)) {
                game = games.get(idGameLong);
            } else {
                throw new IncorrectDataException("Game with idGame " + idGame + " doesn't exist");
            }
        } finally {
            lock.unlock();
        }
        return game;
    }
}