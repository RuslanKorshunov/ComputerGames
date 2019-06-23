package by.epam.computergames.service;

import by.epam.computergames.command.CommandConst;
import by.epam.computergames.command.RequestConst;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.AESCryptologist;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.*;

public class UserService extends AbstractService<User>
{
    private static final String USERS_TABLE="users";
    private static final String USER_INFO_TABLE="userinfo";

    @Override
    public void add(User user) throws IncorrectDataException, ConnectionException, DAOException, CryptologistException
    {
        if(!LoginValidator.validate(user.getLogin()))
        {
            throw new IncorrectDataException("Login has invalid value.");
        }
        if(!PasswordValidator.validate(user.getPassword()))
        {
            throw new IncorrectDataException("Password has invalid value.");
        }
        if(!EmailValidator.validate(user.getEmail()))
        {
            throw new IncorrectDataException("Email has invalid value.");
        }
        if(!NameValidator.validate(user.getName()))
        {
            throw new IncorrectDataException("Name has invalid value.");
        }
        if(!SurnameValidator.validate(user.getSurname()))
        {
            throw new IncorrectDataException("Surname has invalid value");
        }

        AESCryptologist cryptologist=new AESCryptologist();
        String password=cryptologist.makeAs(user.getPassword());
        user.setPassword(password);

        AbstractDAO dao=null;
        try
        {
            dao=new UserDAO();
            dao.create(user);
        }
        finally
        {
            returnConnection(dao);
        }
    }

    @Override
    public void change(Object ... values) throws IncorrectDataException,
                                                    ConnectionException,
                                                    DAOException
    {
        String login=(String) values[0];
        CommandConst command=(CommandConst) values[1];
        String newValue=(String) values[2];
        String column;
        String table;

        switch (command)
        {
            case CHANGE_NAME:
                if(!NameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Name"+newValue+" has incorrect value.");
                }
                column= RequestConst.NAME.getValue();
                table=USER_INFO_TABLE;
                break;
            case CHANGE_SURNAME:
                if(!SurnameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Surname"+newValue+" has incorrect value.");
                }
                column= RequestConst.SURNAME.getValue();
                table=USER_INFO_TABLE;
                break;
            case CHANGE_PASSWORD:
                if(!PasswordValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Password "+newValue+" has incorrect value.");
                }
                column= RequestConst.PASSWORD.getValue();
                table=USERS_TABLE;
                break;
            case CHANGE_SEX:
                column= RequestConst.SEX.getValue();
                table=USER_INFO_TABLE;
                break;
            case CHANGE_EMAIL:
                if(!EmailValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Email "+newValue+" has incorrect value.");
                }
                column= RequestConst.EMAIL.getValue();
                table=USER_INFO_TABLE;
                break;
            default:
                throw new IncorrectDataException("command can't be processed.");
        }
        AbstractDAO dao=null;
        try
        {
            dao=new UserDAO();
            dao.update(table, column, newValue, login);
        }
        finally
        {
            returnConnection(dao);
        }
    }

    @Override
    public User find(Object ... values) throws IncorrectDataException,
                                                            ConnectionException,
                                                            DAOException,
                                                            CryptologistException
    {
        String login=(String) values[0];
        String password=(String) values[1];
        if(!LoginValidator.validate(login))
        {
            throw new IncorrectDataException("login has invalid value.");
        }
        if(!PasswordValidator.validate(password))
        {
            throw new IncorrectDataException("password has invalid value.");
        }

        User user;
        AbstractDAO dao=null;
        try
        {
            dao=new UserDAO();
            user=(User)dao.findBy(login);
        }
        finally
        {
            returnConnection(dao);
        }


        AESCryptologist cryptologist=new AESCryptologist();
        password=cryptologist.makeAs(password);

        if(!user.getPassword().equals(password))
        {
            throw new IncorrectDataException("User Password isn't equal to entered password.");
        }

        return user;
    }
}