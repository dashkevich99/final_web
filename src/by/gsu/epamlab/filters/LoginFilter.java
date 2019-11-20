package by.gsu.epamlab.filters;

import by.gsu.epamlab.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/upload.jsp"})
public class LoginFilter implements Filter {
    private static final String USER = "user";
    private static final String URL_LOGIN = "/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        User user = (User) session.getAttribute(USER);
        if(user == null){
            session.invalidate();
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendRedirect(URL_LOGIN);
            return;
        }
    filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
