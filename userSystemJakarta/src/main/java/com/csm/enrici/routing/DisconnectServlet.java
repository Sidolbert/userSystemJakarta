package com.csm.enrici.routing;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisconnectServlet
 */
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//quand on doit déconnecter, peu importe par quel chemin :
		//on détruit la session
		request.getSession().invalidate();
		//on redirige vers le formulaire de connexion
		//attention : sendRedirect travaille avec des URL HTTP, donc il faut lui donner une adresse relative si on veut rester dans le contexte de l'appli web
		response.sendRedirect("connect");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
