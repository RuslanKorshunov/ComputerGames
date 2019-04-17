package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AbstractCommand
{
    void execute(HttpServletRequest request, HttpServletResponse response) throws ConnectionException,
                                                                                    IncorrectDataException;
}
