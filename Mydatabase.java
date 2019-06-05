package database.db2;

import org.json.JSONException;

import javafx.application.Application;

public class Mydatabase {
	public static void main(String[] args) throws JSONException, Exception {
		if(db.exist()==1) {
		  App app =new App();
		  app.apiReader();
		}
		db DB =new db();
	    Application.launch(searchUI.class, args);
	}
	
    
}
