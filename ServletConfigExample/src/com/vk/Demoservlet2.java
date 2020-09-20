package com.vk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demoservlet
 */
@WebServlet(name="/Demoservlet2",initParams={@WebInitParam(name="Param1",value="Param 1 Value",description="param1")})
public class Demoservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	      
	    ServletConfig config=getServletConfig();  
	    String driver=config.getInitParameter("Param1");  
	    out.print("Driver is: "+driver);  
	          
	    out.close();  
	      
	}

}
