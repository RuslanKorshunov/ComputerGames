package by.epam.computergames.service;

import by.epam.computergames.command.CommandConst;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.GameDAO;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GamesDelivery;
import by.epam.computergames.entity.PageDelivery;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.YearValidator;
import by.epam.computergames.warehouse.GameWarehouse;

import java.util.List;

public class SearchGamesService extends AbstractService
{
    private static final int NUMBER_OF_ENTITIES=8;
    private static final int FIRST_PAGE_INDEX=0;
    private static final int DEFAULT_YEAR_FROM=1900;
    private static final int DEFAULT_YEAR_TO=2050;

    @Override
    public GamesDelivery find(Object... values) throws ConnectionException,
                                                            DAOException,
                                                            IncorrectDataException
    {
        PageDelivery pageDelivery=(PageDelivery)values[0];
        GamesDelivery delivery=new GamesDelivery();

        String yearFrom=pageDelivery.getYearFrom();
        if(yearFrom!=null)
        {
            if(!YearValidator.validate(yearFrom))
            {
                throw new IncorrectDataException("Year "+yearFrom+" has invalid value.");
            }
            if(yearFrom.equals(""))
            {
                pageDelivery.setYearFrom(DEFAULT_YEAR_FROM);
            }
        }
        String yearTo=pageDelivery.getYearTo();
        if(yearTo!=null)
        {
            if(!YearValidator.validate(yearTo))
            {
                throw new IncorrectDataException("Year "+yearTo+" has invalid value.");
            }
            if(yearTo.equals(""))
            {
                pageDelivery.setYearTo(DEFAULT_YEAR_TO);
            }
        }
        //todo БРЕЕЕЕД
        String genre=pageDelivery.getIdGenre();
        if(genre!=null)
        {
            if(genre.equals(""))
            {
                pageDelivery.setIdGenre(null);
            }
        }
        String developer=pageDelivery.getIdDeveloper();
        if(developer!=null)
        {
            if(developer.equals(""))
            {
                pageDelivery.setIdDeveloper(null);
            }
        }
        int pageNumber=checkCommand(pageDelivery);

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
            games=dao.find(idFirst, pageDelivery);
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

    private int checkCommand(PageDelivery pageDelivery)
    {
        String command=pageDelivery.getCommand();
        int pageNumber=Integer.parseInt(pageDelivery.getPageNumber());
        if(command.equals(CommandConst.FORWARD.getValue()))
        {
            pageDelivery.setPageNumber(pageNumber++);
        }
        if(command.equals(CommandConst.BACKWARD.getValue()) && pageNumber!=FIRST_PAGE_INDEX)
        {
            pageDelivery.setPageNumber(pageNumber--);
        }
        return pageNumber;
    }
}
