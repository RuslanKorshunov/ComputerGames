package by.epam.computergames.service;

import by.epam.computergames.command.CommandEnum;
import by.epam.computergames.command.ConstEnum;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.ChangeUserParameterDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.NameValidator;

public class ChangeUserParameterService
{
    private static final String USER_INFO="userinfo";

    public void change(String login, CommandEnum command, String newValue) throws IncorrectDataException,
                                                                                    ConnectionException,
                                                                                    DAOException
    {
        String column=null;

        switch (command)
        {
            case CHANGE_NAME:
                if(!NameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Name"+newValue+"has incorrect value.");
                }
                column=ConstEnum.NAME.getValue();//TODO может, создать отдельные переменные
                break;
        }
        AbstractDAO dao=new ChangeUserParameterDAO();
        dao.update(USER_INFO, column, newValue, login);
        dao.returnConnection();
    }
}
