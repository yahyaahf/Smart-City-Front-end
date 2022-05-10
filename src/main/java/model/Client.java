/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Collection;
/**
 *
 * @author Lenovo
 */
public class Client {
    private long id;
	private String email;
	private String telephone;
	private String adress;
        private String type;
    private Collection<Location> locations;
    
	public Client( String email, String telephone, String adress, Collection<Location> locations) {
		
		
		this.email = email;
		this.telephone = telephone;
		this.adress = adress;
		this.locations = locations;
	}
	
	

	public Client( String email, String telephone, String adress) {
		super();
		
		this.email = email;
		this.telephone = telephone;
		this.adress = adress;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Collection<Location> getLocations() {
		return locations;
	}

	public void setLocations(Collection<Location> locations) {
		this.locations = locations;
	}



	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", telephone=" + telephone + ", adress=" + adress
				+ ", locations=" + locations + "]";
	}
    
    
}
