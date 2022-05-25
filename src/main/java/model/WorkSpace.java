/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class WorkSpace {
    private long id;
    private long numero;
	private int surface;
	private  String  type;
	private int etage;
	private String position;
	private SmartBuilding building;
        public WorkSpace(long id,long numero, int surface, String type, int etage, String position) {
		this.id=id;
		this.numero = numero;
		this.surface = surface;
		this.type = type;
		this.etage = etage;
		this.position = position;
		
	}
	public WorkSpace(long numero, int surface, String type, int etage, String position) {
		
		this.numero = numero;
		this.surface = surface;
		this.type = type;
		this.etage = etage;
		this.position = position;
		
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public int getSurface() {
		return surface;
	}
	public void setSurface(int surface) {
		this.surface = surface;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public SmartBuilding getBuilding() {
		return building;
	}
	public void setBuilding(SmartBuilding building) {
		this.building = building;
	}
        public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		/*return "WorkSpace [id=" + id + ", numero=" + numero + ", surface=" + surface + ", type=" + type + ", etage=" + etage
				+ ", position=" + position + "]";*/
                return ""+type;
	}
}
