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
    public GameDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public List<Game> find(int idFirst, int size) throws DAOException
    {
        PreparedStatement statement=null;
        List<Game> games;
        try
        {
            final String QUERY="select games.idGame, games.name, " +
                    "games.idGenre, developers.developer, " +
                    "games.picture from games " +
                    "join developers on games.idDeveloper=developers.idDeveloper " +
                    "limit "+idFirst+", "+size;
            statement=connection.prepareStatement(QUERY);
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
                games.add(game);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("GameDAO can't get data from database due to an internal error.");
        }
        catch (IncorrectDataException e)
        {
            throw new DAOException("GameDAO can't get data from database because one of games has unknown idGenre.");
        }
        finally
        {
            closeStatement(statement);
        }
        return games;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DAOException {
        super.update(tableName, column, newValue, id);
    }

    @Override
    public void create(Game entity) throws DAOException {
        super.create(entity);
    }
}
