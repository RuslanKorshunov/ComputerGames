package by.epam.computergames.service;

import by.epam.computergames.command.CommandName;
import by.epam.computergames.command.RequestParameter;
import by.epam.computergames.cryptologist.AESCryptologist;
import by.epam.computergames.dao.AbstractDao;
import by.epam.computergames.dao.DaoException;
import by.epam.computergames.dao.UserDao;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class UserService extends AbstractService<User> {
    private static final String USERS_TABLE = "users";
    private static final String USER_INFO_TABLE = "userinfo";

    @Override
    public void add(User user) throws ServiceException {
        try {
            if (!LoginValidator.validate(user.getLogin())) {
                throw new IncorrectDataException("Login has invalid value.");
            }
            if (!PasswordValidator.validate(user.getPassword())) {
                throw new IncorrectDataException("Password has invalid value.");
            }
            if (!EmailValidator.validate(user.getEmail())) {
                throw new IncorrectDataException("Email has invalid value.");
            }
            if (!NameValidator.validate(user.getName())) {
                throw new IncorrectDataException("Name has invalid value.");
            }
            if (!SurnameValidator.validate(user.getSurname())) {
                throw new IncorrectDataException("Surname has invalid value");
            }

            AESCryptologist cryptologist = new AESCryptologist();
            String password = cryptologist.makeAs(user.getPassword());
            user.setPassword(password);

            AbstractDao dao = null;
            try {
                dao = new UserDao();
                dao.create(user);
            } finally {
                returnConnection(dao);
            }
        } catch (IncorrectDataException |
                DaoException |
                UnsupportedEncodingException |
                NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Object... values) throws ServiceException {
        String login = (String) values[0];
        CommandName command = (CommandName) values[1];
        String newValue = (String) values[2];
        String column;
        String table;

        try {
            switch (command) {
                case CHANGE_NAME:
                    if (!NameValidator.validate(newValue)) {
                        throw new IncorrectDataException("Name" + newValue + " has incorrect value.");
                    }
                    column = RequestParameter.NAME.getValue();
                    table = USER_INFO_TABLE;
                    break;
                case CHANGE_SURNAME:
                    if (!SurnameValidator.validate(newValue)) {
                        throw new IncorrectDataException("Surname" + newValue + " has incorrect value.");
                    }
                    column = RequestParameter.SURNAME.getValue();
                    table = USER_INFO_TABLE;
                    break;
                case CHANGE_PASSWORD:
                    if (!PasswordValidator.validate(newValue)) {
                        throw new IncorrectDataException("Password " + newValue + " has incorrect value.");
                    }
                    column = RequestParameter.PASSWORD.getValue();
                    table = USERS_TABLE;
                    break;
                case CHANGE_SEX:
                    column = RequestParameter.SEX.getValue();
                    table = USER_INFO_TABLE;
                    break;
                case CHANGE_EMAIL:
                    if (!EmailValidator.validate(newValue)) {
                        throw new IncorrectDataException("Email " + newValue + " has incorrect value.");
                    }
                    column = RequestParameter.EMAIL.getValue();
                    table = USER_INFO_TABLE;
                    break;
                default:
                    throw new IncorrectDataException("command can't be processed.");
            }
            AbstractDao dao = null;
            try {
                dao = new UserDao();
                dao.update(table, column, newValue, login);
            } finally {
                returnConnection(dao);
            }
        } catch (IncorrectDataException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User find(Object... values) throws ServiceException {
        String login = (String) values[0];
        String password = (String) values[1];
        User user;

        try {
            if (!LoginValidator.validate(login)) {
                throw new IncorrectDataException("login has invalid value");
            }
            if (!PasswordValidator.validate(password)) {
                throw new IncorrectDataException("password has invalid value");
            }


            AbstractDao dao = null;
            try {
                dao = new UserDao();
                user = (User) dao.findBy(login);
            } finally {
                returnConnection(dao);
            }

            AESCryptologist cryptologist = new AESCryptologist();
            password = cryptologist.makeAs(password);

            if (!user.getPassword().equals(password)) {
                throw new IncorrectDataException("User Password isn't equal to entered password.");
            }
        } catch (IncorrectDataException |
                DaoException |
                UnsupportedEncodingException |
                NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}