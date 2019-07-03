package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static final Logger logger = LogManager.getLogger(UserDao.class);

    private static final String SELECT_USER_BY_LOGIN_QUERY = "select users.login, users.password, " +
            "users.type, userinfo.name, " +
            "userinfo.surname, userinfo.sex, " +
            "userinfo.email " +
            "from userinfo join users " +
            "on users.login=userinfo.login where users.login=?";
    private static final String INSERT_INTO_USERS_QUERY = "insert into users values (?, ?, ?)";
    private static final String INSERT_INTO_USERINFO_QUERY = "insert into userinfo values (?, ?, ?, ?, ?)";

    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final String THIRD = "third";

    public UserDao() throws DaoException {
        super();
    }

    @Override
    public User findBy(Object... value) throws DaoException {
        String login = (String) value[0];
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(login);
                String password = rs.getString(2);
                user.setPassword(password);
                switch (rs.getInt(3)) {
                    case 1:
                        user.setRole(Role.ADMIN);
                        break;
                    case 2:
                        user.setRole(Role.USER);
                        break;
                    default:
                        throw new DaoException("Data in database has invalid value.");
                }
                String name = rs.getString(4);
                user.setName(name);
                String surname = rs.getString(5);
                user.setSurname(surname);
                switch (rs.getString(6)) {
                    case MALE:
                        user.setSex(Sex.MALE);
                        break;
                    case FEMALE:
                        user.setSex(Sex.FEMALE);
                        break;
                    case THIRD:
                        user.setSex(Sex.THIRD);
                        break;
                    default:
                        throw new DaoException("Data in database has invalid value.");
                }
                String email = rs.getString(7);
                user.setEmail(email);
            }
        } catch (SQLException e) {
            throw new DaoException("UserDao can't get data from database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
        return user;
    }

    @Override
    public void create(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_INTO_USERS_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, Role.USER.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(INSERT_INTO_USERINFO_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getSex().getValue());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException eSQL) {
                logger.warn("There is " + e + " exception when connection tried make rollback.");
            }
            throw new DaoException("UserDao can't get data from database due to an internal error.", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.warn("There is " + e + " exception when connection tried set autocommit.");
            }
            closeStatement(statement);
        }
    }

    @Override
    public void update(String tableName, String column, String newValue, String login) throws DaoException {
        PreparedStatement statement = null;
        try {
            final String UPDATE_USER_QUERY = "update " + tableName + " set " + column + "=? where login=?";
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, newValue);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("UserDao can't update data in database due to an internal error.", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void returnConnection() throws ConnectionException {
        super.returnConnection();
    }

    @Override
    public List<User> find(Object... values) throws DaoException {
        return null;
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