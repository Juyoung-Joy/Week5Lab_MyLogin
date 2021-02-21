package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

/**
 *
 * @author 824664
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();        
        String action = request.getParameter("logout");
        
        if(action != null){
           session.invalidate();
           request.setAttribute("message", "Successfully logged out");
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                   .forward(request, response); 
           return;
        }
        
        String username = (String) session.getAttribute("username");
        
        if (username != null ) {
            session.setAttribute("username", username);
            response.sendRedirect("home");
        }
        else { 
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response); 
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountService account = new AccountService();
        User user = null;
        
        if(username!= null && password != null && !username.equals("") && !password.equals("")){
        user = account.login(username, password);

        }
        
        if (user != null) {
            session.setAttribute("username", username);
            response.sendRedirect("home");
        } else {
            request.setAttribute("message", "Incorrect user or password. Please try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
        

}
