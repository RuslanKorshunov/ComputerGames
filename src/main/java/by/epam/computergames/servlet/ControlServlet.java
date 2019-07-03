package by.epam.computergames.servlet;

import by.epam.computergames.command.*;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
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
public class ControlServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControlServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {//todo показать
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            logger.warn("ConnectionPool couldn't be destroyed.");
        }
        logger.info("Servlet was destroyed.");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND.getValue());
        try {
            CommandProvider commandProvider = new CommandProvider();
            AbstractCommand command = commandProvider.provide(commandName);
            logger.info("Command " + commandName + " begins.");
            Router router = command.execute(request);
            if (router.getType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(router.getTarget()).forward(request, response);
            } else {
                response.sendRedirect(router.getTarget());
            }
            logger.info("Command " + commandName + " ends.");
        } catch (IncorrectDataException e) {
            logger.error(e);
            request.getRequestDispatcher(PageName.MAIN_PAGE.getPath()).forward(request, response);
        }
    }
}