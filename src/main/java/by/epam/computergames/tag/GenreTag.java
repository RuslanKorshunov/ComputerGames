package by.epam.computergames.tag;

import by.epam.computergames.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class GenreTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(GenreTag.class);
    private int idGenre;
    private static final String NOT_DEFINED = "genre.unknown";

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    @Override
    public int doStartTag() {
        JspWriter writer = pageContext.getOut();
        try {
            Genre genre = Genre.getGenre(idGenre);
            String genreValue = genre.getValue();
            writer.write("<fmt:message key=\"interactive_movie\"/>");
            writer.write("<br>");
        } catch (IOException e) {
            logger.error(e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;
    }
}