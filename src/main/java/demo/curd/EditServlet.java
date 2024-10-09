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
public class EditServlet extends HttpServlet {

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
            int userId = Integer.parseInt(request.getParameter("id"));
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String kq = "";
            String name = "", pass = "", mail = "", country = "";

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demodb", "sa", "123");
                ps = con.prepareStatement("SELECT * FROM users where id=?");
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    name = rs.getString("name");
                    pass = rs.getString("password");
                    mail = rs.getString("email");
                    country = rs.getString("country");
                }

            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }           
            kq += "<form action='UpdateServlet' method='post'>";
            kq += "<table border='0'>";
            kq += "<input type='hidden' name='id' value='" + userId + "'/>";
            kq += "<tr><td>Name: <td><input type='text' name='name' value='" + name + "'/></td></td></tr>";
            kq += "<tr><td>Password: <td><input type='password' name='pass' value='" + pass + "'/></td></td></tr>";
            kq += "<tr><td>Email: <td><input type='email' name='mail' value='" + mail + "'/></td></td></tr>";
            kq += "<tr><td>Country: </td><td><select name='country'>";
            String[] countries = {"Việt Nam", "US", "UK", "Others"};
            for (String c : countries) {
                if (c.equals(country)) {
                    kq += "<option value='" + c + "' selected>" + c + "</option>";
                } else {
                    kq += "<option value='" + c + "'>" + c + "</option>";
                }
            }
            kq += "</select></td></tr>";
            kq += "<tr><td><input type='submit' value='Save & Edit'/></td></tr>";
            kq += "</table>";
            kq += "</form>";
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");
            out.println("</head>");
            out.println("<h1>Update User</h1>");
            out.println(kq);
            out.println("<body>");
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
