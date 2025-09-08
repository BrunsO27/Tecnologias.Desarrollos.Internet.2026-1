package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/controlador"})
public class Controlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Devolviendo valores de un Formulario";
        String DOCTYPE = "<!DOCTYPE html>"; // HTML5

        out.println(DOCTYPE + "\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>" + title + "</title>\n" +
                "<style>\n" +
                "table { border-collapse: collapse; margin: auto; }\n" +
                "th, td { border: 1px solid black; padding: 8px; }\n" +
                "th { background-color: #FFAD00; }\n" +
                "body { background-color: #FDF5E6; text-align: center; }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>" + title + "</h1>\n" +
                "<table>\n" +
                "<tr><th>Nombre del Campo</th><th>Valor(es) del Campo</th></tr>");

        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            out.println("<tr><td>" + paramName + "</td><td>");
            String[] paramValues = request.getParameterValues(paramName);

            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue == null || paramValue.isEmpty()) {
                    out.print("<i>Ningún Valor</i>");
                } else {
                    out.print(paramValue);
                }
            } else {
                out.println("<ul>");
                for (String v : paramValues) {
                    out.println("<li>" + v + "</li>");
                }
                out.println("</ul>");
            }

            out.println("</td></tr>");
        }

        out.println("</table>\n</body>\n</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}