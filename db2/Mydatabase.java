package database.db2;

import org.json.JSONException;

import javafx.application.Application;

public class Mydatabase {
	public static void main(String[] args) throws JSONException, Exception {
		

	    App app =new App();
		app.apiReader();
		
	    Application.launch(searchUI.class, args);
	}
	
    
}
