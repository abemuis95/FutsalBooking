/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

import bean.Booking;
import bean.Customer;
import bean.Court;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import jdbc.JDBCUtility;

/**
 *
 * @author USER
 */
@WebServlet(name = "SearchBookingServlet", urlPatterns = {"/bookonline/search-book"})
public class SearchBookingServlet extends HttpServlet {
    
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
        HttpSession session = request.getSession();
        
        String uniqueID = request.getParameter("uniqueID");
        Booking booking = null;
        Customer cust = null;
        ArrayList<Court> courts = new <Court>ArrayList();
        
        try {                    
            PreparedStatement preparedStatement = jdbcUtility.getPsSelectBookingViaUniqueID();
            preparedStatement.setString(1, uniqueID);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                
                //get date data from database
                String bookingDate = rs.getString("bookingDate");
                String scheduleDate = rs.getString("scheduleDate");
                
                //parse bookingDate string to Date object
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                try {
                    date = formatter.parse(bookingDate);
                } catch (Exception ex) {
                }

                //convert MYSQL format to other format (bookingDate)
                formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss a");
                bookingDate = formatter.format(date);
                
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = formatter.parse(scheduleDate);
                } catch (Exception ex) {
                }
                
                //convert MYSQL format to other format (bookingDate)
                formatter = new SimpleDateFormat("dd MMMM yyyy");
                scheduleDate = formatter.format(date);
                
                booking = new Booking();
                booking.setUniqueID(uniqueID);
                booking.setBookingDate(bookingDate);
                booking.setScheduleDate(scheduleDate);
                booking.setAmountAfter(rs.getString("amountAfter"));
                booking.setStatus(rs.getString("status"));
                booking.setReceipt(rs.getString("receipt"));
                booking.setReason(rs.getString("reason"));
            }
            
           
        }
	catch (SQLException ex)
	{
            out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
        
        //GET CUSTOMER
        try {                    
            PreparedStatement preparedStatement = jdbcUtility.getPsSelectCustomerViaUniqueID();
            preparedStatement.setString(1, uniqueID);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                cust = new Customer();
                String name = rs.getString("name");
                cust.setName(name);
            }
            
           
        }
	catch (SQLException ex)
	{
            out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
        
        //GET CUSTOMER
        try {                    
            PreparedStatement preparedStatement = jdbcUtility.getPsSelectReservedCourtViaUniqueID();
            preparedStatement.setString(1, uniqueID);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String timeSlot = "";
                Court c = new Court();
                int code = rs.getInt("code");
                
                //get time slot base on the code                
                switch(code/10){
                    case 1:
                        timeSlot = "18:00 - 19:00";
                        break;
                    case 2:
                        timeSlot = "19:00 - 20:00";
                        break;
                    case 3:
                        timeSlot = "20:00 - 21:00";
                        break;
                    case 4:
                        timeSlot = "21:00 - 22:00";
                        break;
                    case 5:
                        timeSlot = "22:00 - 22:30";
                        break;
                }
                
                c.setTimeSlot(timeSlot);
                c.setCourtNumber(code%10);
                courts.add(c);
            }
            
           
        }
	catch (SQLException ex)
	{
            out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
        
        if(booking != null){
            request.setAttribute("booking", booking);
            request.setAttribute("customer", cust);
            request.setAttribute("courts", courts);
            sendPage(request, response, "/pages/booking/searchbook.jsp");
        }
        else{
            boolean notFound = true;
            session.setAttribute("bookNotFound", notFound);
            response.sendRedirect(request.getContextPath() + "/bookonline");
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
