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
import model.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *
 * @author Lenovo
 */
public class EmployeeHttp {

OkHttpClient client ;
Gson gson;
public EmployeeHttp() {
this.client= new OkHttpClient();
gson = new Gson();
}





private List<Employee> parse(String responseBody) {

    List<Employee>employees=new ArrayList<>() ;
   
        JsonArray response = JsonParser.parseString(responseBody).getAsJsonArray();

        for(int i=0;i<response.size();i++) {
            JsonObject obj = (JsonObject)response.get(i);                  
            String nom=obj.get("nom").getAsString();
    String prenom=obj.get("prenom").getAsString();
            String email= obj.get("email").getAsString();
            String sexe=obj.get("sexe").getAsString();
            String telephone= obj.get("telephone").getAsString();
            String adress= obj.get("adress").getAsString();
   
            Employee e1=new Employee(nom, prenom, email, sexe, telephone, adress);
            employees.add(e1);
           
        }    
            return employees;
        }

public void addEmployee(Employee e) {

String json=gson.toJson(e);
OkHttpClient client = new OkHttpClient().newBuilder()
 .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, json);
Request request = new Request.Builder()
 .url("http://localhost:4567/api/employees/:idEntreprise/add")
 .method("POST", body)
 .addHeader("Content-Type", "application/json")
 .build();
try {
Response response = client.newCall(request).execute();
System.out.println(response.body().string());
} catch (IOException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

public List<Employee> getEmployee(){
List<Employee> resultats=new ArrayList<>();
String body=null;
OkHttpClient client = new OkHttpClient().newBuilder()
 .build();
Request request = new Request.Builder()
 .url("http://localhost:4567/api/employees/:idEntreprise")
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
