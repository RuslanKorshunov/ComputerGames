package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public interface AbstractCommand
{
    Router execute(HttpServletRequest request) throws ConnectionException, DAOException;
}
