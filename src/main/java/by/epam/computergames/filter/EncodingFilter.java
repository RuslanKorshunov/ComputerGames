package by.epam.computergames.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/")

public class EncodingFilter implements Filter {
    private static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig)
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        servletRequest.setCharacterEncoding(CHARACTER_ENCODING);
        servletResponse.setCharacterEncoding(CHARACTER_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {

    }
}