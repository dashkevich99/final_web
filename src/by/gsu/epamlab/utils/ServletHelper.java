package by.gsu.epamlab.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHelper extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String KEY_ERROR_MESSAGE = "errorMessage";
	private static final String URL_LOGIN = "/login.jsp";

	// to forward to a view layer
	public  void  forward(String url, HttpServletRequest request,
							HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// to forward back with an error message
	public  void forwardError(String error, String message, HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(KEY_ERROR_MESSAGE, message);
		forward(URL_LOGIN, request, response);
	}
}
