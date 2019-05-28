package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Sex;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request)
    {

        Router router=new Router();
        String login=request.getParameter(ConstEnum.LOGIN.getValue());
        String password=request.getParameter(ConstEnum.PASSWORD.getValue());
        String email=request.getParameter(ConstEnum.EMAIL.getValue());
        String name=request.getParameter(ConstEnum.NAME.getValue());
        String surname=request.getParameter(ConstEnum.SURNAME.getValue());
        String sex=request.getParameter(ConstEnum.SEX.getValue());
        try
        {
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

            AbstractService service=new RegistrationService();
            service.add(user);
            Page page=Page.MAIN_PAGE;
            router.setTarget(page.getPath());

            HttpSession session=request.getSession();
            session.setAttribute(ConstEnum.LOGIN.getValue(), login);
            session.setAttribute(ConstEnum.ROLE.getValue(), user.getRole());
            session.setAttribute(ConstEnum.NAME.getValue(), user.getName());
            session.setAttribute(ConstEnum.SURNAME.getValue(), user.getSurname());
        }
        catch (IncorrectDataException|DAOException e)
        {
            System.out.println(e);
            //todo лог
            router.setTarget(Page.REGISTRATION_PAGE.getPath());
        }
        catch (ConnectionException|CryptologistException e)
        {
            System.out.println(e);
            //TODO мне кажется, лучше перенаправлять на главное окно с правами гостя
            //todo лог
            router.setTarget(Page.LOGIN_PAGE.getPath());
        }

        return router;
    }
}