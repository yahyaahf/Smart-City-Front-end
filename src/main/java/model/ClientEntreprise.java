/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class ClientEntreprise extends Client {
	private String formeJuridique;
	private String patente;
	
	public ClientEntreprise(String email, String telephone, String adress,
			String formeJuridique, String patente) {
		super(email, telephone, adress);
		this.formeJuridique = formeJuridique;
		this.patente = patente;
	}

	public ClientEntreprise(String email, String telephone, String adress) {
		super(email, telephone, adress);
		
	}

	public String getFormeJuridique() {
		return formeJuridique;
	}

	public void setFormeJuridique(String formeJuridique) {
		this.formeJuridique = formeJuridique;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	@Override
	public String toString() {
		return "ClientEntreprise [formeJuridique=" + formeJuridique + ", patente=" + patente + "]";
	}
    
}
