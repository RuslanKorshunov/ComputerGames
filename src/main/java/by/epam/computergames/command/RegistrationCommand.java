package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements AbstractCommand
{
    private static final Logger logger= LogManager.getLogger(RegistrationCommand.class);

    @Override
    public Router execute(HttpServletRequest request)
    {

        Router router=new Router();
        String login=request.getParameter(RequestConst.LOGIN.getValue());
        String password=request.getParameter(RequestConst.PASSWORD.getValue());
        String email=request.getParameter(RequestConst.EMAIL.getValue());
        String name=request.getParameter(RequestConst.NAME.getValue());
        String surname=request.getParameter(RequestConst.SURNAME.getValue());
        String sex=request.getParameter(RequestConst.SEX.getValue());
        User user=new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        if(sex==null)
        {
            user.setSex(Sex.THIRD);
        }
        else
        {
            user.setSex(Sex.valueOf(sex.toUpperCase()));
        }

        AbstractService service=new UserService();
        try
        {
            service.add(user);
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());

            HttpSession session=request.getSession();
            session.setAttribute(RequestConst.LOGIN.getValue(), login);
            session.setAttribute(RequestConst.ROLE.getValue(), user.getRole());
            session.setAttribute(RequestConst.NAME.getValue(), user.getName());
            session.setAttribute(RequestConst.SURNAME.getValue(), user.getSurname());
        }
        catch (IncorrectDataException|DAOException e)
        {
            logger.error(e);
            router.setTarget(Page.REGISTRATION_PAGE.getPath());
        }
        catch (ConnectionException|CryptologistException e)
        {
            logger.error(e);
            router.setTarget(Page.AUTHORIZATION_PAGE.getPath());
        }

        return router;
    }
}