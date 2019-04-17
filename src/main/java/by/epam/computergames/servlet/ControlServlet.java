package by.epam.computergames.servlet;

import by.epam.computergames.command.AbstractCommand;
import by.epam.computergames.command.CommandProvider;
import by.epam.computergames.command.Router;
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
    private static final String COMMAND="command";//TODO наверное, это нужно перенести

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
                                                                                                    IncorrectDataException,
                                                                                                    IOException,
                                                                                                    ServletException
    {
        CommandProvider commandProvider=new CommandProvider();
        String commandName=request.getParameter(COMMAND);
        AbstractCommand command=commandProvider.provide(commandName);
        Router router=command.execute(request);
        if(router.getType()== Router.Type.FORWARD)
        {
            request.getRequestDispatcher(router.getTarget()).forward(request ,response);
        }
    }
}