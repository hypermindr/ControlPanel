package com.hypermindr.controlpanel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportConfigServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String intervalStr = request.getParameter("interval");
		String methodStr = request.getParameter("method");
		String serverStr = request.getParameter("server");

	
		request.setAttribute("givenInterval", intervalStr);
		request.setAttribute("givenMethod", methodStr);
		request.setAttribute("givenServer", serverStr);
		
		request.getRequestDispatcher("view.jsp").forward(request, response);  
	}

}
