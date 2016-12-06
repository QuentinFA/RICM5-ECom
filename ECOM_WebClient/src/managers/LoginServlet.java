package managers;

import java.io.IOException; 
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import session.UserFacade;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userfacade = new UserFacade();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		// get request parameters for userID and password
		String name = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		User user = userfacade.find(name);
		
		if(user != null){
			
			String pass = user.getPassword();
			
			if( pass.equals(pwd)){
				Cookie loginCookie = new Cookie("user",name);
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				response.sendRedirect("#");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>Le mot de passe est incorrect.</font>");
				rd.include(request, response);
			}
		} else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Cet Utilisateur n'existe pas.</font>");
			rd.include(request, response);
		}


	}

}
