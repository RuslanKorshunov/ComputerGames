package by.epam.computergames.command;

import by.epam.computergames.exception.IncorrectDataException;

public class Router
{
    public enum Type
    {
        FORWARD,
        REDIRECT
    }

    private String target;
    private Type type=Type.FORWARD;

    public String getTarget() throws IncorrectDataException
    {
        if(target==null)
        {
            throw new IncorrectDataException("target is null.");
        }
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setRedirect()
    {
        type=Type.REDIRECT;
    }

    public Type getType()
    {
        return type;
    }
}