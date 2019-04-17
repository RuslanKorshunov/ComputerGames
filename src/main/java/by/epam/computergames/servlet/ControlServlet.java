package by.epam.computergames.servlet;

import by.epam.computergames.command.AbstractCommand;
import by.epam.computergames.command.AuthorizationCommand;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControlServlet", urlPatterns = {"/ControlServlet"})
public class ControlServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request ,response);
        }
        catch (ConnectionException|IncorrectDataException e)
        {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request ,response);
        }
        catch (ConnectionException|IncorrectDataException e)
        {

        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConnectionException,
                                                                                                    IncorrectDataException
    {
        AbstractCommand command=new AuthorizationCommand();
        command.execute(request, response);
    }
}