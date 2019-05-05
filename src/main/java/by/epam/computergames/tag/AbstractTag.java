package by.epam.computergames.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public abstract class AbstractTag extends TagSupport
{
    protected List<String> pictures;

    public void setPictures(List<String> pictures)
    {
        this.pictures=pictures;
    }

    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            String contextPath=pageContext.getServletContext().getContextPath();

            writer.write("<div class=\"gallery-section\">");
            writer.write("<div class=\"inner-width\">");
            writer.write("<h1>MyGallery</h1>");
            writer.write("<div class=\"border\"></div>");
            writer.write("<div class=\"gallery\">");
            for (int index = 0; index < 8; index++)
            {
                for(String picture: pictures)
                {
                    String path=contextPath+"/img/"+picture;
                    writer.write("<a href=\"#\" class=\"image\">");
                    writer.write("<img src=\""+path+"\"/></a>");
                    index++;
                }
                writer.write("<a href=\"#\" class=\"image\">");
                writer.write("<img src=\""+contextPath+"/img/not_found.png\"/></a>");
            }
        }
        catch(IOException e)
        {
            //TODO добавить лог
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            writer.write("</div>");
            writer.write("</div>");
            writer.write("</div>");
        }
        catch (IOException e)
        {
            //TODO добавить лог
        }
        return EVAL_PAGE;
    }
}
