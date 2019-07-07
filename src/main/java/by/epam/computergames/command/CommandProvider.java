package by.epam.computergames.command;

import by.epam.computergames.exception.IncorrectDataException;

public class CommandProvider {
    public AbstractCommand provide(String parameter) throws IncorrectDataException {
        if(parameter==null)
        {
            throw new IncorrectDataException("parameter can't be null.");
        }
        AbstractCommand command = null;
        parameter = parameter.toUpperCase();
        try {
            CommandName commandName = CommandName.valueOf(parameter);
            switch (commandName) {
                case AUTHORIZATION:
                    command = new AuthorizationCommand();
                    break;
                case LOGOUT:
                    command = new LogOutCommand();
                    break;
                case GET_USER_INFO:
                    command = new GetUserInfoCommand();
                    break;
                case CHANGE_NAME:
                case CHANGE_SURNAME:
                case CHANGE_PASSWORD:
                case CHANGE_EMAIL:
                case CHANGE_SEX:
                    command = new ChangeUserParameterCommand();
                    break;
                case GET_MAIN_PAGE:
                    command = new GetMainPageCommand();
                    break;
                case REGISTRATION:
                    command = new RegistrationCommand();
                    break;
                case GET_REGISTRATION_PAGE:
                    command = new GetRegistrationPageCommand();
                    break;
                case GET_GAMES:
                case BACKWARD_GAMES:
                case FORWARD_GAMES:
                    command = new SearchGamesCommand();
                    break;
                case GET_GAME:
                    command = new GetGameCommand();
                    break;
                case SET_REVIEW:
                    command = new SetReviewCommand();
                    break;
                case GET_SEARCH_PAGE:
                    command = new GetSearchPageCommand();
                    break;
                case CHANGE_SEARCH_PARAMETER:
                    command = new ChangeSearchParameterCommand();
                    break;
                case GET_REVIEW_PAGE:
                    command = new GetReviewPageCommand();
                    break;
                case CHANGE_LANG:
                    command = new ChangeLanguageCommand();
                    break;
                case GET_AUTHORIZATION_PAGE:
                    command = new GetAuthorizationPageCommand();
                    break;
                case GET_REVIEWS_PAGE:
                case FORWARD_REVIEWS:
                case BACKWARD_REVIEWS:
                    command = new GetReviewsPageCommand();
                    break;
                case DELETE_REVIEW:
                    command = new DeleteReviewCommand();
                    break;
                case GET_CHANGE_GAME_PAGE:
                    command = new GetChangeGamePageCommand();
                    break;
                case CHANGE_GAME:
                    command = new ChangeGameCommand();
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("parameter has invalid value.", e);
        }
        return command;
    }
}