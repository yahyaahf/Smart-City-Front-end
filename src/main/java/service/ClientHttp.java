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

import model.Client;
import model.ClientEntreprise;
import model.ClientIndividu;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class ClientHttp {


OkHttpClient client ;
Gson gson;
public ClientHttp() {
this.client= new OkHttpClient();
gson = new Gson();
}

private List<ClientIndividu> parse1(String responseBody) {

    List<ClientIndividu>clients=new ArrayList<>() ;
   
        JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
            JsonObject obj = (JsonObject)response.get(i);
            //long numero= obj.get("numero").getAsLong();
            //String type= obj.get("type").getAsString();
            String email= obj.get("email").getAsString();
            String telephone= obj.get("telephone").getAsString();
            String adress= obj.get("adress").getAsString();
    String nom=obj.get("nom").getAsString();
    String prenom=obj.get("prenom").getAsString();
    String sexe=obj.get("sexe").getAsString();
           
           
           
            ClientIndividu c1=new ClientIndividu(email, adress, telephone, nom, prenom,sexe);
           
            clients.add(c1);
           
        }
           
            return clients;
        }

private List<ClientEntreprise> parse2(String responseBody) {

    List<ClientEntreprise>clients=new ArrayList<>() ;
   
        JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
            JsonObject obj = (JsonObject)response.get(i);
            //long numero= obj.get("numero").getAsLong();
            //String type= obj.get("type").getAsString();
            String email= obj.get("email").getAsString();
            String telephone= obj.get("telephone").getAsString();
            String adress= obj.get("adress").getAsString();
            String formeJuridique=obj.get("formeJuridique").getAsString();
            String patente=obj.get("patente").getAsString();
   
            ClientEntreprise c1=new ClientEntreprise( email, telephone, adress, formeJuridique, patente);
           
            clients.add(c1);
           
        }
           
            return clients;
        }


public void addClient(Client c) {
String json=gson.toJson(c);
OkHttpClient clnt = new OkHttpClient().newBuilder()
 .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(json,mediaType);
Request request = new Request.Builder()
 .url("http://localhost:4567/api/clients/add")
 .method("POST", body)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clnt.newCall(request).execute();
System.out.println(response.body().string());
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

public List<ClientIndividu> getClientIndividuel(){

List<ClientIndividu> resultats=new ArrayList<>();
String body=null;
OkHttpClient clients = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/ci/clients")
 .method("GET", null)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clients.newCall(request).execute();
body = response.body().string();
           resultats=parse1(body);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return resultats;

}

public List<ClientEntreprise> getClientEntreprise(){

List<ClientEntreprise> resultats=new ArrayList<>();
String body=null;
OkHttpClient clent = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/ce/clients")
 .method("GET", null)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = clent.newCall(request).execute();
body = response.body().string();
           resultats=parse2(body);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return resultats;
}

}
