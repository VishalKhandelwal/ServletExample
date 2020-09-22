package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/search")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String databaseURL, username, password;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext context = config.getServletContext();
	      databaseURL = context.getInitParameter("databaseURL");
	      username = context.getInitParameter("username");
	      password = context.getInitParameter("password");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html;charset=UTF-8");
	      PrintWriter out = response.getWriter();
	 
	      Connection conn = null;
	      Statement stmt = null;
	 
	      try {
	         // Retrieve and process request parameters: "author" and "search"
	         String author = request.getParameter("author");
	         boolean hasAuthorParam = author != null && !author.equals("Select...");
	         String searchWord = request.getParameter("search");
	         boolean hasSearchParam = searchWord != null && ((searchWord = searchWord.trim()).length() > 0);
	 
	         out.println("<html><head><title>Query Results</title></head><body>");
	         out.println("<h2>YAEBS - Query Results</h2>");
	 
	         if (!hasAuthorParam && !hasSearchParam) {  // No params present
	            out.println("<h3>Please select an author or enter a search term!</h3>");
	            out.println("<p><a href='start'>Back to Select Menu</a></p>");
	         } else {
	            conn = DriverManager.getConnection(databaseURL, username, password);
	            stmt = conn.createStatement();
	 
	            // Form a SQL command based on the param(s) present
	            StringBuilder sqlStr = new StringBuilder();  // more efficient than String
	            sqlStr.append("SELECT * FROM books WHERE qty > 0 AND (");
	            if (hasAuthorParam) {
	               sqlStr.append("author = '").append(author).append("'");
	            }
	            if (hasSearchParam) {
	               if (hasAuthorParam) {
	                  sqlStr.append(" OR ");
	               }
	               sqlStr.append("author LIKE '%").append(searchWord)
	                     .append("%' OR title LIKE '%").append(searchWord).append("%'");
	            }
	            sqlStr.append(") ORDER BY author, title");
	            //System.out.println(sqlStr);  // for debugging
	            ResultSet rset = stmt.executeQuery(sqlStr.toString());
	 
	            if (!rset.next()) {  // Check for empty ResultSet (no book found)
	               out.println("<h3>No book found. Please try again!</h3>");
	               out.println("<p><a href='start'>Back to Select Menu</a></p>");
	            } else {
	               // Print the result in an HTML form inside a table
	               out.println("<form method='get' action='order'>");
	               out.println("<table border='1' cellpadding='6'>");
	               out.println("<tr>");
	               out.println("<th>&nbsp;</th>");
	               out.println("<th>AUTHOR</th>");
	               out.println("<th>TITLE</th>");
	               out.println("<th>PRICE</th>");
	               out.println("<th>QTY</th>");
	               out.println("</tr>");
	 
	               // ResultSet's cursor now pointing at first row
	               do {
	                  // Print each row with a checkbox identified by book's id
	                  String id = rset.getString("id");
	                  out.println("<tr>");
	                  out.println("<td><input type='checkbox' name='id' value='" + id + "' /></td>");
	                  out.println("<td>" + rset.getString("author") + "</td>");
	                  out.println("<td>" + rset.getString("title") + "</td>");
	                  out.println("<td>$" + rset.getString("price") + "</td>");
	                  out.println("<td><input type='text' size='3' value='1' name='qty" + id + "' /></td>");
	                  out.println("</tr>");
	               } while (rset.next());
	               out.println("</table><br />");
	 
	               // Ask for name, email and phone using text fields (arranged in a table)
	               out.println("<table>");
	               out.println("<tr><td>Enter your Name:</td>");
	               out.println("<td><input type='text' name='cust_name' /></td></tr>");
	               out.println("<tr><td>Enter your Email (user@host):</td>");
	               out.println("<td><input type='text' name='cust_email' /></td></tr>");
	               out.println("<tr><td>Enter your Phone Number (8-digit):</td>");
	               out.println("<td><input type='text' name='cust_phone' /></td></tr></table><br />");
	 
	               // Submit and reset buttons
	               out.println("<input type='submit' value='ORDER' />");
	               out.println("<input type='reset' value='CLEAR' /></form>");
	 
	               // Hyperlink to go back to search menu
	               out.println("<p><a href='start'>Back to Select Menu</a></p>");
	            }
	         }
	         out.println("</body></html>");
	      } catch (SQLException ex) {
	         out.println("<h3>Service not available. Please try again later!</h3></body></html>");
	         Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
	      } finally {
	         out.close();
	         try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	         } catch (SQLException ex) {
	            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
	         }
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
