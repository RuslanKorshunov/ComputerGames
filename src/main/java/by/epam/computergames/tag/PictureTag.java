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
    private int pageNumber;

    public void setDeliveries(List<PictureDelivery> deliveries)
    {
        this.deliveries = deliveries;
    }
    public void setPageNumber(int pageNumber){this.pageNumber=pageNumber;}

    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            String contextPath=pageContext.getServletContext().getContextPath();
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

    @Override
    public int doEndTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            writer.write("<form action=\"ControlServlet\" method=\"get\">\n" +
                    "<input type=\"submit\" name=\"command\" value=\"forward\">\n" +
                    "<input type=\"submit\" name=\"command\" value=\"backward\">\n" +
                    "<input type=\"hidden\" name=\"page_number\" value=\""+pageNumber+"\">\n" +
                    "</form>");
        }
        catch (IOException e)
        {
            //TODO добавить лог
        }
        return EVAL_PAGE;
    }
}
