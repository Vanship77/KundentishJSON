package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Productos;
import services.ProductosService;
import services.ProductosServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.json", "/productoshtml"})
public class ProductosJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductosService service = new ProductosServiceImplement();
        List<Productos> productos;

        try {
            productos = service.listar();
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener la lista de productos.");
            return;
        }

        String servletPath = req.getServletPath();
        boolean esJson = servletPath.endsWith(".json");

        if (esJson) {
            // Configurar la respuesta para descargar el archivo JSON
            resp.setContentType("application/json;charset=UTF-8");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.json");

            try (PrintWriter out = resp.getWriter()) {
                // Generar JSON formateado
                out.println("[");
                for (int i = 0; i < productos.size(); i++) {
                    Productos producto = productos.get(i);
                    out.println("  {");
                    out.println("    \"idProducto\": \"" + producto.getIdProducto() + "\",");
                    out.println("    \"nombre\": \"" + producto.getNombre() + "\",");
                    out.println("    \"marca\": \"" + producto.getMarca() + "\",");
                    out.println("    \"modelo\": \"" + producto.getModelo() + "\",");
                    out.println("    \"precio\": \"" + producto.getPrecio() + "\",");
                    out.println("    \"tipo\": \"" + producto.getTipo() + "\"");
                    out.print("  }");
                    if (i < productos.size() - 1) {
                        out.println(",");
                    } else {
                        out.println();
                    }
                }
                out.println("]");
            }
        } else {
            // Generar HTML con tabla y enlace para descargar JSON
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Lista de Productos Tecnológicos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Lista de Productos Tecnológicos</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json\">Descargar JSON</a></p>");
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<th>ID Producto</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Marca</th>");
                out.println("<th>Modelo</th>");
                out.println("<th>Precio</th>");
                out.println("<th>Tipo</th>");
                out.println("</tr>");
                for (Productos p : productos) {
                    out.println("<tr>");
                    out.println("<td>" + p.getIdProducto() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getMarca() + "</td>");
                    out.println("<td>" + p.getModelo() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}