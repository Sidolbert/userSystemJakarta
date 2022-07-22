package com.csm.enrici.routing;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import jakarta.servlet.http.Cookie;

import com.csm.enrici.model.User;
import com.csm.enrici.model.UserDbAccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionServlet
 */
//l'annotation @WebServlet est une syntaxe alternative pour mapper le chemin d'accès à cette Servlet
//@WebServlet(value="/connect")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//on récupère nos cookies s'ils existent
		Cookie[] cookieArray = request.getCookies();
		//pour chaque cookie récupéré
		if(cookieArray != null) {
			for (Cookie cookie : cookieArray) {
				//si le cookie de nom username est trouvé
				if(cookie.getName().equals("username")) {
					//on passe sa valeur en attribut de requête
					request.setAttribute("username", cookie.getValue());
				}
			}
		}
		
		// on redirige l'URL vers la page du formulaire de connexion
		this.getServletContext().getRequestDispatcher("/WEB-INF/connectForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//on vérifie si la combinaison username+password existe bien dans la base
		User userConnected = UserDbAccess.getUser(request.getParameter("username"), request.getParameter("password"));
		//si on a pas trouvé l'utilisateur et son mot de passe
		if(userConnected == null) {
			request.setAttribute("error_id", true);
			this.doGet(request, response);
		}
		else {
			//si l'utilisateur a coché la case Remember me
			if(request.getParameter("remember") != null) {
				//on crée et enregistre sous forme de cookie ses infos de connexion
				Cookie usernameCookie = new Cookie("username", request.getParameter("username"));
				//on définit la durée de vie du cookie, ici un mois
				usernameCookie.setMaxAge(60 * 60 * 24 * 30);
				usernameCookie.setHttpOnly(true);
				response.addCookie(usernameCookie);
				
			}
			
			//on crée/récupère la session et on lui donne notre bean comme paramètre
			request.getSession().setAttribute("user_info", userConnected);
			this.getServletContext().getRequestDispatcher("/WEB-INF/welcomePage.jsp").forward(request, response);
		}
	}

}
