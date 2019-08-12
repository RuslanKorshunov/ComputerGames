package by.epam.computergames.servlet;

import by.epam.computergames.command.*;
import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.connection.ConnectionPool;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.timer.ConnectionTimer;
import by.epam.computergames.timer.ConnectionTimerTask;
import by.epam.computergames.warehouse.GameWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimerTask;

@WebServlet(name = "ControlServlet", urlPatterns = {"/ControlServlet"})
public class ControlServlet extends HttpServlet {
    private static final long TIME = 10000;
    private static final Logger logger = LogManager.getLogger(ControlServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
            response.sendRedirect(PageName.MAIN_PAGE.getPath());
        }
    }

    @Override
    public void init(){
        logger.info("Servlet starts");
        try {
            ConnectionPool.getInstance();
            GameWarehouse.getInstance();
            ConnectionTimer timer = ConnectionTimer.getInstance();
            TimerTask timerTask = new ConnectionTimerTask();
            timer.schedule(timerTask, TIME, TIME);
        } catch (ConnectionException e) {
            logger.fatal("Servlet can't begin work due to a internal error", e);
        } catch (IncorrectDataException e) {
            logger.warn("Timer doesn't begin checking connections", e);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionTimer.getInstance().cancel();
        } catch (IncorrectDataException e) {
            logger.warn("Timer couldn't be canceled", e);
        }
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            logger.warn("ConnectionPool couldn't be destroyed", e);
        }
        try {
            GameWarehouse.getInstance().destroy();
        } catch (IncorrectDataException e) {
            logger.warn("GameWarehouse couldn't be destroyed", e);
        }
        logger.info("Servlet was destroyed");
    }
}