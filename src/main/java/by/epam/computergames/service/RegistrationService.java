package by.epam.computergames.service;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.AESCryptologist;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.*;

public class RegistrationService extends AbstractService<User>
{
    @Override
    public void add(User user) throws IncorrectDataException,
                                        ConnectionException,
                                        DAOException,
                                        CryptologistException
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

        AbstractDAO dao=new UserDAO();
        dao.create(user);
        dao.returnConnection();
    }
}
