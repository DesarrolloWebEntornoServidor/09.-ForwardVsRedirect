package es.servlets;

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
	private static final String PAGINA_INICIO = "/seleccionMetodoRedireccion.html";
	private static final String PAGINA_REDIRECT = "/destinoRedirect.html";
	private static final String PAGINA_FORWARD = "/destinoForward.html";
	private static final String PARAMETRO_REDIRECCION = "redireccion";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		// response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		String rutaContexto = request.getContextPath();
		// out.println("rutaContexto: " + rutaContexto + " <br />");
		String metodoRedireccion = request.getParameter(PARAMETRO_REDIRECCION);
		// out.println("metodoRedireccion: " + metodoRedireccion + " <br />");
		String vistaDestino;
		try {
			if (seleccionadaRedireccion(metodoRedireccion)) { 
				// out.println("metodoRedireccion NO NULO" + " <br />");
				if (seleccionaForward(metodoRedireccion)) { 					

					redireccionForward(request, response, PAGINA_FORWARD);

				} else if (seleccionaRedirect(metodoRedireccion)){ 
					
					redireccionRedirect(response,rutaContexto,PAGINA_REDIRECT);
				}
			} else { // no se ha recibido el parámetro, se devuelve a la página
						// inicial
				
				redireccionRedirect(response,rutaContexto,PAGINA_INICIO);
			}
		} catch (ServletException e) {

		} catch (IOException e) {

		}

	}

	

	private boolean seleccionadaRedireccion(String metodoRedireccion) {
		return metodoRedireccion != null;
	}

	
	
	private void redireccionRedirect(HttpServletResponse response,
			String rutaContexto, String vistaDestino) throws IOException {
		// out.println("metodoRedireccion R" + " <br />");		
		
		response.sendRedirect(rutaContexto + vistaDestino);
	}

	private boolean seleccionaForward(String metodoRedireccion) {
		// out.println("metodoRedireccion R" + " <br />");
		return metodoRedireccion.equals("F");
	}
	
	private boolean seleccionaRedirect(String metodoRedireccion) {
		// out.println("metodoRedireccion R" + " <br />");
		return metodoRedireccion.equals("R");
	}

	private void redireccionForward(HttpServletRequest request,
			HttpServletResponse response, String vistaDestino) throws ServletException, IOException {		
		// out.println("metodoRedireccion F" + " <br />");
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(vistaDestino);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
