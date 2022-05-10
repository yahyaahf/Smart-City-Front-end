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

import model.WorkSpace;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class WorkSpaceHttp {
    OkHttpClient client ;
	Gson gson;
	
	public WorkSpaceHttp() {
		this.client= new OkHttpClient();
		gson = new Gson();
	}
	
/*	 public static void main(String [] args ) {

		 WorkSpaceCnx connexion=new WorkSpaceCnx();
		 connexion.getWorkSpaceFromServer().forEach(v->System.out.println(v)) ;
	      	    }
	*/ 
	 private List<WorkSpace> parse(String responseBody) {

	    	List<WorkSpace>workspaces=new ArrayList<>() ;
	    	
	        JsonArray response = new JsonParser().parse(responseBody).getAsJsonArray();
	        //long numero, int surface, WorkSpaceType type, int etage, String position) {
	    		
	        for(int i=0;i<response.size();i++) {
	            JsonObject obj = (JsonObject)response.get(i);
                    
                    String type=obj.get("type").getAsString();
	            long numero= obj.get("numero").getAsLong();
                    String position=obj.get("position").getAsString();
	            int surface= obj.get("surface").getAsInt();
	            
	            int etage= obj.get("numeroEtage").getAsInt();
	            
	            
	            WorkSpace ws1=new WorkSpace(numero, surface, type, etage, position);
	            workspaces.add(ws1);
                    
	        }
	            
	            return workspaces;
	        }

	        //JsonObject member = new JsonParser().parse(responseBody).getAsJsonObject();

	 public List<WorkSpace> getWorkSpaceFromServer(long id){
	    	List<WorkSpace> resultats=new ArrayList<>();
                
                String body=null;
		    String url = "http://localhost:4567/api/workSpaces/"+id;

		        Request request = new Request.Builder()
		                .url(url)
		                .build();

		        try (Response response = client.newCall(request).execute()) {
		            body = response.body().string();
		            resultats=parse(body);
		        }
		        catch(IOException e ) {
		            System.out.println(e.getMessage());
		        }
			return resultats;
	  
	    }
	 
	 public void addWorkSpace(WorkSpace w) {
		 
		 String json=gson.toJson(w);
		 OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
				  .url("http://localhost:4567/api/workspaces")
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
	 public void deleteWorkSpace(WorkSpace w) {
		 
		 String json=gson.toJson(w);
		 OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
				  .url("http://localhost:4567/api/workspaces")
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
}
