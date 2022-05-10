/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class ClientIndividu extends Client {
	private String nom,prenom;
	private String sexe;

	
	public ClientIndividu(String email, String telephone, String adress, 
			String nom, String prenom,String sexe) {
		super( email, telephone, adress);
		this.nom = nom;
		this.prenom = prenom;
                this.sexe=sexe;
		
	}

	

	public ClientIndividu( String email, String telephone, String adress) {
		super( email, telephone, adress);
			
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}



	@Override
	public String toString() {
		return "ClientIndividu [nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + "]";
	}
    
}
