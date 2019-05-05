package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.GetUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUserInfoCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException,
                                                            DAOException,
                                                            IncorrectDataException,
                                                            CryptologistException
    {
        Router router=new Router();

        HttpSession session=request.getSession();
        ConstEnum constEnum = ConstEnum.LOGIN;
        String login = (String) session.getAttribute(constEnum.getValue());
        constEnum = ConstEnum.ROLE;
        Role role = (Role) session.getAttribute(constEnum.getValue());

        if(role==Role.GUEST)
        {
            Page page = Page.MAIN_PAGE;
            router.setTarget(page.getPath());
        }
        else
        {
            AbstractService service=new GetUserInfoService();
            User user=(User) service.find(login, role);
            constEnum=ConstEnum.NAME;
            request.setAttribute(constEnum.getValue(), user.getName());
            constEnum=ConstEnum.SURNAME;
            request.setAttribute(constEnum.getValue(), user.getSurname());
            constEnum=ConstEnum.LOGIN;
            request.setAttribute(constEnum.getValue(), user.getLogin());
            constEnum=ConstEnum.PASSWORD;//TODO ПАРОЛИ!!!
            request.setAttribute(constEnum.getValue(), user.getPassword());
            constEnum=ConstEnum.ROLE;
            request.setAttribute(constEnum.getValue(), user.getRole().getId());
            constEnum=ConstEnum.SEX;
            request.setAttribute(constEnum.getValue(), user.getSex().getValue());
            constEnum=ConstEnum.EMAIL;
            request.setAttribute(constEnum.getValue(), user.getEmail());
            Page page = Page.USER_PAGE;
            router.setTarget(page.getPath());
/*                //TODO логгирование
                Page page=Page.INDEX;
                router.setTarget(page.getPath());
                router.setRedirect();
                session.invalidate();*/
        }

        return router;
    }
}