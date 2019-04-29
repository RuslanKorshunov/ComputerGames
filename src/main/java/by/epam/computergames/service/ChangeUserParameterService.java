package by.epam.computergames.service;

import by.epam.computergames.command.CommandEnum;
import by.epam.computergames.command.ConstEnum;
import by.epam.computergames.command.Table;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.ChangeUserParameterDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.EmailValidator;
import by.epam.computergames.validator.NameValidator;
import by.epam.computergames.validator.PasswordValidator;
import by.epam.computergames.validator.SurnameValidator;

public class ChangeUserParameterService
{
    public void change(String login, CommandEnum command, String newValue) throws IncorrectDataException,
                                                                                    ConnectionException,
                                                                                    DAOException
    {
        String column=null;
        String table=null;

        switch (command)
        {
            case CHANGE_NAME:
                if(!NameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Name"+newValue+"has incorrect value.");
                }
                column=ConstEnum.NAME.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_SURNAME:
                if(!SurnameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Surname"+newValue+"has incorrect value.");
                }
                column=ConstEnum.SURNAME.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_PASSWORD:
                if(!PasswordValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Password "+newValue+"has incorrect value.");
                }
                column=ConstEnum.PASSWORD.getValue();//TODO может, создать отдельные переменные
                table=Table.USERS.getValue();
                break;
            case CHANGE_SEX:
                column=ConstEnum.SEX.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_EMAIL:
                if(!EmailValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Email "+newValue+"has incorrect value.");
                }
                column=ConstEnum.EMAIL.getValue();
                table=Table.USER_INFO.getValue();
                break;
        }
        AbstractDAO dao=new ChangeUserParameterDAO();
        dao.update(table, column, newValue, login);
        dao.returnConnection();
    }
}
