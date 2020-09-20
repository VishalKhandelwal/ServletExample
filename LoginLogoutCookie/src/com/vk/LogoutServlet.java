package com.vk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)  
             throws ServletException, IOException {  
response.setContentType("text/html");  
PrintWriter out=response.getWriter();  


request.getRequestDispatcher("link.html").include(request, response);  

Cookie ck=new Cookie("name","");  
ck.setMaxAge(0);  
response.addCookie(ck);  

out.print("you are successfully logged out!");  
}  

}
