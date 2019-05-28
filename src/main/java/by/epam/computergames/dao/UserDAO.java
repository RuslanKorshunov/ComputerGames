package by.epam.computergames.dao;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User>
{
    private static final String SELECT_USER_BY_LOGIN_QUERY ="select users.login, users.password, " +
                                                            "users.type, userinfo.name, " +
                                                            "userinfo.surname, userinfo.sex, " +
                                                            "userinfo.email " +
                                                            "from userinfo join users " +
                                                            "on users.login=userinfo.login where users.login=?";
    private static final String INSERT_INTO_USERS_QUERY="insert into users values (?, ?, ?)";
    private static final String INSERT_INTO_USERINFO_QUERY="insert into userinfo values (?, ?, ?, ?, ?)";

    public UserDAO() throws ConnectionException
    {
        super();
    }

    @Override
    public User findBy(String login) throws DAOException
    {
        User user=new User();
        PreparedStatement statement=null;
        try
        {
            statement=connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
            {
                user.setLogin(login);
                String password=rs.getString(2);
                user.setPassword(password);
                switch (rs.getInt(3))
                {
                    case 1:
                        user.setRole(Role.ADMIN);
                        break;
                    case 2:
                        user.setRole(Role.USER);
                        break;
                    default:
                        throw new DAOException("Data in database has invalid value.");
                }
                String name=rs.getString(4);
                user.setName(name);
                String surname=rs.getString(5);
                user.setSurname(surname);
                switch (rs.getString(6))
                {
                    case "male":
                        user.setSex(Sex.MALE);
                        break;
                    case "female":
                        user.setSex(Sex.FEMALE);
                        break;
                    case "third":
                        user.setSex(Sex.THIRD);
                        break;
                    default:
                        throw new DAOException("Data in database has invalid value.");
                }
                String email=rs.getString(7);
                user.setEmail(email);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("UserDAO can't get data from database due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
        return user;
    }

    @Override
    public void create(User user) throws DAOException
    {
        PreparedStatement statement=null;
        try
        {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(INSERT_INTO_USERS_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, Role.USER.getId());
            statement.executeUpdate();
            statement=connection.prepareStatement(INSERT_INTO_USERINFO_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getSex().getValue());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e)
        {
            try
            {
                connection.rollback();
            }
            catch (SQLException eSQL)
            {
                //todo логгирование
                System.out.println(e);
            }
            throw new  DAOException("UserDAO can't get data from database due to an internal error.", e);
        }
        finally
        {
            try
            {
                connection.setAutoCommit(true);
            }
            catch (SQLException e)
            {
                //todo log
                System.out.println(e);
            }
            closeStatement(statement);
        }
    }

    @Override
    public void update(String tableName, String column, String newValue, String login) throws DAOException
    {
        PreparedStatement statement=null;
        try
        {
            final String UPDATE_USER_QUERY="update "+tableName+" set "+column+"='"+newValue+"' where login='"+login+"'";
            statement=connection.prepareStatement(UPDATE_USER_QUERY);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            //todo log
            throw new DAOException("UserDAO can't update data in database due to an internal error.", e);
        }
        finally
        {
            closeStatement(statement);
        }
    }

    @Override
    public void returnConnection() throws ConnectionException {
        super.returnConnection();
    }

    @Override
    public List<User> find(long idFirst, int size) throws DAOException {
        return null;
    }

    @Override
    public double findAverageValue(long id) throws DAOException {
        return 0;
    }
}