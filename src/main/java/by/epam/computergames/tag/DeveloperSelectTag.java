package by.epam.computergames.tag;

import by.epam.computergames.entity.Developer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class DeveloperSelectTag extends TagSupport
{
    private List<Developer> developers;

    public void setDevelopers(List<Developer> developers)
    {
        this.developers = developers;
    }

    @Override
    public int doStartTag()
    {
        try
        {
            JspWriter writer=pageContext.getOut();
            writer.write("<select name=\"developer\">\n");
            writer.write("<option value=\"0\" selected></option>");
            for(Developer developer: developers)
            {
                long idDeveloper=developer.getIdDeveloper();
                String name=developer.getName();
                writer.write("<option value=\""+idDeveloper+"\">"+name+"</option>");
            }
            writer.write("</select>");
        }
        catch (IOException e)
        {
            //todo log
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag()
    {
        return EVAL_PAGE;
    }
}
