package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Developer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao extends AbstractDao<Developer> {
    private static final String FIND_QUERY = "select * from developers";

    public DeveloperDao() throws ConnectionException {
        super();
    }

    @Override
    public Developer findBy(Object... values) throws DaoException {
        return null;
    }

    @Override
    public List<Developer> find(Object... values) throws DaoException {
        PreparedStatement statement = null;
        List<Developer> developers;
        try {
            developers = new ArrayList<>();
            statement = connection.prepareStatement(FIND_QUERY);//TODO здесь точно PrepareStatement
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Developer developer = new Developer();
                long idDeveloper = rs.getLong(1);
                developer.setIdDeveloper(idDeveloper);
                String name = rs.getString(2);
                developer.setName(name);
                developers.add(developer);
            }
        } catch (SQLException e) {
            throw new DaoException("DeveloperDao can't get data from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
        return developers;
    }

    @Override
    public void update(String tableName, String column, String newValue, String id) throws DaoException {

    }

    @Override
    public void create(Developer entity) throws DaoException {

    }

    @Override
    public double findAverageValue(Object... values) throws DaoException {
        return 0;
    }

    @Override
    public long findSize(Object... values) throws DaoException {
        return 0;
    }

    @Override
    public void delete(Object... values) throws DaoException {

    }
}