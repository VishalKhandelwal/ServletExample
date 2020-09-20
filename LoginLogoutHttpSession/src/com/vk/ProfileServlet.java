package com.vk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");  
	   java.io.PrintWriter out=response.getWriter();  
	        request.getRequestDispatcher("link.html").include(request, response);  
	          
	       javax.servlet.http.HttpSession session=request.getSession(false);  
	        if(session!=null){  
	        String name=(String)session.getAttribute("name");  
	          
	        out.print("Hello, "+name+" Welcome to Profile");  
	        }  
	        else{  
	            out.print("Please login first");  
	            request.getRequestDispatcher("login.html").include(request, response);  
	        }  
	        out.close();  
	}

}
