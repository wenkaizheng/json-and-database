package database.db2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class App implements Serializable {
  

  public  String getText(String url) throws Exception {
    URL website = new URL(url);
    URLConnection connection = website.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
    StringBuilder response = new StringBuilder();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      response.append(inputLine);

    in.close();
    String now  =response.toString();
    File file =new File("saveData");
    if(file.exists()) {
    	// we need to compare to check do we need to update or not
    	try {
    		ObjectInputStream objectInputStream =
    	            new ObjectInputStream(new FileInputStream("saveData"));
            String previous =(String)objectInputStream.readObject();
            objectInputStream.close();
            if(now.equals(previous))
            	return null;

         } catch(IOException e) {
        	 
         }
    	
    }
    else {
    try {
     	ObjectOutputStream objectOutputStream =
                 new ObjectOutputStream(new FileOutputStream("saveData"));
     	objectOutputStream.writeObject(response.toString());
         objectOutputStream.close();         

     } catch(IOException e) {
     }
    }
    return now;
  }

  public void apiReader() throws JSONException, Exception {

    System.setProperty("http.agent", "Chrome");
    db.startConnect();
    String data =getText("https://api.warframestat.us/weapons");
    if (data ==null)
    	return;
    JSONArray jsonArray = new JSONArray(data);
    System.out.printf("Welcome to star war weapon search page there are totally %d weapon to choose\n",
        jsonArray.length());
   // Scanner read = new Scanner(System.in);
    //String Name = read.nextLine();
    // iterate loop
    
   
    db.create();
    
    
  
    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject obj = jsonArray.getJSONObject(i);
        System.out.println(obj);
        String name = obj.getString("name");
      
        int damage = obj.getInt("totalDamage");
        double persecond = obj.getDouble("secondsPerShot");
        int accuracy = obj.getInt("accuracy");
        String des = obj.getString("description");
        int bp=0;
        try {
           bp =obj.getInt("buildPrice");
        }
        catch(JSONException ex) {
        	System.out.println("this weapon does not have price\n");
        }
       
        String url =new String("");
        try {
        	 
             JSONArray ja =obj.getJSONArray("patchlogs");
             JSONObject newObj=ja.getJSONObject(0);
             url =newObj.getString("imgUrl");
         }
         catch(JSONException ex) {
         	System.out.println("this weapon does not have imgurl\n");
         }
        
        weapon init =new weapon(name, damage, persecond, accuracy, des,bp,url);
      
        
        db.insert(init,i);
        
    }
    
        
      
    
  
  }

}