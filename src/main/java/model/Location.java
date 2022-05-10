/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class Location {
    private long id;
    private String dateCreation;
    private String dateDebut;
    private String dateFin ;
    private Client client ;
    private WorkSpace workSpace ;
    
	public Location(long id, String dateCreation, String dateDebut, String dateFin, Client client,
			WorkSpace workSpace) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.client = client;
		this.workSpace = workSpace;
	}

	
	
	public Location(String dateCreation, String dateDebut, String dateFin) {
		
		
		this.dateCreation = dateCreation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}



	public Location(long id, String dateCreation, String dateDebut, String dateFin, Client client) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.client = client;
	}



	public Location(long id2, String dateCreation2, String dateDebut2, String dateFin2) {
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public WorkSpace getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(WorkSpace workSpace) {
		this.workSpace = workSpace;
	}
    
	/*public double calcMontant() {
		int montantCourteDuree=500;
		int montantLongDuree=300;
		long diff = dateFin.getDayOfYear() - dateDebut.getDayOfYear();
		
		if(diff<30) {
			return montantCourteDuree*diff;
		}
		else
			return montantLongDuree*diff;
		
	}*/
}
