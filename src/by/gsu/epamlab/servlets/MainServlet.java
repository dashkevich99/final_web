package by.gsu.epamlab.servlets;

import by.gsu.epamlab.utils.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends ServletHelper {
    private final static String JSP_INDEX = "index.jsp";
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(JSP_INDEX, request, response);
    }
}