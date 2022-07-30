/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhis
 */
public class hospital_serv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int status  = 0;
            int slnumber = 0;
            String doa= request.getParameter("doa");
           
          
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-DENOOO4B:1521:XE","system", "system")) {
                {
                  
                    
                   
                    
                    Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rst = st.executeQuery("select slno from patient");
                    if(rst.last())
                    {
                        slnumber = rst.getInt("slno")+1;
                        
                    }
                    
                    
                    
                    PreparedStatement pst = con.prepareStatement("insert into patient(SLNO,NAME,EMAIL,MOBILE,GENDER,DOA,AGE,ISSUE,STATUS) values(?,?,?,?,?,?,?,?,?)");
                    
                    pst.setInt(1, slnumber);
                    pst.setString(2, request.getParameter("fname"));
                    pst.setString(3, request.getParameter("email"));
                    pst.setString(4, request.getParameter("mob"));
                    pst.setString(5, request.getParameter("gen"));
                    pst.setString(6, doa);
                    pst.setString(7, request.getParameter("age"));
                    pst.setString(8, request.getParameter("issue"));
                    pst.setInt(9,status);
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        out.println("<html> <center> <h2> Appointment done please Visit Hospital by 10:00 AM on " +doa+ " </h2> <a href=\"index.html\">click here to go to home page</a></center></html>");
                        
                    }
                    else{
                        out.println("<html> <center> <h2>We are not surving on the appointment date given by you  </h2> <a href=\"index.html\">click here to go to home page</a></center></html>");
                    }
                    
                    
                    
                    
                    
                    
                }  
            con.close();
            } 
            
        }
        catch(Exception e)
            {
        out.println(e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(hospital_serv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(hospital_serv.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(hospital_serv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(hospital_serv.class.getName()).log(Level.SEVERE, null, ex);
        }
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
