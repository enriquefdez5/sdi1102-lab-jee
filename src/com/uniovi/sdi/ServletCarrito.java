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
		//Si aqu� crea una variable sesi�n de tipo HttpSession por que luego sigue usando request.getSession???
		HttpSession session = request.getSession();
		
		//aqu� vuelve a usar request.getSession().
		
		//TODO Duda:
		//Habr�a que usar la funci�n synchronized donde utiliza la sesi�n. Esto ser�a
		//aqu� y en el manejo del carrito unas l�neas m�s abajo.
		//Realmente a nivel de usuario humano no deber�a haber problema sin utilizar synchronized en este ejemplo.
		//La funci�n se deber�a utilizar si fuera necesario obtener alg�n dato de la sesi�n, modificarlo
		//y posteriormente realizar alguna operaci�n con �l, entonces si habr�a que tener m�s cuidado.
		
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		// No hay carrito, creamos uno y lo insertamos en sesi�n
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
			
			//aqu� vuelve a usar request.getSession().
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
	 * M�todo auxiliar para a�adir productos al carrito de la compra
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
	 * M�todo auxiliar para imprimir el contenido del carrito
	 * @param carrito, se imprimir� el contenido del carrito del usuario de la sesi�n
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
