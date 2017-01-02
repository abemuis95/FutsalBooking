/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.*;
import java.sql.*;

/**
 *
 * @author U
 */
public class JDBCUtility 
{
   Connection con;
   String driver;
   String url;
   String userName;
   String password;
   PreparedStatement psLoginViaUsernamePassword = null;
   PreparedStatement psSelectReservedCourtViaDate = null;
   PreparedStatement psSelectCouponRateViaCouponCode = null;
   PreparedStatement psInsertBooking = null;
   PreparedStatement psInsertCustomer = null;
   PreparedStatement psInsertReservedCourt = null;
   PreparedStatement psInsertCoupon = null;
   PreparedStatement psInsertMember = null;
   PreparedStatement psInsertStaff = null;
   PreparedStatement psSelectBooking = null;
   PreparedStatement psSelectCoupon = null;
   PreparedStatement psSelectMember = null;
   PreparedStatement psSelectStaff = null;
   PreparedStatement psSelectBookingViaUniqueID = null;
   PreparedStatement psSelectCustomerViaUniqueID = null;
   PreparedStatement psSelectReservedCourtViaUniqueID = null;
   PreparedStatement psSelectReservedCourtForMonth = null;
   PreparedStatement psSelectMemberViaNRIC = null;
   PreparedStatement psUpdateBookingStatus = null;  
   PreparedStatement psUpdateBookingReason = null; 
   PreparedStatement psUpdateReservedCourtStatus = null;
   PreparedStatement psDeleteReservedCourt = null;
   PreparedStatement psDeleteBooking = null;
   PreparedStatement psDeleteCoupon = null;
   PreparedStatement psDeleteMember = null;
   PreparedStatement psDeleteStaff = null;
   PreparedStatement psDeleteCustomer = null;
   private PreparedStatement psEditMember = null;
   

   //use this constructor if using ConnectionPool
   public JDBCUtility()
   {
   }

   //use this constructor if not using ConnectionPool
   //ConnectionPool is used for multi user!
   public JDBCUtility(String driver,
                      String url,
                      String userName,
                      String password)
   {
      this.driver = driver;
      this.url = url;
      this.userName = userName;
      this.password = password;
   }

   public  void jdbcConnect()
   {
      try
	   {
         Class.forName (driver);
         con = DriverManager.getConnection(url, userName, password);
         DatabaseMetaData dma = con.getMetaData ();
         System.out.println("\nConnected to " + dma.getURL());
         System.out.println("Driver       " + dma.getDriverName());
         System.out.println("Version      " + dma.getDriverVersion());
         System.out.println("");
	   }
	   catch (SQLException ex)
	   {
         while (ex != null)
         {
		      System.out.println ("SQLState: " +
                                 ex.getSQLState ());
            System.out.println ("Message:  " +
                                 ex.getMessage ());
		      System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
            ex = ex.getNextException ();
		      System.out.println ("");
         }

         System.out.println("Connection to the database error");
	   }
	   catch (java.lang.Exception ex)
	   {
         ex.printStackTrace ();
	   }
   }

   public Connection jdbcGetConnection()
   {
   	return con;
   }

   public void jdbcConClose()
   {
   	try
   	{
         con.close();
   	}
   	catch (Exception ex)
	   {
	   }
   }

    public void prepareSQLStatement()
    {      
        try
        {
            String sqlLoginViaUsernamePassword = "SELECT * FROM user WHERE username = ? AND password = ?";
            psLoginViaUsernamePassword = con.prepareStatement(sqlLoginViaUsernamePassword);
            
            String sqlSelectReservedCourtViaDate = "SELECT * FROM reservedcourt WHERE date = ?";
            psSelectReservedCourtViaDate = con.prepareStatement(sqlSelectReservedCourtViaDate);
            
            String sqlSelectCouponRateViaCouponCode = "SELECT * FROM coupon WHERE couponcode = ?";
            psSelectCouponRateViaCouponCode = con.prepareStatement(sqlSelectCouponRateViaCouponCode);
            
            String sqlInsertBooking = "INSERT INTO booking("
                    + "uniqueID, "
                    + "bookingDate, "
                    + "scheduleDate, "
                    + "amountBefore, "
                    + "amountAfter, "
                    + "receipt, "
                    + "couponCode, "
                    + "dateUpdate) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            psInsertBooking = con.prepareStatement(sqlInsertBooking);
            
            String sqlInsertCustomer = "INSERT INTO customer("
                    + "uniqueID, "
                    + "name, "
                    + "nric, "
                    + "telno, "
                    + "email) "
                    + "VALUES(?, ?, ?, ?, ?)";
            psInsertCustomer = con.prepareStatement(sqlInsertCustomer);
            
            String sqlInsertReservedCourt = "INSERT INTO reservedcourt("
                    + "uniqueID, "
                    + "code, "
                    + "date) "
                    + "VALUES(?, ?, ?)";
            psInsertReservedCourt = con.prepareStatement(sqlInsertReservedCourt);
            
            String sqlInsertCoupon = "INSERT INTO coupon("
                    + "couponcode, "
                    + "discountrate, "
                    + "dateCreated) "
                    + "VALUES(?, ?, ?)";
            psInsertCoupon = con.prepareStatement(sqlInsertCoupon);
            
            String sqlInsertMember = "INSERT INTO member("
                    + "memberNo, "
                    + "name,"
                    + "NRIC,"
                    + "dateRegistered, "
                    + "birthDate, "
                    + "address,"
                    + "telNo, "
                    + "email, "
                    + "dateUpdate) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psInsertMember = con.prepareStatement(sqlInsertMember);
            
            String sqlInsertStaff = "INSERT INTO user("
                    + "username, "
                    + "password,"
                    + "fullname, "
                    + "name,"
                    + "dateregistered, "
                    + "role) "
                    + "VALUES(?, ?, ?, ?, ?, 1)";
            psInsertStaff = con.prepareStatement(sqlInsertStaff);
            
            String sqlSelectBooking = "SELECT * FROM booking";
            psSelectBooking = con.prepareStatement(sqlSelectBooking);
            
            String sqlSelectCoupon = "SELECT * FROM coupon";
            psSelectCoupon = con.prepareStatement(sqlSelectCoupon);
            
            String sqlSelectMember = "SELECT * FROM member";
            psSelectMember = con.prepareStatement(sqlSelectMember);
            
            String sqlSelectStaff = "SELECT * FROM user WHERE role = 1";
            psSelectStaff = con.prepareStatement(sqlSelectStaff);
            
            String sqlSelectBookingViaUniqueID = "SELECT * FROM booking WHERE uniqueID = ?";
            psSelectBookingViaUniqueID = con.prepareStatement(sqlSelectBookingViaUniqueID);
            
            String sqlSelectCustomerViaUniqueID = "SELECT * FROM customer WHERE uniqueID = ?";
            psSelectCustomerViaUniqueID = con.prepareStatement(sqlSelectCustomerViaUniqueID);
            
            String sqlSelectReservedCourtViaUniqueID = "SELECT * FROM reservedcourt WHERE uniqueID = ?";
            psSelectReservedCourtViaUniqueID = con.prepareStatement(sqlSelectReservedCourtViaUniqueID);
            
            String sqlSelectReservedCourtForMonth = "SELECT * FROM booking "
                    + "WHERE (scheduleDate BETWEEN ? AND ?) ";
            psSelectReservedCourtForMonth = con.prepareStatement(sqlSelectReservedCourtForMonth);
            
            String sqlSelectMemberViaNRIC = "SELECT * FROM member WHERE NRIC = ?";
            psSelectMemberViaNRIC = con.prepareStatement(sqlSelectMemberViaNRIC);
            
            String sqlUpdateBookingStatus = "UPDATE booking SET status = ?, dateUpdate = ? WHERE id = ?"; 
            psUpdateBookingStatus = (con.prepareStatement(sqlUpdateBookingStatus));
            
            String sqlUpdateBookingReason = "UPDATE booking SET reason = ? WHERE id = ?"; 
            psUpdateBookingReason = (con.prepareStatement(sqlUpdateBookingReason));
            
            String sqlUpdateReservedCourtStatus = "UPDATE reservedcourt SET status = ? WHERE uniqueID = ?"; 
            psUpdateReservedCourtStatus = (con.prepareStatement(sqlUpdateReservedCourtStatus));
            
            String sqlDeleteReservedCourt = "DELETE FROM reservedcourt WHERE uniqueID = ?";
            psDeleteReservedCourt = con.prepareStatement(sqlDeleteReservedCourt);
            
            String sqlDeleteBooking = "DELETE FROM booking WHERE id = ?";
            psDeleteBooking = con.prepareStatement(sqlDeleteBooking);
            
            String sqlDeleteCustomer = "DELETE FROM customer WHERE uniqueID = ?";
            psDeleteCustomer = con.prepareStatement(sqlDeleteCustomer);
            
            String sqlDeleteCoupon = "DELETE FROM coupon WHERE id = ?";
            psDeleteCoupon = con.prepareStatement(sqlDeleteCoupon);
            
            String sqlDeleteMember = "DELETE FROM member WHERE id = ?";
            psDeleteMember = con.prepareStatement(sqlDeleteMember);
            
            String sqlDeleteStaff = "DELETE FROM user WHERE id = ?";
            psDeleteStaff = con.prepareStatement(sqlDeleteStaff);
            
            String sqlEditMember = "UPDATE member "
                    + "SET address = ?,"
                    + "telNo = ?, "
                    + "email = ?,"
                    + "dateUpdate = ?"
                    + "WHERE id = ?"; 
            psEditMember = (con.prepareStatement(sqlEditMember));
            
        }
	catch (SQLException ex)
	{
            while (ex != null)
            {
                System.out.println ("SQLState: " +
                                 ex.getSQLState ());
                System.out.println ("Message:  " +
                                 ex.getMessage ());
		System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
                ex = ex.getNextException ();
		      System.out.println ("");
            }
            
            System.out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex)
	{
            ex.printStackTrace ();
	}
    }

    /**
     * @return the psLoginViaUsernamePassword
     */
    public PreparedStatement getPsLoginViaUsernamePassword() {
        return psLoginViaUsernamePassword;
    }

    /**
     * @return the psSelectReservedCourtViaDate
     */
    public PreparedStatement getPsSelectReservedCourtViaDate() {
        return psSelectReservedCourtViaDate;
    }

    /**
     * @return the psSelectCouponRateViaCouponCode
     */
    public PreparedStatement getPsSelectCouponRateViaCouponCode() {
        return psSelectCouponRateViaCouponCode;
    }

    /**
     * @return the psInsertBooking
     */
    public PreparedStatement getPsInsertBooking() {
        return psInsertBooking;
    }

    /**
     * @return the psInsertCustomer
     */
    public PreparedStatement getPsInsertCustomer() {
        return psInsertCustomer;
    }

    /**
     * @return the psInsertReservedCourt
     */
    public PreparedStatement getPsInsertReservedCourt() {
        return psInsertReservedCourt;
    }

    /**
     * @return the psInsertCoupon
     */
    public PreparedStatement getPsInsertCoupon() {
        return psInsertCoupon;
    }

    /**
     * @return the psInsertMember
     */
    public PreparedStatement getPsInsertMember() {
        return psInsertMember;
    }

    /**
     * @return the psInsertStaff
     */
    public PreparedStatement getPsInsertStaff() {
        return psInsertStaff;
    }

    /**
     * @return the psSelectBooking
     */
    public PreparedStatement getPsSelectBooking() {
        return psSelectBooking;
    }

    /**
     * @return the psSelectCoupon
     */
    public PreparedStatement getPsSelectCoupon() {
        return psSelectCoupon;
    }

    /**
     * @return the psSelectMember
     */
    public PreparedStatement getPsSelectMember() {
        return psSelectMember;
    }

    /**
     * @return the psSelectStaff
     */
    public PreparedStatement getPsSelectStaff() {
        return psSelectStaff;
    }

    /**
     * @return the psSelectBookingViaUniqueID
     */
    public PreparedStatement getPsSelectBookingViaUniqueID() {
        return psSelectBookingViaUniqueID;
    }

    /**
     * @return the psSelectCustomerViaUniqueID
     */
    public PreparedStatement getPsSelectCustomerViaUniqueID() {
        return psSelectCustomerViaUniqueID;
    }

    /**
     * @return the psSelectReservedCourtViaUniqueID
     */
    public PreparedStatement getPsSelectReservedCourtViaUniqueID() {
        return psSelectReservedCourtViaUniqueID;
    }

    /**
     * @return the psSelectMemberViaNRIC
     */
    public PreparedStatement getPsSelectMemberViaNRIC() {
        return psSelectMemberViaNRIC;
    }

    /**
     * @return the psUpdateBookingStatus
     */
    public PreparedStatement getPsUpdateBookingStatus() {
        return psUpdateBookingStatus;
    }

    /**
     * @return the psDeleteBooking
     */
    public PreparedStatement getPsDeleteBooking() {
        return psDeleteBooking;
    }

    /**
     * @return the psDeleteCoupon
     */
    public PreparedStatement getPsDeleteCoupon() {
        return psDeleteCoupon;
    }

    /**
     * @return the psDeleteMember
     */
    public PreparedStatement getPsDeleteMember() {
        return psDeleteMember;
    }

    /**
     * @return the psDeleteStaff
     */
    public PreparedStatement getPsDeleteStaff() {
        return psDeleteStaff;
    }

    /**
     * @return the psUpdateReservedCourtStatus
     */
    public PreparedStatement getPsUpdateReservedCourtStatus() {
        return psUpdateReservedCourtStatus;
    }

    /**
     * @return the psDeleteReservedCourt
     */
    public PreparedStatement getPsDeleteReservedCourt() {
        return psDeleteReservedCourt;
    }

    /**
     * @return the psUpdateBookingReason
     */
    public PreparedStatement getPsUpdateBookingReason() {
        return psUpdateBookingReason;
    }

    /**
     * @return the psSelectReservedCourtForMonth
     */
    public PreparedStatement getPsSelectReservedCourtForMonth() {
        return psSelectReservedCourtForMonth;
    }

    /**
     * @return the psDeleteCustomer
     */
    public PreparedStatement getPsDeleteCustomer() {
        return psDeleteCustomer;
    }

    /**
     * @return the psEditMember
     */
    public PreparedStatement getPsEditMember() {
        return psEditMember;
    }

    

    

    

   
}
