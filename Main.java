package database.db1;

import javafx.application.Application;

public class Main {
	public static void main(String[] args) {
	    App app =new App();
	    try {
			app.apiReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Application.launch(searchUI.class, args);
        


    }
}
