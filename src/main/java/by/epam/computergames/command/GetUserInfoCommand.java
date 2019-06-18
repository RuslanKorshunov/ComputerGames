package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.GetUserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUserInfoCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(GetUserInfoCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {
        Router router=new Router();

        HttpSession session=request.getSession();
        String login = (String) session.getAttribute(RequestConst.LOGIN.getValue());
        Role role = (Role) session.getAttribute(RequestConst.ROLE.getValue());

        if(role==Role.GUEST)//todo переделать
        {
            Page page = Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        else
        {
            try
            {
                AbstractService service=new GetUserInfoService();
                User user=(User) service.find(login, role);
                request.setAttribute(RequestConst.NAME.getValue(), user.getName());
                request.setAttribute(RequestConst.SURNAME.getValue(), user.getSurname());
                request.setAttribute(RequestConst.LOGIN.getValue(), user.getLogin());
                request.setAttribute(RequestConst.SEX.getValue(), user.getSex().getValue());
                request.setAttribute(RequestConst.EMAIL.getValue(), user.getEmail());
                Page page = Page.USER_PAGE;
                router.setTarget(page.getPath());
            }
            catch (ConnectionException|DAOException|IncorrectDataException|CryptologistException e)
            {
                logger.error(e);
                Page page=Page.MAIN_PAGE;
                router.setTarget(page.getPath());
            }
        }

        return router;
    }
}