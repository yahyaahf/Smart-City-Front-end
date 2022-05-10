/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Employee {

   private long id;
   private String nom , prenom ;
   private String email;
   private String sexe;
   private String telephone ;
   private String adress;
   private ClientEntreprise entreprise;

   public Employee(long id, String nom, String prenom, String email, String sexe, String telephone, String adress,
ClientEntreprise entreprise) {
super();
this.id = id;
this.nom = nom;
this.prenom = prenom;
this.email = email;
this.sexe = sexe;
this.telephone = telephone;
this.adress = adress;
this.entreprise = entreprise;
}

public Employee(long id, String nom, String prenom, String email, String sexe, String telephone,
String adress) {
super();
this.id = id;
this.nom = nom;
this.prenom = prenom;
this.email = email;
this.sexe = sexe;
this.telephone = telephone;
this.adress = adress;
}


public Employee(String nom, String prenom, String email, String sexe, String telephone, String adress) {
super();
this.nom = nom;
this.prenom = prenom;
this.email = email;
this.sexe = sexe;
this.telephone = telephone;
this.adress = adress;
}

public long getId() {
return id;
}

public void setId(long id) {
this.id = id;
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

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getSexe() {
return sexe;
}

public void setSexe(String sexe) {
this.sexe = sexe;
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

public ClientEntreprise getEntreprise() {
return entreprise;
}

public void setEntreprise(ClientEntreprise entreprise) {
this.entreprise = entreprise;
}

@Override
public String toString() {
return "Employee [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", sexe=" + sexe
+ ", telephone=" + telephone + ", adress=" + adress + ", entreprise=" + entreprise + "]";
}


   
   
   
   

}
