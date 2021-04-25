package at.htl.simpleservlet.boundary;

import at.htl.simpleservlet.controller.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        DataSource ds = Database.getDataSource();
        String sql = "SELECT id, name FROM person";
        String response = "";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            // Count no of rows
            Statement stmt = conn.createStatement();
            ResultSet rsno = stmt.executeQuery("SELECT COUNT(*) FROM person");
            rsno.next();
            int noOfRows = rsno.getInt(1);

            ResultSet rs = pstmt.executeQuery();

            StringBuilder sb = new StringBuilder();
//            sb.append("<html>");
//            sb.append("<body>");
//
//            sb.append("</body>");
//            sb.append("</html>");

            int counter = 0;

            sb.append("[");
            while (rs.next()) {
                counter++;
                sb.append("{");
                sb.append("\"id\":").append(rs.getInt(1));
                sb.append(",");
                sb.append("\"name\":\"").append(rs.getString(2)).append("\"");
                sb.append("}");
                if (noOfRows != counter) {
                    sb.append(",");
                }
            }
            sb.append("]");
            response = sb.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(response);
    }
}
