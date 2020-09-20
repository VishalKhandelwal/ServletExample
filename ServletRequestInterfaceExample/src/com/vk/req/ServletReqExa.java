package com.vk.req;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletReqExa
 */
@WebServlet("/ServletReqExa")
public class ServletReqExa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Whta is="+response.getWriter().append("Served at: ").append(request.getContextPath()));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String Name = request.getParameter("name");//will return value
		pw.println("Welcome"+Name);
		pw.close();
	}

}
