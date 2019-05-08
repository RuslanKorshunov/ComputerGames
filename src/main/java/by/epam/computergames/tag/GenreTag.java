package by.epam.computergames.tag;

import by.epam.computergames.entity.Genre;
import by.epam.computergames.exception.IncorrectDataException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class GenreTag extends TagSupport
{
    private int idGenre;
    private static final String NOT_DEFINED="not_defined";

    public void setIdGenre(int idGenre)
    {
        this.idGenre = idGenre;
    }

    @Override
    public int doStartTag()
    {
        JspWriter writer=pageContext.getOut();
        try
        {
            try
            {
                Genre genre=Genre.getGenre(idGenre);
                String genreValue=genre.getValue();
                writer.write("<fmt:message key=\"interactive_movie\"/>");
                writer.write("<br>");
            }
            catch (IncorrectDataException e)
            {
                //todo лог
                writer.write("<fmt:message key=\""+NOT_DEFINED+"\"/>");
            }
        }
        catch (IOException e)
        {
            //todo log
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException
    {

        return EVAL_PAGE;
    }
}
