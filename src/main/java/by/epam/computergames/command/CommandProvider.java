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
            CommandEnum commandEnum=CommandEnum.valueOf(commandName);
            switch (commandEnum)
            {
                case AUTHORIZATION:
                    command=new AuthorizationCommand();
            }
        }
        catch (NullPointerException e)
        {
            throw new IncorrectDataException("commandName can't be null.");
        }
        catch (IllegalArgumentException e)
        {
            throw new IncorrectDataException("commandName has invalid value.");
        }
        return command;
    }
}