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


import model.SmartBuilding;
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
public class SmartBuildingHtttp {
    OkHttpClient client ;
	Gson gson;
	public SmartBuildingHtttp() {
		this.client= new OkHttpClient();
		gson = new Gson();
	}
	
	/*public static void main(String [] args ) {

		SmartBuildingHtttp service=new SmartBuildingHtttp();
		service.getSmartBuildingFromServer().forEach(v->System.out.println(v)) ;
		//service.addSmartBuilding(new SmartBuilding(3,325,33.9,7.9));
	      }*/
	
	
	private List<SmartBuilding> parse(String responseBody) {

    	List<SmartBuilding>smartBuildings=new ArrayList<>() ;
    	
        /*JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();*/
        
    	//JsonObject response = JsonParser.parseString(responseBody).getAsJsonObject();

    	 JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
        	JsonObject obj = (JsonObject)response.get(i);
            long numero= obj.get("numero").getAsLong();
            int nombreEtage=obj.get("nombreEtages").getAsInt();
            int surface=obj.get("surface").getAsInt();
            double longitude= obj.get("longitude").getAsDouble();
            double latitude= obj.get("latitude").getAsDouble();
            String adress= obj.get("adress").getAsString();
            
            
            SmartBuilding b1=new SmartBuilding(numero,nombreEtage, surface, longitude, latitude, adress);
            smartBuildings.add(b1);
        }
            
            return smartBuildings;
        }
	
	
	public List<SmartBuilding> getSmartBuildingFromServer(long id){
List<SmartBuilding> resultats=new ArrayList<>();
/*if(id==0){
    SmartBuilding b1=new SmartBuilding(2, 6, 300, "aaaaaaaaaaaaaaaaa");
    resultats.add(b1);
}*/
String body=null;
OkHttpClient clent = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/smartBuildings/"+id)
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
	
	
	  public void addSmartBuilding(SmartBuilding s,long idVille) {
	    	String json=gson.toJson(s);
	    	OkHttpClient clent= new OkHttpClient().newBuilder()
	    			  .build();
	    			MediaType mediaType = MediaType.parse("application/json");
	    			RequestBody body = RequestBody.create(json,mediaType
	    			);
	    			Request request = new Request.Builder()
	    			  .url("http://localhost:4567/api/smartBuildings/"+idVille+"/add")
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
	  
	  public void deleteSmartBuilding(SmartBuilding s) {
		  String json=gson.toJson(s);
		  OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
				  .url("http://localhost:4567/api/smartBuildings//remove")
				  .method("DELETE", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					System.out.println(response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  }
	  public SmartBuilding updateSmartBuilding(SmartBuilding s) {
		  String json=gson.toJson(s);
		  OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
				  .url("http://localhost:4567/api/smartBuildings//update")
				  .method("PUT", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					System.out.println(response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return s;
	  }
	  
	  public SmartBuilding getSmartBuildingById(SmartBuilding s) {
		  String json=gson.toJson(s);
		  OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				Request request = new Request.Builder()
				  .url("http://localhost:4567//api/smartBuildings/smartBuilding/")
				  .method("GET", null)
				  .addHeader("Content-Type", "application/json")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					System.out.println(response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return s;
		  
	  }
}
