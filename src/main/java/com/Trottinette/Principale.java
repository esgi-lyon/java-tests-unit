package com.Trottinette;

public class Principale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Utilisateur u = new Utilisateur();
		
		// Début de location
		if(u.choixActionLocation() == 1)
		{
			int id = u.saisirIdUtilisateur();
			
			while(!u.verifierUtilisateur(id))
				System.out.println("Veuillez saisir un identifiant correct");
			
			u.enregistrerUtilisateur(id);
		}
		// Fin de location
		else
		{
			int id = u.saisirIdUtilisateur();
			
			while(!u.verifierUtilisateur(id))
				System.out.println("Veuillez saisir un identifiant correct");
			
			
			u.miseAJourUtilisateur(id);
		}
		
		

	}

}
