/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.member;

import bean.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "ViewMemberProfileServlet", urlPatterns = {"/ksc-admin/ViewMemberProfileServlet"})
public class ViewMemberProfileServlet extends HttpServlet {
    
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
            
            String nric = request.getParameter("nric");
            Member m = null;

            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectMemberViaNRIC();
                preparedStatement.setString(1, nric);
                ResultSet rs = preparedStatement.executeQuery();
                
                while(rs.next()){
                    int id = rs.getInt("id");
                    String memberNo = rs.getString("memberNo");
                    String name = rs.getString("name");
                    String NRIC = rs.getString("NRIC");
                    String dateRegistered = rs.getString("dateRegistered");
                    String birthDate = rs.getString("birthDate");
                    String address = rs.getString("address");
                    String telNo = rs.getString("telNo");
                    String email = rs.getString("email");
                    String dateUpdate = rs.getString("dateUpdate");
                    
                    //parse date string to Date object
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Date dt = new Date();
                    Date dt2 = new Date();
                    try {
                        date = formatter.parse(dateRegistered);
                        dt = formatter.parse(dateUpdate);
                        dt2 = sdf.parse(birthDate);
                    } catch (Exception ex) {
                    }

                    //convert MYSQL format to other format
                    formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss a");
                    sdf = new SimpleDateFormat("dd MMM yyyy ");
                    dateRegistered = formatter.format(date);
                    dateUpdate = formatter.format(dt);
                    birthDate = sdf.format(dt2);
                    
                    m = new Member();
                    m.setId(id);
                    m.setMemberNo(memberNo);
                    m.setName(name);
                    m.setAddress(address);
                    m.setBirthDate(birthDate);
                    m.setDateRegistered(dateRegistered);
                    m.setDateUpdate(dateUpdate);
                    m.setEmail(email);
                    m.setNRIC(NRIC);
                    m.setTelNo(telNo);
                    
                }

                request.setAttribute("member", m);

                sendPage(request, response, "/pages/admin/viewmember.jsp");

            } catch (SQLException ex) {
                out.println("dalam ex");
            }
            
        }else {
            
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
