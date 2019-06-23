package by.epam.computergames.tag;

import by.epam.computergames.command.RequestConst;
import by.epam.computergames.entity.Review;
import by.epam.computergames.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ReviewTag extends TagSupport
{
    private static final Logger logger= LogManager.getLogger(ReviewTag.class);
    private List<Review> reviews;

    public void setReviews(List<Review> reviews)
    {
        this.reviews=reviews;
    }

    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            HttpSession session=pageContext.getSession();
            int role=(int)session.getAttribute(RequestConst.ROLE.getValue());
            writer.write("<div class=\"reviews\">");
            for(Review review: reviews)
            {
                writer.write("<div class=\"review\">");
                writer.write("<h5>"+review.getName()+" "+review.getSurname()+"</h5>");
                writer.write("<h5>"+review.getMark()+"</h5>");
                writer.write("<textarea class=\"review-text\" disabled>"+review.getComment()+"</textarea>");
                if(role==Role.ADMIN.getId())
                {
                    writer.write("<form action=\"ControlServlet\" method=\"get\">");
                    writer.write("<input type=\"submit\" class=\"btn\" value=\"Delete\">");
                    writer.write("<input type=\"hidden\" name=\"command\" value=\"delete_review\">");
                    writer.write("<input type=\"hidden\" name=\"id\" value=\""+review.getIdGame()+"\">");
                    writer.write("<input type=\"hidden\" name=\"login\" value=\""+review.getLogin()+"\">");
                    writer.write("<input type=\"hidden\" name=\"page_number\" value=\"0\">");
                    writer.write("</form>");
                }
                writer.write("</div>");
            }
            writer.write("</div>");
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        return SKIP_BODY;
    }
}