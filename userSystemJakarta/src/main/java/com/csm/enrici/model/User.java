package com.csm.enrici.model;

import java.io.Serializable;

/**
 * Classe représentant les informations d'un utilisateur de l'appli web
 * @author c.enrici
 *
 */
public class User implements Serializable {
	
	private String username;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
