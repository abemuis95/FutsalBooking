/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "InsertMemberServlet", urlPatterns = {"/ksc-admin/InsertMemberServlet"})
public class InsertMemberServlet extends HttpServlet {
    
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
            String memberno = request.getParameter("memberno");
            String name = request.getParameter("name");
            String nric = request.getParameter("nric");
            String birthDate = request.getParameter("birthdate");
            String address = request.getParameter("address");
            String telNo = request.getParameter("telno");
            String email = request.getParameter("email");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateRegistered = formatter.format(date);
            
            //parse date string to Date object
            formatter = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                date = formatter.parse(birthDate);
            } catch (Exception ex) {
            }

            //convert MYSQL format to other format
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = formatter.format(date);
            
            try {                    
                PreparedStatement preparedStatement = jdbcUtility.getPsInsertMember();

                preparedStatement.setString(1, memberno);
                preparedStatement.setString(2, name);                
                preparedStatement.setString(3, nric);
                preparedStatement.setString(4, dateRegistered);
                preparedStatement.setString(5, birthDate);
                preparedStatement.setString(6, address);
                preparedStatement.setString(7, telNo);
                preparedStatement.setString(8, email);
                preparedStatement.setString(9, dateRegistered);
                preparedStatement.executeUpdate();


                response.sendRedirect("member");


            }
            catch (SQLException ex)
            {
                out.println("Connection to the database error");
            }
            catch (java.lang.Exception ex)
            {
                ex.printStackTrace ();
            } 
        }
        else{
            response.sendRedirect(request.getContextPath() );
        }
        
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
