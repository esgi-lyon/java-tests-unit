package com.Trottinette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connexion {
	
	Connection connexion = null;
	Statement statement = null;
	ResultSet resultat = null;
	
	public Connection connect() {
		
		System.out.println("début connect");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/trottinette?serverTimezone=UTC", "root", "");
			
			if(connexion != null)
				System.out.println("ok");
			else
				System.out.println("nok");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connexion;
	}
	
	public List<Integer> getUtilisateur() {
		List<Integer> listId = new ArrayList<>();
		try {
			connexion = connect();
			Statement st = connexion.createStatement();
			resultat = st.executeQuery("SELECT id FROM utilisateur;");
			
			while(resultat.next()) {
				listId.add(resultat.getInt("id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listId;
	}
	
	public void insertLocation(int id, long dateEntree) {
		try {
			connect();
			statement = connexion.createStatement();
			PreparedStatement p = connexion.prepareStatement("INSERT INTO location (id_utilisateur, date_entree) VALUES (?, ?)");
			p.setInt(1, id);
			p.setLong(2, dateEntree);
			
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long getDateEntree(int id) {
		long dateEntree = 0;
		try {
			connect();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT date_entree FROM location where id_utilisateur = " + id + " and prix is null;");
			
			while(resultat.next()) {
				dateEntree = resultat.getLong("date_entree");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateEntree;
	}
	
	public double getTarif(int id) {
		double tarif = 0;
		try {
			connect();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT tarif FROM utilisateur where id = " + id + ";");
			
			while(resultat.next()) {
				tarif = resultat.getLong("tarif");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarif;
	}
	
	public void updateLocation(int id, double prix, long dateSortie) {
		try {
			connect();
			statement = connexion.createStatement();
			PreparedStatement p = connexion.prepareStatement("UPDATE location SET date_sortie = ?, prix = ? where id_utilisateur = ? and prix is null");
			p.setLong(1, dateSortie);
			p.setDouble(2, prix);
			p.setInt(3, id);
			
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
