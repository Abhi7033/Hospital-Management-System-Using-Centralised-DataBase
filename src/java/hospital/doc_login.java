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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhis
 */
public class doc_login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            String mob= request.getParameter("mob");
            String pwd= request.getParameter("pwd");
            
             Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-DENOOO4B:1521:XE","system", "system")) {
                {
                    Statement stm =con.createStatement();
                    ResultSet rs= stm.executeQuery ("select Name from doctors where mobile= '"+mob+"' and password='"+pwd+"'");
                    
                    
                    
                    if(rs.next()){
                       String name = rs.getString("NAME");

                        out.println("<html> <center> <h2> Successfully Logged In </h2><h1 style =\"color:tomato;\">Welcome "+name+" </h1> <p style =\"color:blue;\">List of Patients </p></center></html>");
                        
                        out.print("<center><table border=1  height:90px; width: 100%; bgcolor=\"\"><tr><th>Name</th><th>Gender</th><th>PATIENT AGE</th><th>Issue</th><th>Patient Mobile</th></tr>");
                        ResultSet rst = stm.executeQuery("select name,gender,age,issue,mobile from patient where status = '0'");
                        while(rst.next())
                        {
                             out.print("<tr><td>");
                             out.println(rst.getObject(1).toString()); 
                             out.print("</th><th>");
                            out.println(rst.getObject(2).toString());
                            out.print("</th><th>");
                            out.println(rst.getObject(3).toString());
                            out.print("</th><th>");
                            out.println(rst.getObject(4).toString());
                            out.print("</th><th>");
                            String id= rst.getObject(5).toString();
                            out.print("<form action=\"patientPres\" method=\"post\"><input type=\"buttom\" value=\""+id+"\" name=\"patPres\"> <input type=\"submit\" value=\"AddPres.\"></form>  ");
                            out.print("</th></td>");
                            
                        }
                        out.println("<script> </script>");
                         
                        //else
                        //{
                           // out.println("<html> alert('No patient') </html>")
                        //}
                    }
                    else{
                        out.println("<html><center><h2>Email or password is invalid please try again</h2> <a href=\"drlogin.html\">click here to Login</a></center></html>");
                    }
                    out.print("</table><a href=\"index.html\">click here to go to homepage</a></center>");
                    
                    
                    
                }   }      
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
