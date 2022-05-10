/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Location;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class LocationHttp {


OkHttpClient client ;
Gson gson;
public LocationHttp() {
this.client= new OkHttpClient();
gson = new Gson();
}

private List<Location> parse(String responseBody) {

    List<Location>locations=new ArrayList<>() ;
       JsonArray response = JsonParser.parseString(responseBody).getAsJsonArray();

       for(int i=0;i<response.size();i++) {
           JsonObject obj = (JsonObject)response.get(i);
           String dateCreation=obj.get("dateCreation").getAsString();
           String dateDebut=obj.get("dateDebut").getAsString();
           String dateFin=obj.get("dateFin").getAsString();
           Location l1=new Location(dateCreation, dateDebut, dateFin);
           locations.add(l1);
       }
       return locations;
       }



public void addLocation(Location l,long idClient,long idwork) {

String json=gson.toJson(l);
OkHttpClient clt = new OkHttpClient().newBuilder()
 .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(json,mediaType);
Request request = new Request.Builder()
 .url("http://localhost:4567/api/locations/"+idClient+"/"+idwork+"/add")
 .method("POST", body)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clt.newCall(request).execute();
System.out.println(response.body().string());
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

public List<Location> getLocations() {

List<Location> resultats=new ArrayList<>();
    String body=null;
OkHttpClient clent = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/locations")
 .method("GET", null)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clent.newCall(request).execute();
body = response.body().string();
           resultats=parse(body);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return resultats;
}

}

