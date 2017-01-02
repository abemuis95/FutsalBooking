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
@WebServlet(name = "ViewMemberServlet", urlPatterns = {"/ksc-admin/member"})
public class ViewMemberServlet extends HttpServlet {
    
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
            ArrayList<Member> member = new <Member>ArrayList();

            try {
                ResultSet rs = jdbcUtility.getPsSelectMember().executeQuery();

                while (rs.next()) {
                    
                    int id = rs.getInt("id");
                    String memberNo = rs.getString("memberNo");
                    String name = rs.getString("name");
                    String NRIC = rs.getString("NRIC");
                    String dateRegistered = rs.getString("dateRegistered");
                    
                    //parse date string to Date object
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date schedule = new Date();
                    try {
                        schedule = formatter.parse(dateRegistered);
                    } catch (Exception ex) {
                    }

                    //convert MYSQL format to other format
                    formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss a");
                    dateRegistered = formatter.format(schedule);
                    
                    Member memb = new Member();
                    memb.setId(id);
                    memb.setName(name);
                    memb.setMemberNo(memberNo);
                    memb.setNRIC(NRIC);
                    memb.setDateRegistered(dateRegistered);
                    member.add(memb);
                   

                }
            } catch (SQLException ex) {
                out.println("dalam ex");
            }
            
            String randomNumber = "A" + String.format("0%4d",(1000 + (int)(Math.random() * (1999 - 1000))));
            request.setAttribute("randomNumber", randomNumber);
            request.setAttribute("member", member);

            sendPage(request, response, "/pages/admin/member.jsp");
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
