package by.epam.computergames.service;

import by.epam.computergames.command.CommandConst;
import by.epam.computergames.command.RequestConst;
import by.epam.computergames.command.Table;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.AbstractDAO;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.dao.UserDAO;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.validator.EmailValidator;
import by.epam.computergames.validator.NameValidator;
import by.epam.computergames.validator.PasswordValidator;
import by.epam.computergames.validator.SurnameValidator;

public class ChangeUserParameterService extends AbstractService<User>
{
    @Override
    public void change(Object ... values) throws IncorrectDataException,
                                            ConnectionException,
                                            DAOException
    {
        String login=(String) values[0];
        CommandConst command=(CommandConst) values[1];
        String newValue=(String) values[2];
        String column=null;
        String table=null;

        switch (command)
        {
            case CHANGE_NAME:
                if(!NameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Name"+newValue+"has incorrect value.");
                }
                column= RequestConst.NAME.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_SURNAME:
                if(!SurnameValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Surname"+newValue+"has incorrect value.");
                }
                column= RequestConst.SURNAME.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_PASSWORD:
                if(!PasswordValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Password "+newValue+"has incorrect value.");
                }
                column= RequestConst.PASSWORD.getValue();//TODO может, создать отдельные переменные
                table=Table.USERS.getValue();
                break;
            case CHANGE_SEX:
                column= RequestConst.SEX.getValue();
                table=Table.USER_INFO.getValue();
                break;
            case CHANGE_EMAIL:
                if(!EmailValidator.validate(newValue))
                {
                    throw new IncorrectDataException("Email "+newValue+"has incorrect value.");
                }
                column= RequestConst.EMAIL.getValue();
                table=Table.USER_INFO.getValue();
                break;
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
}
