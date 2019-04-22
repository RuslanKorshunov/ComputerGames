package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.entity.Role;
import by.epam.computergames.entity.User;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.GetUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetUserInfoCommand implements AbstractCommand
{
    private GetUserInfoService service;

    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException
    {
        Router router=new Router();

        HttpSession session=request.getSession();
        ConstEnum constEnum = ConstEnum.LOGIN;
        String login = (String) session.getAttribute(constEnum.getValue());
        constEnum = ConstEnum.ROLE;
        Role role = (Role) session.getAttribute(constEnum.getValue());

        if(role==Role.GUEST)
        {
            PageEnum pageEnum=PageEnum.MAIN_PAGE;
            router.setTarget(pageEnum.getPath());
        }
        else
        {
            service=new GetUserInfoService();
            try
            {
                User user=service.find(login, role);
                constEnum=ConstEnum.NAME;
                request.setAttribute(constEnum.getValue(), user.getName());
                constEnum=ConstEnum.SURNAME;
                request.setAttribute(constEnum.getValue(), user.getSurname());
                constEnum=ConstEnum.LOGIN;
                request.setAttribute(constEnum.getValue(), user.getLogin());
                constEnum=ConstEnum.PASSWORD;
                request.setAttribute(constEnum.getValue(), user.getPassword());
                constEnum=ConstEnum.ROLE;
                request.setAttribute(constEnum.getValue(), user.getRole().getId());
                constEnum=ConstEnum.SEX;
                request.setAttribute(constEnum.getValue(), user.getSex().getValue());
                constEnum=ConstEnum.EMAIL;
                request.setAttribute(constEnum.getValue(), user.getEmail());
                PageEnum pageEnum=PageEnum.USER_PAGE;
                router.setTarget(pageEnum.getPath());
            }
            catch (IncorrectDataException e)
            {
                //TODO логгирование
                PageEnum page=PageEnum.INDEX;
                router.setTarget(page.getPath());
                router.setRedirect();
                session.invalidate();
            }
        }

        return router;
    }
}