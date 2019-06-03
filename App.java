package database.db1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
public class App {
  

  public String getText(String url) throws Exception {
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

  public void apiReader()throws Exception {

    System.setProperty("http.agent", "Chrome");
    JSONArray jsonArray = new JSONArray(getText("https://api.warframestat.us/weapons"));
    System.out.printf("Welcome to star war weapon search page there are totally %d weapon to choose\n",
        jsonArray.length());
   // Scanner read = new Scanner(System.in);
    //String Name = read.nextLine();
    // iterate loop
    db database =new db();
    if(database.create()==1) {
    	System.out.println("The database is already exist");
    	
    }
    else {
    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject obj = jsonArray.getJSONObject(i);
        String name = obj.getString("name");
      
        int damage = obj.getInt("totalDamage");
        double persecond = obj.getDouble("secondsPerShot");
        int accuracy = obj.getInt("accuracy");
        String des = obj.getString("description");

        weapon init =new weapon(name, damage, persecond, accuracy, des);
      
        
        database.insert(init,i);
        
    }
        
      
    }
  
  }

}