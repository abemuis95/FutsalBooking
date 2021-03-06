/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;


import bean.ReservedCourt;
import bean.Booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "GetTimeCourtByDateServlet", urlPatterns = {"/bookonline/time-court"})
public class GetTimeCourtByDateServlet extends HttpServlet {
    
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
        ArrayList<ReservedCourt> courtCodeList = new <ReservedCourt>ArrayList();
        Booking booking = null;
        ReservedCourt rc = null;
        
        HttpSession session = request.getSession();
        
        if(request.getParameter("scheduleDate") != null){
            String date = request.getParameter("scheduleDate");
            booking = new Booking();
            //out.println(date);
            
            //parse date string to Date object
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date schedule = new Date();
            try {
                schedule = formatter.parse(date);
            } catch (Exception ex) {
            }
            
            //convert date string to MYSQL date
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.format(schedule);
            booking.setScheduleDate(date);
            
            try {
                schedule = formatter.parse(date);
            } catch (Exception ex) {
            }
            
            
            
            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectReservedCourtViaDate();
                preparedStatement.setString(1, date);
                ResultSet rs = preparedStatement.executeQuery();
                
                while (rs.next()){
                    String code = rs.getString("code");
                    int status = rs.getInt("status");
                    out.println(code);
                    
                    rc = new ReservedCourt();
                    rc.setCode(code);
                    rc.setStatus(status);
                    
                    courtCodeList.add(rc);
                }
                
                //convert mysql date to MY date (for use on bookcourt.jsp page)
                formatter = new SimpleDateFormat("dd-MMMM-yyyy");
                date = formatter.format(schedule);
                
                session.setAttribute("booking", booking);
                request.setAttribute("reservedCourt", courtCodeList);
                request.setAttribute("scheduleDate", date);
                sendPage(request, response, "/pages/booking/bookcourt.jsp");
                
            } catch(SQLException ex){
                
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
