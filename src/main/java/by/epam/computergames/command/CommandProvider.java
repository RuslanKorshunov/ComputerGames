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
                case BACKWARD_GAMES:
                case FORWARD_GAMES:
                    command=new SearchGamesCommand();
                    break;
                case GET_GAME:
                    command=new GetGameCommand();
                    break;
                case SET_REVIEW:
                    command=new SetReviewCommand();
                    break;
                case GET_SEARCH_PAGE:
                    command=new GetSearchPageCommand();
                    break;
                case CHANGE_SEARCH_PARAMETER:
                    command=new ChangeSearchParameterCommand();
                    break;
                case GET_REVIEW_PAGE:
                    command=new GetReviewPageCommand();
                    break;
                case CHANGE_LANG:
                    command=new ChangeLanguageCommand();
                    break;
                case GET_AUTHORIZATION_PAGE:
                    command=new GetAuthorizationPageCommand();
                    break;
                case GET_REVIEWS_PAGE:
                case FORWARD_REVIEWS:
                case BACKWARD_REVIEWS:
                    command=new GetReviewsPageCommand();
                    break;
                case DELETE_REVIEW:
                    command=new DeleteReviewCommand();
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