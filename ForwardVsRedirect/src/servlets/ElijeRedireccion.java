package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ElijeRedireccion")
public class ElijeRedireccion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		String rutaContexto = request.getContextPath();
		// out.println("rutaContexto: " + rutaContexto + " <br />");
		String metodoRedireccion = request.getParameter("redireccion");
		// out.println("metodoRedireccion: " + metodoRedireccion + " <br />");
		String vistaDestino;
		if (metodoRedireccion != null) {  // se ha recibido el parámetro metodoRedireccion
			// out.println("metodoRedireccion NO NULO" + " <br />");
			if (metodoRedireccion.equals("F")) { // se ha seleccionado FORWARD
				// out.println("metodoRedireccion F" + " <br />");
				vistaDestino = "/destinoForward.html";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			} else {  // se ha seleccionado REDIRECT
				// out.println("metodoRedireccion R" + " <br />");
				vistaDestino = "/destinoRedirect.html";
				response.sendRedirect(rutaContexto + vistaDestino);
			}
		} else {  // no se ha recibido el parámetro, se devuelve a la página inicial
			// out.println("metodoRedireccion NULO" + " <br />");
			vistaDestino = "/seleccionMetodoRedireccion.html";
			response.sendRedirect(rutaContexto + vistaDestino);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
