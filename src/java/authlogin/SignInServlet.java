/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authlogin;

import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCUtility;

/**
 *
 * @author USER
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/SignIn"})
public class SignInServlet extends HttpServlet {
    
    private JDBCUtility jdbcUtility;
    private Connection con;
    
    public void init() throws ServletException
    {
        String driver = "com.mysql.jdbc.Driver";

        String dbName = "db_futsal";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JDBCUtility(driver,
                                      url,
                                      userName,
                                      password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
        jdbcUtility.prepareSQLStatement();
    }    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        User profile = null;
        //Get the session object
	HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);

        if (session.getAttribute("userSession") != null) {
            response.sendRedirect("ksc-admin/Home"); //redirect to Home servlet
        } else {
            String username = request.getParameter("username");
            String pw = request.getParameter("password");

            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsLoginViaUsernamePassword();
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, pw);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    
                    String dateRegistered = rs.getString("dateregistered");
                    //parse date string to Date object
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date schedule = new Date();
                    try {
                        schedule = formatter.parse(dateRegistered);
                    } catch (Exception ex) {
                    }

                    //convert MYSQL format to other format
                    formatter = new SimpleDateFormat("MMMM. yyyy");
                    dateRegistered = formatter.format(schedule);
                    
                    profile = new User();
                    profile.setUsername(username);
                    profile.setName(rs.getString("name"));
                    profile.setFullname(rs.getString("fullname"));
                    profile.setRole(rs.getInt("role"));
                    profile.setDateRegistered(dateRegistered);
                }
            } catch (SQLException ex) {
            }
            
            if (profile != null) {
                session.setAttribute("userSession", profile);
                response.sendRedirect("ksc-admin/Home"); //redirect to Home servlet
            } else {
                boolean loginError = true;
                session.setAttribute("loginError", loginError);
                response.sendRedirect("ksc-admin");
            }

        }

        
     
        
                                   
        
    }
    
    void sendPage(HttpServletRequest req, HttpServletResponse res, String fileName) throws ServletException, IOException
    {
        // Get the dispatcher; it gets the main page to the user
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fileName);

	if (dispatcher == null)
	{
            System.out.println("There was no dispatcher");
	    // No dispatcher means the html file could not be found.
	    res.sendError(res.SC_NO_CONTENT);
	}
	else
	    dispatcher.forward(req, res);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
