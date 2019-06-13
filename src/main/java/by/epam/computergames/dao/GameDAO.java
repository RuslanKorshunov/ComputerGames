package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.EntityConst;
import by.epam.computergames.entity.Game;
import by.epam.computergames.entity.GameParameter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO extends AbstractDAO<Game>
{
    private static final int NUMBER_OF_ENTITIES=8;
    private static final String BEGIN_QUERY="select games.idGame as idGame, games.name, " +
                                            "games.idGenre as idGenre, developers.developer as developer, " +
                                            "games.picture, games.year as year from games " +
                                            "join developers on games.idDeveloper=developers.idDeveloper";

    private static final String END_QUERY=" limit ?, ?";
    private static final String FIND_SIZE_QUERY="select count(idGame) from games";

    public GameDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public List<Game> find(Object... values) throws DAOException
    {
        long idFirst=(long)values[0];
        GameParameter gameParameter =(GameParameter) values[1];

        long idLast=idFirst+NUMBER_OF_ENTITIES;

        String regex=BEGIN_QUERY;
        regex= checkGameParameter(gameParameter, regex);
        regex+=END_QUERY;

        PreparedStatement statement=null;
        List<Game> games;
        try
        {
            statement=connection.prepareStatement(regex);
            statement.setLong(1, idFirst);
            statement.setLong(2, idLast);
            ResultSet rs=statement.executeQuery();
            games=new ArrayList<>();
            while (rs.next())
            {
                Game game=new Game();
                long idGame=rs.getInt(1);
                game.setIdGame(idGame);
                String name=rs.getString(2);
                game.setName(name);
                int idGenre=rs.getInt(3);
                game.setGenre(idGenre);
                String developer=rs.getString(4);
                game.setDeveloper(developer);
                String picture=rs.getString(5);
                game.setPicture(picture);
                int year=rs.getInt(6);
                game.setYear(year);
                games.add(game);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("GameDAO can't get data from database due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
        return games;
    }

    @Override
    public Game findBy(Object... values) throws DAOException {
        return null;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DAOException {

    }

    @Override
    public void create(Game entity) throws DAOException {

    }

    @Override
    public double findAverageValue(long id) throws DAOException {
        return 0;
    }

    @Override
    public long findSize(Object ... values) throws DAOException
    {
        GameParameter gameParameter=(GameParameter) values[0];
        String regex=FIND_SIZE_QUERY;
        regex=checkGameParameter(gameParameter, regex);
        PreparedStatement statement;//TODO может, лучше заменить
        long size=0;
        try
        {
            statement=connection.prepareStatement(regex);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                size=rs.getLong(1);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("GameDAO can't find size from database due to an internal error.", e);
        }
        return size;
    }

    private String checkGameParameter(GameParameter gameParameter, String regex)
    {
        final String WHERE=" where ";
        final String AND=" and ";
        boolean isUsed=false;

        String yearFrom= gameParameter.getYearFrom();
        if(yearFrom!=null)
        {
            regex+=WHERE+"year>="+yearFrom;
            isUsed=true;
        }
        String yearTo= gameParameter.getYearTo();
        if(yearTo!=null)
        {
            if(isUsed)
            {
                regex+=AND+"year<="+yearTo;
            }
            else
            {
                regex+=WHERE+"year<="+yearTo;
                isUsed=true;
            }
        }
        String idGenre= gameParameter.getIdGenre();
        if(idGenre!=null && !idGenre.equals(EntityConst.DEFAULT_ID_GENRE))
        {
            if(isUsed)
            {
                regex+=AND+"idGenre="+idGenre;
            }
            else
            {
                regex+=WHERE+"idGenre="+idGenre;
                isUsed=true;
            }
        }
        String idDeveloper= gameParameter.getIdDeveloper();
        if(idDeveloper!=null && !idDeveloper.equals(EntityConst.DEFAULT_ID_DEVELOPER))
        {
            if(isUsed)
            {
                regex+=AND+"games.idDeveloper="+idDeveloper;
            }
            else
            {
                regex+=WHERE+"games.idDeveloper="+idDeveloper;
            }
        }

        return regex;
    }
}
