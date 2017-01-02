/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.booking;

import bean.Booking;
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
@WebServlet(name = "ViewBookingServlet", urlPatterns = {"/ksc-admin/viewbooking"})
public class ViewBookingServlet extends HttpServlet {
    
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
        ArrayList<Booking> bookings = new <Booking>ArrayList();
        if((session != null) && (session.getAttribute("userSession") != null)){
            try {
                ResultSet rs = jdbcUtility.getPsSelectBooking().executeQuery();

                while (rs.next()) {
                    
                    // get data from database
                    int id = rs.getInt("id");
                    String uniqueID = rs.getString("uniqueID");
                    String bookingDate = rs.getString("bookingDate");
                    String scheduleDate = rs.getString("scheduleDate");
                    String amountAfter = rs.getString("amountAfter");
                    String receipt = rs.getString("receipt");
                    String status = rs.getString("status");
                    
                    ///////////////////////////////////////
                    //change date format for bookingdate//
                    ////////////////////////////////////////
                    //parse date string to Date object
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = formatter.parse(bookingDate);
                    } catch (Exception ex) {
                    }

                    //convert MYSQL format to other format
                    formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss a");
                    bookingDate = formatter.format(date);
                    
                    ///////////////////////////////////////
                    //chage date format for schedule date//
                    //////////////////////////////////////
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = formatter.parse(scheduleDate);
                    } catch (Exception ex) {
                    }
                    
                    //convert MYSQL format to other format
                    formatter = new SimpleDateFormat("dd-MM(MMM)-yyyy");
                    scheduleDate = formatter.format(date);
                    
                    
                    //Coupon object
                    Booking b = new Booking();
                    b.setId(id);
                    b.setUniqueID(uniqueID);
                    b.setAmountAfter(amountAfter);
                    b.setBookingDate(bookingDate);
                    b.setScheduleDate(scheduleDate);
                    b.setReceipt(receipt);
                    b.setStatus(status);
                    
                    //add to ArrayList
                    bookings.add(b);


                }
            } catch (SQLException ex) {
                out.println("dalam ex");
            }

            request.setAttribute("booking", bookings);

            sendPage(request, response, "/pages/admin/adminbooking.jsp");
        }
        else{
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
