package by.epam.computergames.tag;

import by.epam.computergames.entity.PictureDelivery;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PictureTag extends TagSupport
{
    private List<PictureDelivery> deliveries;

    public void setDeliveries(List<PictureDelivery> deliveries)
    {
        this.deliveries = deliveries;
    }

    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            String contextPath=pageContext.getServletContext().getContextPath();

            /*writer.write("<div class=\"gallery-section\">");
            writer.write("<div class=\"inner-width\">");
            writer.write("<h1>MyGallery</h1>");
            writer.write("<div class=\"border\"></div>");
            writer.write("<div class=\"gallery\">");*/
            for(PictureDelivery delivery: deliveries)
            {
                String path=contextPath+"/img/"+delivery.getPicture();
                String command="ControlServlet?command="+delivery.getCommand().getValue()+"&id="+delivery.getId();
                writer.write("<a href=\""+command+"\" class=\"image\">");
                writer.write("<img src=\""+path+"\"/></a>");
            }
            for (int index = 0; index < 8- deliveries.size(); index++)
            {
                writer.write("<a href=\"#\" class=\"image\">");
                writer.write("<img src=\""+contextPath+"/img/not_found.png\"/>");
                writer.write("</a>");
            }
        }
        catch(IOException e)
        {
            //TODO добавить лог
        }
        return SKIP_BODY;
    }

    /*@Override
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
    }*/
}
