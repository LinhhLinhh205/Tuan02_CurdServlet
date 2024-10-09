/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package demo.curd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ViewServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String kq="";
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demodb","sa","123");
                ps=con.prepareStatement("select * from users");
                rs=ps.executeQuery();
                kq+="<table border=1>";
                kq+="<tr><td>Id</td><td>Name</td><td>Password</td><td>Email</td><td>Country</td><td>Edit</td><td>Delete</td></tr>";
                while (rs.next()) {
                    kq+="<tr>";
                    kq+="<td>"+rs.getInt(1)+"</td>";
                    kq+="<td>"+rs.getString(2)+"</td>";
                    kq+="<td>"+rs.getString(3)+"</td>";
                    kq+="<td>"+rs.getString(4)+"</td>";
                    kq+="<td>"+rs.getString(5)+"</td>";
                    kq+="<td> <a href='EditServlet?id="+rs.getInt(1)+"'> Edit</a></td>";
                    kq+="<td> <a href='DeleteServlet?id="+rs.getInt(1)+"'> Delete</a></td>";
                    kq+="</tr>";                   
                }
                kq+="</table>";
                con.close();
            }catch(Exception ex){
                System.out.println("Error: "+ex.toString());
            }                                                                                                          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewServlet</title>");            
            out.println("</head>");
            out.println("<body>"); 
            out.println("<a href='index.html'>Add New Users</a>");
            out.println("<br>");            
            out.println("<h1>Users List</h1>");
            out.println(kq);
            out.println("</body>");
            out.println("</html>");
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
