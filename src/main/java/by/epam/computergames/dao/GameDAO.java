package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Game;
import by.epam.computergames.exception.IncorrectDataException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO extends AbstractDAO<Game>
{
    private static final String GAME_QUERY="select games.idGame, games.name, " +
                                            "games.idGenre, developers.developer, " +
                                            "games.picture, games.year from games " +
                                            "join developers on games.idDeveloper=developers.idDeveloper " +
                                            "limit ?, ?";

    public GameDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public List<Game> find(long idFirst, int size) throws DAOException
    {
        PreparedStatement statement=null;
        List<Game> games;
        try
        {
            statement=connection.prepareStatement(GAME_QUERY);
            statement.setLong(1, idFirst);
            statement.setInt(2, size);
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
        catch (IncorrectDataException e)
        {
            throw new DAOException("GameDAO can't get data from database because one of games has unknown idGenre.", e);
        }
        finally
        {
            closeStatement(statement);
        }
        return games;
    }

    @Override
    public Game findBy(String id) throws DAOException {
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
}
