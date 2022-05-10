/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Ville;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class VilleHttp {
      OkHttpClient client ;
	Gson gson;
	public VilleHttp() {
		this.client= new OkHttpClient();
		gson = new Gson();
	}

	
	/*public static void main(String [] args ) {

		 VilleHttp service=new VilleHttp();
		 //service.getVillesFromServer().forEach(v->System.out.println(v)) ;
		 service.addVille(new Ville("Agadir",33.97,6.9));
		 //service.deleteVille(new Ville("Agadir",33.97,6.8));
	     	    }*/

	    private List<Ville> parse(String responseBody) {

    List<Ville>villes=new ArrayList<>() ;
   
       
          JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();

       for(int i=0;i<response.size();i++) {
           JsonObject obj = (JsonObject)response.get(i);
           //long id= obj.get("id").getAsLong();
           long numero= obj.get("numero").getAsLong();
           String nom= obj.get("nom").getAsString();
           double longitude= obj.get("longitude").getAsDouble();
           double latitude= obj.get("latitude").getAsDouble();
           
           Ville v1=new Ville(numero,nom,longitude,latitude);
           villes.add(v1);
       }
           
           return villes;
       }

/*Ville v1= new  Ville(1, "Rabat", 87, 23);
	    	Ville v2= new  Ville(2, "Casa", 45, 20);
	    	Ville v3= new  Ville(3, "Tanger", 87, 23);
	    	Ville v4= new  Ville(4, "Fes", 87, 23);
	    	resultats.add(v1);
	    	resultats.add(v2);
	    	resultats.add(v3);
	    	resultats.add(v4);*/
	        //JsonObject member = new JsonParser().parse(responseBody).getAsJsonObject();

	    public List<Ville> getVillesFromServer(){
      
      List<Ville> resultats=new ArrayList<>();
    String body=null;
    OkHttpClient clent = new OkHttpClient().newBuilder()
     .build();
    Request request = new Request.Builder()
     .url("http://localhost:4567/api/villes")
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
	    
	    public void addVille(Ville v) {
	    	
	    	String json=gson.toJson(v);
	    	OkHttpClient clt = new OkHttpClient().newBuilder()
	    			  .build();
	    			MediaType mediaType = MediaType.parse("application/json");
	    			RequestBody body = RequestBody.create(json,mediaType);
	    			Request request = new Request.Builder()
	    			  .url("http://localhost:4567/api/villes/add")
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
	    
	    public void deleteVille(long id) {
	    	//Ville v=this.getNumeroVille(id.get)
	    	String json=gson.toJson(id);
	    	OkHttpClient clent = new OkHttpClient().newBuilder()
	    			  .build();
	    			MediaType mediaType = MediaType.parse("application/json");
	    			RequestBody body = RequestBody.create(mediaType, json);
	    			Request request = new Request.Builder()
	    			  .url("http://localhost:4567/api/villes/"+id+"/remove")
	    			  .method("DELETE", body)
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
	    public Ville updateVille(Ville v) {
	    	
	    	String json=gson.toJson(v);
	    	OkHttpClient clnt = new OkHttpClient().newBuilder()
	    			  .build();
	    			MediaType mediaType = MediaType.parse("application/json");
	    			RequestBody body = RequestBody.create(json,mediaType);
	    			Request request = new Request.Builder()
	    			  .url("http://localhost:4567/api/villes/id/update")
	    			  .method("PUT", body)
	    			  .addHeader("Content-Type", "application/json")
	    			  .build();
	    			try {
						Response response = clnt.newCall(request).execute();
						System.out.println(response.body().string());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return v;    
	    }
	    public Ville getVille(String a) {
	    	Ville r=null;
	    	VilleHttp v= new VilleHttp();
	    	List<Ville> s=v.getVillesFromServer();
	    	for(Ville b:s) {
	    		if (b.getNom().equals(a)) {
	    			r=b;
	    		}
	    		
	    	}
	    	return r;
	    	
	    	
	    	
	    }

	    public long getNumeroVille(String nom) {
	    	
	    	
	    	List<Ville> s=this.getVillesFromServer();
	    	for(Ville b:s) {
	    		if(b.getNom().equalsIgnoreCase(nom)) {
	    			System.out.println(b.getNumero());
	    			return b.getNumero();
	    		}
	    	}
	    	
			return 0;
	    	
	    }
	    
	    /*public  Ville getVille(long id){
	    	
	        String body = null;
	        String url = "http://localhost:4567/api/villes/"+id;
	        Request request = new Request.Builder()
	                .url(url)
	                .build();
	        try (Response response = client.newCall(request).execute()) {
	            body = response.body().string();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        JsonArray villes = new JsonParser().parse(body).getAsJsonArray();
	        Ville s=new Ville();
	        for (int i = 0; i < villes.size(); i++) {
	            JsonObject member = (JsonObject) villes.get(i);
	            String nom = member.get("nom").getAsString();
	            Integer numero = member.get("numero").getAsInt();
	            // String ville = member.get("ville") != JsonNull.INSTANCE ? member.get("ville").getAsString() : null;
	            s.put(nom,new Ville(numero,nom));
	        }
	        return s;
	    }*/
}
