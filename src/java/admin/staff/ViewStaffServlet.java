/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.staff;

import bean.Coupon;
import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet(name = "ViewStaffServlet", urlPatterns = {"/ksc-admin/staff"})
public class ViewStaffServlet extends HttpServlet {
    
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
        HttpSession session = request.getSession(false);
        if((session != null) && (session.getAttribute("userSession") != null)){
            
            User user = (User)session.getAttribute("userSession");
            if(user.getRole() != 2){
                response.sendRedirect(request.getContextPath() + "/ksc-admin/pagenotfound" );
            }else{
                ArrayList<User> staffArray = new <User>ArrayList();

                try {
                    ResultSet rs = jdbcUtility.getPsSelectStaff().executeQuery();

                    while (rs.next()) {
                        //get data from database
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        String fullname = rs.getString("fullname");
                        String dateRegistered = rs.getString("dateregistered");

                        //parse dateRegistered string to Date object
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        try {
                            date = formatter.parse(dateRegistered);
                        } catch (Exception ex) {
                        }

                        //convert MYSQL format to other format
                        formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss a");
                        dateRegistered = formatter.format(date);

                        //create User object 
                        User staff = new User();
                        staff.setId(id);
                        staff.setUsername(username);
                        staff.setFullname(fullname);
                        staff.setDateRegistered(dateRegistered);

                        // add User object to araylist
                        staffArray.add(staff);


                    }
                } catch (SQLException ex) {
                    out.println(ex);
                }

                request.setAttribute("staff", staffArray);

                sendPage(request, response, "/pages/admin/staff.jsp");
            }
            
                
        }else{
            response.sendRedirect(request.getContextPath() + "/ksc-admin" );
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
