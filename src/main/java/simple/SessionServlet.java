package simple;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        int localPort = request.getLocalPort();

        String sesssionID = request.getSession().getId();
        String testKey = (String) request.getSession().getAttribute("testKey");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("tomcat port: " + localPort + " ---- sesssionID : " + sesssionID + "\n");
            out.append("tomcat port: " + localPort + " ---- testKey : " + testKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}

