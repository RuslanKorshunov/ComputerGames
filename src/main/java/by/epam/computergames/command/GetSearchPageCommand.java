package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Developer;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.DeveloperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetSearchPageCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(GetSearchPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();
        AbstractService service=new DeveloperService();
        try
        {
            List<Developer> developers=(List<Developer>)service.find();
            request.setAttribute(RequestConst.DEVELOPERS.getValue(), developers);
            router.setTarget(Page.SEARCH_PAGE.getPath());
        }
        catch (ConnectionException|DAOException|IncorrectDataException|CryptologistException e)
        {
            logger.error(e);
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        return router;
    }
}
