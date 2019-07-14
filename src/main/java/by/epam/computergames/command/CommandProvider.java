package by.epam.computergames.command;

import by.epam.computergames.exception.IncorrectDataException;

public class CommandProvider {


    public AbstractCommand provide(String parameter) throws IncorrectDataException {
        if (parameter == null) {
            throw new IncorrectDataException("parameter can't be null");
        }
        AbstractCommand command;
        parameter = parameter.toUpperCase();
        try {
            CommandName commandName = CommandName.valueOf(parameter);
            command = commandName.getCommand();
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("parameter has invalid value", e);
        }
        return command;
    }
}