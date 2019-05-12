package by.epam.computergames.command;

import by.epam.computergames.connection.ConnectionException;
import by.epam.computergames.cryptologist.CryptologistException;
import by.epam.computergames.dao.DAOException;
import by.epam.computergames.entity.Mark;
import by.epam.computergames.exception.IncorrectDataException;
import by.epam.computergames.service.AbstractService;
import by.epam.computergames.service.RateGameService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RateGameCommand implements AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request) throws ConnectionException,
                                                                DAOException,
                                                                IncorrectDataException,
                                                                CryptologistException
    {
        Router router;

        String starValue=request.getParameter(ConstEnum.STAR_FORM.getValue());
        int star=Integer.parseInt(starValue);
        String idGameValue=request.getParameter(ConstEnum.ID.value);
        long idGame=Long.parseLong(idGameValue);
        HttpSession session=request.getSession();
        String login=(String)session.getAttribute(ConstEnum.LOGIN.getValue());

        try
        {
            Mark mark=new Mark();
            mark.setIdGame(idGame);
            mark.setLogin(login);
            mark.setMark(star);
            AbstractService service=new RateGameService();
            service.add(mark);
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        }
        catch (Exception e)
        {
            //TODO log
            AbstractCommand command=new GetGameCommand();
            router=command.execute(request);
        }

        return router;
    }
}
