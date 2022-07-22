package com.csm.enrici.routing;

import java.io.IOException;

import com.csm.enrici.model.UserDbAccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
//@WebServlet(value="/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/registerForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//on prépare une variable qui repère les erreurs
		boolean errors = false;
		
		//on vérifie que l'username est rempli
		if(request.getParameter("username").isBlank()) {
			request.setAttribute("error_empty_name", true);
			errors = true;
		}
		//on vérifie que l'username n'est pas déjà pris
		else if(UserDbAccess.exists(request.getParameter("username"))) {
			request.setAttribute("username_taken", true);
			errors = true;
		}
		//on vérifie que le password est rempli
		if(request.getParameter("password").isBlank()) {
			request.setAttribute("error_empty_pass", true);
			errors = true;
		}
		//on vérifie que le password2 est rempli
		if(request.getParameter("password2").isBlank()) {
			request.setAttribute("error_empty_pass2", true);
			errors = true;
		}
		//on vérifie que password et password2 ont le même contenu
		else if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			request.setAttribute("pass_not_confirmed", true);
			errors = true;
		}
		
		//en cas de pb, on renvoie au formulaire d'inscription
		
		if(errors == true) {
			this.doGet(request, response);
		}
		//si tout va bien, on enregistre en base le nouvel utilisateur
		UserDbAccess.createUser(request.getParameter("username"), request.getParameter("password"));
		//on redirige vers le formulaire de connexion
		//attention : sendRedirect travaille avec des URL HTTP, donc il faut lui donner une adresse relative si on veut rester dans le contexte de l'appli web
		response.sendRedirect("connect");
		
	}

}
