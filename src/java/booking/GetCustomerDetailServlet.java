/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

import bean.Booking;
import bean.Court;
import bean.Customer;
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
@WebServlet(name = "GetCustomerDetailServlet", urlPatterns = {"/GetCustDetail"})
public class GetCustomerDetailServlet extends HttpServlet {
    
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
        Customer cust = null;
        ArrayList<Court> courts = new <Court>ArrayList();
        //get booking from session
        Booking booking = (Booking) session.getAttribute("booking");
        
        String couponCode = "NOCOUPON";
        //set couponcode in booking
        booking.setCouponCode(couponCode);
        
        if(request.getParameter("coupon") != null)
            couponCode = request.getParameter("coupon");
        
        String nric = request.getParameter("nric");
        String name = request.getParameter("name");
        String telno = request.getParameter("telno");
        String email = request.getParameter("email");
        
        
        
        
        int discountRate = 0;
        int amount = 0;
        //calculate amount before and after discount rate
        for(String code : booking.getCourtCode()){
            amount += 70;
        }
        
        //set amountBefore in booking
        String amountBefore = Integer.toString(amount);
        booking.setAmountBefore(amountBefore);
        booking.setAmountAfter(amountBefore);
        
        //excute prepared statement to get discountRate from table coupon
        try{
            PreparedStatement preparedStatement = jdbcUtility.getPsSelectCouponRateViaCouponCode();
            preparedStatement.setString(1, couponCode);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                discountRate = rs.getInt("discountrate");
                
                //calculate amountAfter
                amount = amount - (amount * discountRate / 100);
                String amountAfter = Integer.toString(amount);
                booking.setAmountAfter(amountAfter);
                booking.setCouponCode(couponCode);
            }
        }
        catch(SQLException ex){
                out.println("dalam ex");
            }
        
        
        //create bookings date and set it in Booking booking object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String bookingDate = formatter.format(date);
        booking.setBookingDate(bookingDate);
        
        //create uniqueID for booking and set it in Booking booking object
        formatter = new SimpleDateFormat("MMmmHHyy");
        date = new Date();
        String uniqueID = "ksc" + formatter.format(date) + (100 + (int)(Math.random() * (199 - 100)));
        booking.setUniqueID(uniqueID);
        
        //Customer object
        cust = new Customer();
        cust.setNric(nric);
        cust.setName(name);
        cust.setTelno(telno);
        cust.setEmail(email);
        
        //get court and time slot for display
        for (String codes : booking.getCourtCode()) {
            String timeSlot = "";
            Court c = new Court();
            int code = Integer.parseInt(codes);
            //get time slot base on the code                
            switch (code / 10) {
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
            c.setCourtNumber(code % 10);
            courts.add(c);
        }
        
        session.setAttribute("booking", booking);
        session.setAttribute("customer",cust);
        session.setAttribute("courts", courts);
        response.sendRedirect(request.getContextPath() + "/bookonline/payments");
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
