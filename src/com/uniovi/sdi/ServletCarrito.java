package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO Duda: 
		//Si aquí crea una variable sesión de tipo HttpSession por que luego sigue usando request.getSession???
		HttpSession session = request.getSession();
		
		//aquí vuelve a usar request.getSession().
		
		//TODO Duda:
		//Habría que usar la función synchronized donde utiliza la sesión. Esto sería
		//aquí y en el manejo del carrito unas líneas más abajo.
		//Realmente a nivel de usuario humano no debería haber problema sin utilizar synchronized en este ejemplo.
		//La función se debería utilizar si fuera necesario obtener algún dato de la sesión, modificarlo
		//y posteriormente realizar alguna operación con él, entonces si habría que tener más cuidado.
		
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		// No hay carrito, creamos uno y lo insertamos en sesión
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
			
			//aquí vuelve a usar request.getSession().
			request.getSession().setAttribute("carrito", carrito);
		}
		String producto = request.getParameter("producto");
		if (producto != null) {
			insertarEnCarrito(carrito, producto);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Tienda SDI: carrito</TITLE></HEAD>");
		out.println("<BODY>");
		out.println(carritoEnHTML(carrito) + "<br>");
		out.println("<a href=\"tienda.html\">Volver</a></BODY></HTML>");
	}

	/**
	 * Método auxiliar para añadir productos al carrito de la compra
	 * 
	 * @param               carrito, hash con producto y cantidad
	 * @param claveProducto identificador del producto elegido
	 */
	private void insertarEnCarrito(Map<String, Integer> carrito, String claveProducto) {
		if (carrito.get(claveProducto) == null)
			carrito.put(claveProducto, new Integer(1));
		else {
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos + 1));
		}
	}

	/**
	 * Método auxiliar para imprimir el contenido del carrito
	 * @param carrito, se imprimirá el contenido del carrito del usuario de la sesión
	 * @return
	 */
	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = "";
		for (String key : carrito.keySet())
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		return carritoEnHTML;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
