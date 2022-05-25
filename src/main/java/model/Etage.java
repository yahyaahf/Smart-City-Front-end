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
public class Etage {
    private long id;
    private long numEtage;
	private double surface;
	private List<WorkSpace> workSpaces;
	private SmartBuilding smartBuilding;
        
        public Etage(long id,long numEtage,double surface){
            this.id=id;
            this.numEtage=numEtage;
            this.surface=surface;
        }
	
	public Etage(long numEtage, double surface/* List<WorkSpace> workSpaces, SmartBuilding smartBuilding*/) {
		
		this.numEtage = numEtage;
		this.surface = surface;
	//	this.workSpaces = workSpaces;
	//	this.smartBuilding = smartBuilding;
	}
        
        public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id=id;
	}

	public long getNumEtage() {
		return numEtage;
	}

	public void setNumEtage(long numEtage) {
		this.numEtage = numEtage;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public List<WorkSpace> getWorkSpaces() {
		return workSpaces;
	}

	public void setWorkSpaces(List<WorkSpace> workSpaces) {
		this.workSpaces = workSpaces;
	}

	public SmartBuilding getSmartBuilding() {
		return smartBuilding;
	}

	public void setSmartBuilding(SmartBuilding smartBuilding) {
		this.smartBuilding = smartBuilding;
	}

	@Override
	public String toString() {
		//return "Etage [id="+id+"numEtage=" + numEtage + ", surface=" + surface + "]";
                return ""+numEtage;
	}
    
}
