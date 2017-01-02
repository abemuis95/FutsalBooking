/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.report;


import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
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
@WebServlet(name = "ViewReportServlet", urlPatterns = {"/ksc-admin/report"})
public class ViewReportServlet extends HttpServlet {
    
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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String yearString = sdf.format(date);
        int year = Integer.parseInt(yearString);
        
        
        HttpSession session = request.getSession(false);
        if((session != null) || (session.getAttribute("userSession") != null)){
            
            User user = (User)session.getAttribute("userSession");
            if(user.getRole() != 2){
                response.sendRedirect(request.getContextPath() + "/ksc-admin/pagenotfound" );
            }else{
                ArrayList<Integer> reportMonth = new <Integer>ArrayList();

                for(int i = 1; i<=12; i++){


                    String month = String.format("%02d", i);
                    String dateStart = year+"-"+month+"-01";
                    String dateEnd;

                    if(i == 12){
                        year += 1;
                        String nextMonth = "01";
                        dateEnd = year+"-"+nextMonth+"-01";
                    }
                    else{

                        String nextMonth = String.format("%02d", i+1);                   
                        dateEnd = year+"-"+nextMonth+"-01";
                    }

                    try {
                        PreparedStatement ps = jdbcUtility.getPsSelectReservedCourtForMonth();
                        ps.setString(1, dateStart);
                        ps.setString(2, dateEnd);
                        ResultSet rs = ps.executeQuery();

                        int count = 0;
                        while (rs.next()) {

                            count += 1;

                        }

                        reportMonth.add(count);

                    } catch (SQLException ex) {
                        out.println("dalam ex");
                    }

                }

                request.setAttribute("reportMonth", reportMonth);

                sendPage(request, response, "/pages/admin/report.jsp");
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
