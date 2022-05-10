/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Reservation {

    private long id;
    private String dateDebut;
    private String dateFin ;
    private String heureDebut ;
    private String heureFin ;
    private WorkSpace workSpace;
    private Employee employee;

    public Reservation(long id, String dateDebut, String dateFin, String heureDebut, String heureFin,
WorkSpace workSpace, Employee employee) {
super();
this.id = id;
this.dateDebut = dateDebut;
this.dateFin = dateFin;
this.heureDebut = heureDebut;
this.heureFin = heureFin;
this.workSpace = workSpace;
this.employee = employee;
}

public Reservation(long id, String dateDebut, String dateFin, String heureDebut, String heureFin) {
super();
this.id = id;
this.dateDebut = dateDebut;
this.dateFin = dateFin;
this.heureDebut = heureDebut;
this.heureFin = heureFin;
}


public Reservation(String dateDebut, String dateFin, String heureDebut, String heureFin) {
super();
this.dateDebut = dateDebut;
this.dateFin = dateFin;
this.heureDebut = heureDebut;
this.heureFin = heureFin;
}

public long getId() {
return id;
}

public void setId(long id) {
this.id = id;
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

public String getHeureDebut() {
return heureDebut;
}

public void setHeureDebut(String heureDebut) {
this.heureDebut = heureDebut;
}

public String getHeureFin() {
return heureFin;
}

public void setHeureFin(String heureFin) {
this.heureFin = heureFin;
}

public WorkSpace getWorkSpace() {
return workSpace;
}

public void setWorkSpace(WorkSpace workSpace) {
this.workSpace = workSpace;
}

public Employee getEmployee() {
return employee;
}

public void setEmployee(Employee employee) {
this.employee = employee;
}

@Override
public String toString() {
return "Reservation [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heureDebut="
+ heureDebut + ", heureFin=" + heureFin + ", workSpace=" + workSpace + ", employee=" + employee + "]";
}
   
   
   
   

}
