package com.csm.enrici.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * classe gérant la récupération d'un User depuis la BDD et son insertion à l'inscription
 * @author c.enrici
 *
 */
public class UserDbAccess {

	/**
	 * la connexion à la BDD
	 */
	private static Connection linkBdd;
	
	/**
	 * le chemin d'accès à ma BDD
	 */
	private static String dsn = "jdbc:mysql://localhost:3306/user_access";
	/**
	 * l'identifiant utilisateur d'accès à la base
	 */
	private static String dbUser = "cyril.enrici";
	/**
	 * le mdp d'accès à la base
	 */
	private static String dbPass = "_e7sziTAeio-M_pG";
	
	
	//constructeur non nécessaire, la classe utilitaire travaillera ici en statique
//	public UserDbAccess() {
//		// TODO Auto-generated constructor stub
//	}
	
	/**
	 * connecte l'appli web à la BDD configurée
	 * récupère la connexion existante si elle est déjà créée
	 * @return l'objet Connection
	 */
	public static Connection getConnection() {
		
		
		//si la connexion n'existe pas
		if (linkBdd == null) {
			
            try {
            	//on charge le Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                //on utilise sa méthode de création de connexion
                linkBdd = DriverManager.getConnection(dsn, dbUser, dbPass);
            } catch (SQLException e) {
            	//en cas de problème on bloque
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		//on retourne l'attribut
        return linkBdd;
		
	}
	
	/**
	 * méthode qui va chercher un utilisateur depuis son nom
	 * s'il existe, elle retourne un objet User
	 * sinon null
	 * @param username le nom de l'utilisateur recherché
	 * @return l'objet User ou null
	 */
	public static User getUser(String username) {
		//on fait appel à l'autre version de la méthode, avec une valeur true
		return getUser(username, "");
		
	}
	
	/**
	 * méthode qui va chercher un utilisateur depuis son nom et son mot de passe
	 * s'il existe, elle retourne un objet User
	 * sinon null
	 * @param username le nom de l'utilisateur recherché
	 * @return l'objet User ou null
	 */
	public static User getUser(String username, String password) {
		//si la connexion existe on la récupère, sinon on la crée
		linkBdd = getConnection();
		try {
			
			String sql = "SELECT * FROM users WHERE username=?";
			//si on cherche aussi le mot de passe, on le précise
			if(!password.isEmpty()) {
				sql +=  "AND password=?";
			}
			//on crée une requête préparée
			PreparedStatement userGet = linkBdd.prepareStatement(sql);
			//on paramètre ses variables
			userGet.setString(1, username);
			if(!password.isEmpty()) {
				userGet.setString(2, password);
			}
			//on exécute la requête préparée
			ResultSet rs = userGet.executeQuery();
			//si on a trouvé notre utilisateur
			if(rs.next()) {
				//on crée un objet User et on le renvoie
				User u = new User();
				u.setUsername(rs.getString("username"));
				return u;
			}else {
				//on renvoie null
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * méthode qui insère un nouvel utilisateur dans la base depuis un username et un password
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 */
	public static void createUser(String username, String password) {
		
		//on récupère la connexion
		linkBdd = getConnection();
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		try {
			//on crée une requête préparée depuis la connexion
			PreparedStatement userInsert = linkBdd.prepareStatement(sql);
			userInsert.setString(1, username);
			userInsert.setString(2, password);
			userInsert.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * méthode qui vérifie si un nom d'utilisateur existe déjà dans la BDD
	 * @param username le nom d'utilisateur cherché
	 * @return true s'il existe déjà, false sinon
	 */
	public static boolean exists(String username) {
		if(getUser(username) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
