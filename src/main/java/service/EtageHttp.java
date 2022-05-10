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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Etage;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class EtageHttp {
    OkHttpClient client ;
	Gson gson;
	
	public EtageHttp() {
		this.client= new OkHttpClient();
		gson = new Gson();
	}
	
	/*public static void main(String [] args ) {

		 EtageHttp service=new EtageHttp();
		 //service.getEtageFromServer().forEach(v->System.out.println(v)) ;
		 service.addEtage(new Etage(3,320));
	      	    }*/
	
	public List<Etage> parse(String responseBody) {

    	List<Etage>etages=new ArrayList<>() ;
    	
        JsonArray response = JsonParser.parseString(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
            JsonObject obj = (JsonObject)response.get(i);
            long numEtage= obj.get("etageNum").getAsLong();
            double surface=obj.get("surface").getAsDouble();
            
            Etage e1=new Etage(numEtage,surface);
            etages.add(e1);
        }
            
            return etages;
        }

        //JsonObject member = new JsonParser().parse(responseBody).getAsJsonObject();

	public List<Etage> getEtageFromServer(long id){
            /*
    	List<Etage> resultats=new ArrayList<>();
    	  String body=null;
	        String url = "http://localhost:4567/api/etages/"+id;
OkHttpClient clent = new OkHttpClient().newBuilder()
     .build();
	        Request request = new Request.Builder()
	                .url(url)
	                .build();

	        try (Response response = clent.newCall(request).execute()) {
	            body = response.body().string();
	            resultats=parse(body);
	        }

	        catch(IOException e ) {
	            System.out.println(e.getMessage());
	        }
*/
          
            /*List<Etage> resultats=new ArrayList<>();
    	  String body=null;
            OkHttpClient clien = new OkHttpClient().newBuilder()
  .build();
Request request = new Request.Builder()
  .url("http://localhost:4567/api/etages/"+id)
  .method("GET", null)
  .build();
        try {
            Response response = clien.newCall(request).execute();
            body = response.body().string();
        } catch (IOException ex) {
            Logger.getLogger(EtageHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
		return resultats;
    	*/
            List<Etage> resultats=new ArrayList<>();
  	  	String body=null;
		OkHttpClient clien = new OkHttpClient().newBuilder()
				  .build();
				Request request = new Request.Builder()
				  .url("http://localhost:4567/api/etages/"+id)
				  .method("GET", null)
				  .build();
				try {
					Response response = clien.newCall(request).execute();
					body = response.body().string();
		            resultats=parse(body);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return resultats;
    }
	
	public void addEtage(Etage v) {
    	
    	String json=gson.toJson(v);
    	OkHttpClient client = new OkHttpClient().newBuilder()
    			  .build();
    			MediaType mediaType = MediaType.parse("application/json");
    			RequestBody body = RequestBody.create(mediaType, 
    			json);
    			Request request = new Request.Builder()
    			  .url("http://localhost:4567/api/etages")
    			  .method("POST", body)
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
    
	public void deleteEtage(Etage v) {
		
		String json=gson.toJson(v);
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
				  .url("http://localhost:4567/etage/delete")
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
        public Etage getVille(Long id1,Long id2) {
	    	Etage r=null;
	    	EtageHttp v= new EtageHttp();
	    	List<Etage> s=v.getEtageFromServer(id2);
	    	for(Etage b:s) {
	    		if (b.getNumEtage()==id1){
                            r=b;
	    		}
	    		
	    	}
	    	return r;
	    	
	    	
	    	
	    }
}
