package by.epam.computergames.service;

import by.epam.computergames.command.CommandName;
import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.GameDao;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NumberValidator;
import by.epam.computergames.warehouse.GameWarehouse;

import java.util.List;

public class GameService extends AbstractService<Game> {
    private static final int NUMBER_OF_ENTITIES = 8;
    private static final int FIRST_PAGE_INDEX = 0;

    @Override
    public List<Game> findAll(Object... values) throws ServiceException {
        GameParameter gameParameter = (GameParameter) values[0];
        List<Game> games;

        try {
            String yearFrom = gameParameter.getYearFrom();
            if (yearFrom == null || !NumberValidator.validate(yearFrom)) {
                throw new IncorrectDataException("Year has invalid value " + "\"" + yearFrom + "\"");
            }
            String yearTo = gameParameter.getYearTo();
            if (yearTo == null || !NumberValidator.validate(yearTo)) {
                throw new IncorrectDataException("Year has invalid value " + "\"" + yearTo + "\"");
            }
            String idGenre = gameParameter.getIdGenre();
            if (idGenre == null || !NumberValidator.validate(idGenre)) {
                throw new IncorrectDataException("IdGenre has invalid value " + "\"" + idGenre + "\"");
            }
            String idDeveloper = gameParameter.getIdDeveloper();
            if (idDeveloper == null || !NumberValidator.validate(idDeveloper)) {
                throw new IncorrectDataException("IdDeveloper has invalid value " + "\"" + idDeveloper + "\"");
            }
            int pageNumber = checkCommand(gameParameter);

            long idFirst = pageNumber * NUMBER_OF_ENTITIES;
            long size = findSize(gameParameter);
            if (idFirst >= size && pageNumber != FIRST_PAGE_INDEX) {
                pageNumber--;
                idFirst = pageNumber * NUMBER_OF_ENTITIES;
            }
            gameParameter.setPageNumber(pageNumber);
            AbstractDao dao = null;
            try {
                dao = new GameDao();
                games = dao.find(idFirst, gameParameter);
            } finally {
                returnConnection(dao);
            }

            GameWarehouse warehouse = GameWarehouse.getInstance();
            for (Game game : games) {
                warehouse.put(game);
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
        return games;
    }

    private long findSize(GameParameter gameParameter) throws DaoException {
        Long size;

        AbstractDao dao = null;
        try {
            dao = new GameDao();
            size = dao.findSize(gameParameter);
        } finally {
            returnConnection(dao);
        }

        return size;
    }

    private int checkCommand(GameParameter gameParameter) {
        String command = gameParameter.getCommand();
        int pageNumber = Integer.parseInt(gameParameter.getPageNumber());
        if (command.equals(CommandName.FORWARD_GAMES.getValue())) {
            pageNumber++;
        }
        if (command.equals(CommandName.BACKWARD_GAMES.getValue()) && pageNumber != FIRST_PAGE_INDEX) {
            pageNumber--;
        }
        return pageNumber;
    }

    @Override
    public void update(Object... values) throws ServiceException {
        Game game = (Game) values[0];

        try {
            String idGame = game.getIdGame();
            if (idGame == null || !NumberValidator.validate(idGame)) {
                throw new IncorrectDataException("idGame has invalid value " + "\"" + idGame + "\"");
            }
            String name = game.getName();
            if (name == null) {
                throw new IncorrectDataException("name has invalid value " + "\"" + name + "\"");
            }
            String idDeveloper = game.getDeveloper();
            if (idDeveloper == null || !NumberValidator.validate(idDeveloper)) {
                throw new IncorrectDataException("idDeveloper has invalid value " + "\"" + idDeveloper + "\"");
            }
            String year = game.getYear();
            if (year == null || !NumberValidator.validate(year)) {
                throw new IncorrectDataException("year has invalid value " + "\"" + year + "\"");
            }

            AbstractDao dao = null;
            try {
                dao = new GameDao();
                dao.update(game);
                game = ((GameDao) dao).findBy(idGame);
                GameWarehouse gameWarehouse = GameWarehouse.getInstance();
                gameWarehouse.put(game);
            } finally {
                returnConnection(dao);
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}