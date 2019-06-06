package database.db2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class App {
  

  public  String getText(String url) throws Exception {
    URL website = new URL(url);
    URLConnection connection = website.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
    StringBuilder response = new StringBuilder();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      response.append(inputLine);

    in.close();

    return response.toString();
  }

  public void apiReader() throws JSONException, Exception {

    System.setProperty("http.agent", "Chrome");
    JSONArray jsonArray = new JSONArray(getText("https://api.warframestat.us/weapons"));
    System.out.printf("Welcome to star war weapon search page there are totally %d weapon to choose\n",
        jsonArray.length());
   // Scanner read = new Scanner(System.in);
    //String Name = read.nextLine();
    // iterate loop
    db database =new db();
    database.create();
    
    
  
    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject obj = jsonArray.getJSONObject(i);
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

        weapon init =new weapon(name, damage, persecond, accuracy, des,bp);
      
        
        database.insert(init,i);
        
    }
    
        
      
    
  
  }

}