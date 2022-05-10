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
public class SmartBuilding {
    private long numero;
	private int nombreEtages;
	private int surface;
	private String adress;
	
	private double longitude;
	private double latitude;
	private Ville ville;
	private List<Etage> etages;
	
	
	public SmartBuilding(long numero, int nombreEtages, int surface, String adress) {
		
		this.numero = numero;
		this.nombreEtages = nombreEtages;
		this.surface = surface;
		this.adress = adress;
		//this.latitude = latitude;
	
	}
        
        public SmartBuilding(long numero,int nombreEtages, int surface, double longitude, double latitude,String adress) {
		super();
                this.numero=numero;
		this.nombreEtages = nombreEtages;
		this.surface = surface;
		this.longitude = longitude;
		this.latitude = latitude;
		this.adress=adress;
	}
        
        public SmartBuilding(int nombreEtages, int surface, double longitude, double latitude,String adress) {
		
		this.nombreEtages = nombreEtages;
		this.surface = surface;
		this.longitude = longitude;
		this.latitude = latitude;
		this.adress=adress;
	}

    public SmartBuilding(int nombreEtage, int surfaceBuild, String adressString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
	
	
	/*public SmartBuilding(int nombreEtages, int surface, double longitude, double latitude) {
		super();
		this.nombreEtages = nombreEtages;
		this.surface = surface;
		this.longitude = longitude;
		//this.latitude = latitude;
	}*/


	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public int getNombreEtages() {
		return nombreEtages;
	}
	public void setNombreEtages(int nombreEtages) {
		this.nombreEtages = nombreEtages;
	}
	public int getSurface() {
		return surface;
	}
	public void setSurface(int surface) {
		this.surface = surface;
	}
	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}
	/*public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/*public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}*/
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public List<Etage> getEtages() {
		return etages;
	}
	public void setEtages(List<Etage> etages) {
		this.etages = etages;
	}
	@Override
	public String toString() {
		return "SmartBuilding [numero=" + numero + ", nombreEtages=" + nombreEtages + ", surface=" + surface
				+ ", adress=" + adress + "]";
	}
}
