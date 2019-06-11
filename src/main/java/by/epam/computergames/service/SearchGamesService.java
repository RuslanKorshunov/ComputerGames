package by.epam.computergames.service;

import by.epam.computergames.command.CommandConst;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.GameDAO;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GameParameter;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.DeveloperValidator;
import by.epam.computergames.validator.GenreValidator;
import by.epam.computergames.validator.YearValidator;
import by.epam.computergames.warehouse.GameWarehouse;

import java.util.List;

public class SearchGamesService extends AbstractService
{
    private static final int NUMBER_OF_ENTITIES=8;
    private static final int FIRST_PAGE_INDEX=0;

    @Override
    public List<Game> findAll(Object... values) throws ConnectionException,
                                                        DAOException,
                                                        IncorrectDataException
    {
        GameParameter gameParameter =(GameParameter)values[0];

        String yearFrom= gameParameter.getYearFrom();
        if(yearFrom!=null &&!YearValidator.validate(yearFrom))
        {
            throw new IncorrectDataException("Year "+yearFrom+" has invalid value.");
        }
        String yearTo= gameParameter.getYearTo();
        if(yearTo!=null && !YearValidator.validate(yearTo))
        {
            throw new IncorrectDataException("Year "+yearTo+" has invalid value.");
        }
        //todo БРЕЕЕЕД
        String idGenre= gameParameter.getIdGenre();
        if(idGenre!=null && !GenreValidator.validate(idGenre))
        {
            throw new IncorrectDataException("IdGenre "+idGenre+" has invalid value.");
        }
        String idDeveloper= gameParameter.getIdDeveloper();
        if(idDeveloper!=null && !DeveloperValidator.validate(idDeveloper))
        {
            throw new IncorrectDataException("IdDeveloper "+idDeveloper+" has invalid value.");
        }
        int pageNumber=checkCommand(gameParameter);

        long idFirst=pageNumber*NUMBER_OF_ENTITIES;
        long size=findSize(gameParameter);
        if(idFirst>=size && pageNumber!=FIRST_PAGE_INDEX)
        {
            pageNumber--;
            gameParameter.setPageNumber(pageNumber);
            idFirst=pageNumber*NUMBER_OF_ENTITIES;
        }
        AbstractDAO dao=null;
        List<Game> games;
        try
        {
            dao=new GameDAO();
            games=dao.find(idFirst, gameParameter);
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

        return games;
    }

    private long findSize(GameParameter gameParameter) throws ConnectionException, DAOException
    {
        Long size;

        AbstractDAO dao=null;
        try
        {
            dao=new GameDAO();
            size=dao.findSize(gameParameter);
        }
        finally
        {
            returnConnection(dao);
        }

        return size;
    }

    private int checkCommand(GameParameter gameParameter)
    {
        String command= gameParameter.getCommand();
        int pageNumber=Integer.parseInt(gameParameter.getPageNumber());
        if(command.equals(CommandConst.FORWARD.getValue()))
        {
            gameParameter.setPageNumber(pageNumber++);
        }
        if(command.equals(CommandConst.BACKWARD.getValue()) && pageNumber!=FIRST_PAGE_INDEX)
        {
            gameParameter.setPageNumber(pageNumber--);
        }
        return pageNumber;
    }
}
