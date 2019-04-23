package by.epam.computergames.servlet;

import by.epam.computergames.command.AbstractCommand;
import by.epam.computergames.command.CommandProvider;
import by.epam.computergames.command.ConstEnum;
import by.epam.computergames.command.Router;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.dao.DAOException;
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
        catch (ConnectionException|IncorrectDataException|DAOException e)
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
        catch (ConnectionException|IncorrectDataException|DAOException e)
        {

        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConnectionException,
                                                                                                    IOException,
                                                                                                    ServletException,
                                                                                                    IncorrectDataException,
                                                                                                    DAOException
    {
        CommandProvider commandProvider=new CommandProvider();
        String commandName=request.getParameter(ConstEnum.COMMAND.getValue());
        AbstractCommand command=commandProvider.provide(commandName);
        Router router=command.execute(request);
        if(router.getType()== Router.Type.FORWARD)
        {
            request.getRequestDispatcher(router.getTarget()).forward(request ,response);
        }
        else
        {
            response.sendRedirect(router.getTarget());
        }
    }
    //TODO где обрабатывать исключения connectionexception и incorrectdataexception
}