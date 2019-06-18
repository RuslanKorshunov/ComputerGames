package by.epam.computergames.servlet;

import by.epam.computergames.command.AbstractCommand;
import by.epam.computergames.command.CommandProvider;
import by.epam.computergames.command.RequestConst;
import by.epam.computergames.command.Router;
import by.epam.computergames.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControlServlet", urlPatterns = {"/ControlServlet"})
public class ControlServlet extends HttpServlet
{
    private static final Logger logger= LogManager.getLogger(ControlServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request ,response);
        }
        catch (IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request ,response);
        }
        catch (IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                                    ServletException,
                                                                                                    IncorrectDataException
    {
        CommandProvider commandProvider=new CommandProvider();
        String commandName=request.getParameter(RequestConst.COMMAND.getValue());
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