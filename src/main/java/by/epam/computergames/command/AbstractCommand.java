package by.epam.computergames.command;

import javax.servlet.http.HttpServletRequest;

public interface AbstractCommand {
    Router execute(HttpServletRequest request);
}
