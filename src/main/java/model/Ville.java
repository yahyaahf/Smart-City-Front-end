/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Ville {
    private long id;
    private long numero;
	private String nom;
	private double latitude;
	private double longitude;
	private List<SmartBuilding> buildings;
	
	public Ville(long id,long numero, String nom, double latitude, double longitude) {
		super();
                this.id=id;
		this.numero = numero;
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
        public Ville(long numero, String nom, double latitude, double longitude) {
		super();
               
		this.numero = numero;
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	public Ville(long numero, String nom) {
		super();
		this.numero = numero;
		this.nom = nom;
		
		
	}
	
	public Ville(String nom, double latitude, double longitude) {
		super();
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<SmartBuilding> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<SmartBuilding> buildings) {
		this.buildings = buildings;
	}
        public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Ville [numero=" + numero + ", nom=" + nom + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
    
}
