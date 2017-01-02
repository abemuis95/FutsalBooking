/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

import bean.Booking;
import bean.Customer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "InsertBookingServlet", urlPatterns = {"/bookonline/insertbooking"})
@MultipartConfig(location="C:\\Users\\USER\\Documents\\NetBeansProjects\\FutsalBookingSystem\\web\\WEB-INF\\receipt",
                 fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class InsertBookingServlet extends HttpServlet {
    
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
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "receipt";

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
        Booking booking = (Booking) session.getAttribute("booking");
        Customer cust = (Customer)session.getAttribute("customer");
        
        //get booking details
        String uniqueID = booking.getUniqueID();
        String bookingDate = booking.getBookingDate();
        String scheduleDate = booking.getScheduleDate();
        String amountBefore = booking.getAmountBefore();
        String amountAfter = booking.getAmountAfter();
        String couponCode = booking.getCouponCode();
        String courtCode[] = booking.getCourtCode();
        
        //get customer details
        String name = cust.getName();
        String nric = cust.getNric();
        String telNo = cust.getTelno();
        String email = cust.getEmail();
        
        
        ////////////////////////////////////
        /*PART FOR UPLOAD FILE START HERE */
        ///////////////////////////////////
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        String fileName = "";
        for (Part part : request.getParts()) {          
            
            fileName = extractFileName(part);
            String[] fileNameSplits = fileName.split("\\.");
            // extension is assumed to be the last part
            int extensionIndex = fileNameSplits.length - 1;
            // add extension to id
            fileName = uniqueID + "." + fileNameSplits[extensionIndex];
            part.write(fileName);
            //part.write(fileName);
        }
        /*PART FOR UPLOAD FILE END HERE */
        
        out.println(uniqueID);
        out.println(bookingDate);
        out.println(scheduleDate);
        out.println(amountBefore);
        out.println(amountAfter);
        out.println(fileName);
        out.println(couponCode);
        
        //INSERT INTO BOOKING TABLE
        try {                    
            PreparedStatement preparedStatement = jdbcUtility.getPsInsertBooking();
            preparedStatement.setString(1, uniqueID);
            preparedStatement.setString(2, bookingDate);
            preparedStatement.setString(3, scheduleDate);
            preparedStatement.setString(4, amountBefore);
            preparedStatement.setString(5, amountAfter);
            preparedStatement.setString(6, fileName);
            preparedStatement.setString(7, couponCode);
            preparedStatement.setString(8, bookingDate);
            preparedStatement.executeUpdate();
            
           
        }
	catch (SQLException ex)
	{
            out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
        
        //INSERT INTO CUSTOMER TABLE
        try {                    
            PreparedStatement preparedStatement = jdbcUtility.getPsInsertCustomer();
            preparedStatement.setString(1, uniqueID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, nric);
            preparedStatement.setString(4, telNo);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
            
            
        }
	catch (SQLException ex)
	{
            out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
        
        //INSERT INTO RESERVED COURT TABLE
        for(String code : courtCode){
            //update in table
            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsInsertReservedCourt();
                preparedStatement.setString(1, uniqueID);
                preparedStatement.setString(2, code);
                preparedStatement.setString(3, scheduleDate);
                preparedStatement.executeUpdate();

                out.println("<script>");
                out.println("    alert('Booking insert success');");
                out.println("</script>");

            } catch (SQLException ex) {
                out.println("Connection to the database error");
            } catch (java.lang.Exception ex) {
                ex.printStackTrace();
            }
        }
        
        session.removeAttribute("booking");
        session.removeAttribute("customer");
        session.removeAttribute("courts");
        response.sendRedirect(request.getContextPath() + "/bookonline/booksuccess");
        
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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
