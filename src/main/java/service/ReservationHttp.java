/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Reservation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Lenovo
 */
public class ReservationHttp {

OkHttpClient client ;
Gson gson;
public ReservationHttp() {
this.client= new OkHttpClient();
gson = new Gson();
}



private List<Reservation> parse(String responseBody) {

    List<Reservation>reservations=new ArrayList<>() ;
   
        JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
            JsonObject obj = (JsonObject)response.get(i);  
            //String dateDebut, String dateFin, String heureDebut, String heureFin
            String dateDebut=obj.get("dateDebut").getAsString();
            String dateFin=obj.get("dateFin").getAsString();
            String heureDebut=obj.get("heureDebut").getAsString();
            String heureFin=obj.get("heureFin").getAsString();
                           
            Reservation e1=new Reservation(dateDebut, dateFin, heureDebut, heureFin);
            reservations.add(e1);
           
        }    
            return reservations;
        }

public void addReservation(Reservation r,long idwork,long idemployee) {
String json=gson.toJson(r);
OkHttpClient clent = new OkHttpClient().newBuilder()
 .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, json);
Request request = new Request.Builder()
 .url("http://localhost:4567/api/reservations/"+idwork+"/"+idemployee+"/add")
 .method("POST", body)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clent.newCall(request).execute();
System.out.println(response.body().string());
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

public List<Reservation> getReservations(){
List<Reservation> resultats=new ArrayList<>();
String body=null;
OkHttpClient client = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/reservations")
 .method("GET", null)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = client.newCall(request).execute();
body = response.body().string();
           resultats=parse(body);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return resultats;
}

}


