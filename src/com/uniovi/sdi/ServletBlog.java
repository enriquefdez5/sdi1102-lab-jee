package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Servlet implementation class ServletBlog
 */
@WebServlet("/blog")
public class ServletBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> posts = (HashMap<String, String>) request.getSession().getAttribute("posts");
		// No hay carrito, creamos uno y lo insertamos en sesión
		if (posts == null) {
			posts = new HashMap<String, String>();
			//aquí vuelve a usar request.getSession().
			request.getSession().setAttribute("posts", posts);
		}
		String usuario = request.getParameter("user");
		String comentario = request.getParameter("post");
		if (usuario != null && comentario !=null) {
			añadirPost(posts, usuario, comentario);
		}
		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresPost", posts);
		getServletContext().getRequestDispatcher("/vista-blog.jsp").forward(request,
		response);
		
	}
	
	private void añadirPost(Map<String, String> posts, String user, String comentario) {
		if (posts.get(comentario) == null) {
			posts.put(comentario, user);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> posts = (HashMap<String, String>) request.getSession().getAttribute("posts");
		// No hay carrito, creamos uno y lo insertamos en sesión
		if (posts == null) {
			posts = new HashMap<String, String>();
			//aquí vuelve a usar request.getSession().
			request.getSession().setAttribute("posts", posts);
		}
		String usuario = request.getParameter("user");
		String comentario = request.getParameter("post");
		if (usuario != null && comentario !=null) {
			añadirPost(posts, usuario, comentario);
		}
		// Retornar la vista con parámetro "carrito"
		request.getSession().setAttribute("posts", posts);
		getServletContext().getRequestDispatcher("/vista-blog.jsp").forward(request,
		response);
	}

}
