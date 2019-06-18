package by.epam.computergames.tag;

import by.epam.computergames.entity.PictureDelivery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PictureTag extends TagSupport
{
    private static final Logger logger= LogManager.getLogger(PictureTag.class);

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
                String command="ControlServlet?command="+delivery.getCommand().getValue()+
                                "&id="+delivery.getId();
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
            logger.error(e);
        }
        return SKIP_BODY;
    }
}
