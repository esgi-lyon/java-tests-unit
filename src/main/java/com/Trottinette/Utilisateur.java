package com.Trottinette;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utilisateur {
	
	int id;
	String nom, prenom;
	int typeClient;
	double solde, tarif;
	
	Connexion c = new Connexion();
	
	
	public Utilisateur(int id, String nom, String prenom, int typeClient, double solde) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.typeClient = typeClient;
		this.solde = solde;
		this.tarif = 2;
	}
	
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	int choixActionLocation()
	{
		int choix;
		Scanner sc = new Scanner(System.in);
		System.out.println("Sasiri 1 pour le début de location et 2 pour la fin de location");
		choix = sc.nextInt();
		while(choix != 1 && choix != 2)
			System.out.println("Saisir le bon choix");
		return choix;
	}

	int saisirIdUtilisateur()
	{
		int id;
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir votre identifiant : ");
		id = sc.nextInt();
		
		return id;
	}
	
	boolean verifierUtilisateur(int id)
	{
		boolean result = false;
		List<Integer> listId = null;
		
		
		listId = c.getUtilisateur();
		
		for(int idU : listId)
		{
			if(id == idU)
				result = true;
		}		
		
		return result;
	}
	
	void enregistrerUtilisateur(int id)
	{
		Date dateEntree = new Date();
		dateEntree.setTime(System.currentTimeMillis());
		
		c.insertLocation(id, dateEntree.getTime());
	}
	
	void miseAJourUtilisateur(int id)
	{
		Date dateSortie = new Date();
		dateSortie.setTime(System.currentTimeMillis());
		
		long dateEntree = c.getDateEntree(id);
		
		double duree = (int) (dateSortie.getTime() - dateEntree) / (1000 * 60);
		
		double prix = calculLocationTrottinette(id, duree);
		
		c.updateLocation(id, prix, dateSortie.getTime());
		
	}
	
	double calculLocationTrottinette(int id, double duree)
	{
		double prix = 0;
		
		double tarif = c.getTarif(id) / 60;
		
		prix = tarif * duree;
		
		System.out.println("tarif : " + tarif);
		System.out.println("duree : " + duree);
		System.out.println("prix : " + prix);
		
		return prix;
		
	}
	
	
	

}
