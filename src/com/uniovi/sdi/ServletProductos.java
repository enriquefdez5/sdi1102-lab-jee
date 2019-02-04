package com.uniovi.sdi;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Servlet implementation class ServletProductos
 */
@WebServlet("/productos")
public class ServletProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Producto> productosTienda = new LinkedList<Producto>();
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdProductos");
			List<Producto> respuesta = db.queryByExample(Producto.class);
			// NO RETORNAR LA MISMA LISTA DE LA RESPUESTA
			productosTienda.addAll(respuesta);
			
			// Retornar la vista con parámetro "productos"
			request.setAttribute("productosTienda", productosTienda);
			getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request,
			response);

		} finally {
			db.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
