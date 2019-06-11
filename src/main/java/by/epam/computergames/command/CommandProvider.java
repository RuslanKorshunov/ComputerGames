package by.epam.computergames.command;

import by.epam.computergames.exception.IncorrectDataException;

public class CommandProvider
{
    public AbstractCommand provide(String commandName) throws IncorrectDataException
    {
        AbstractCommand command=null;
        commandName=commandName.toUpperCase();
        try
        {
            CommandConst commandConst = CommandConst.valueOf(commandName);
            switch (commandConst)
            {
                case AUTHORIZATION:
                    command=new AuthorizationCommand();
                    break;
                case LOGOUT:
                    command=new LogOutCommand();
                    break;
                case GET_USER_INFO:
                    command=new GetUserInfoCommand();
                    break;
                case CHANGE_NAME:
                case CHANGE_SURNAME:
                case CHANGE_PASSWORD:
                case CHANGE_EMAIL:
                case CHANGE_SEX:
                    command=new ChangeUserParameterCommand();
                    break;
                case GET_MAIN_PAGE:
                    command=new GetMainPageCommand();
                    break;
                case REGISTRATION:
                    command=new RegistrationCommand();
                    break;
                case GET_REGISTRATION_PAGE:
                    command=new GetRegistrationPageCommand();
                    break;
                case GET_GAMES:
                    command=new SearchGamesCommand();
                    break;
                case GET_GAME:
                    command=new GetGameCommand();
                    break;
                case RATE_GAME:
                    command=new RateGameCommand();
                    break;
                case BACKWARD:
                    command=new SearchGamesCommand();
                    break;
                case FORWARD:
                    command=new SearchGamesCommand();
                    break;
                case GET_SEARCH_PAGE:
                    command=new GetSearchPageCommand();
                    break;
                case CHANGE_SEARCH_PARAMETER:
                    command=new ChangeSearchParameterCommand();
                    break;
            }
        }
        catch (NullPointerException e)
        {
            throw new IncorrectDataException("commandName can't be null.", e);
        }
        catch (IllegalArgumentException e)
        {
            throw new IncorrectDataException("commandName has invalid value.", e);
        }
        return command;
    }
}