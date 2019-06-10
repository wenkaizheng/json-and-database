package database.db2;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import org.json.JSONException;

import javafx.application.Application;

public class Mydatabase {
	public static void main(String[] args) throws JSONException, Exception {
		
		
	    App app =new App();
		app.apiReader();
		
	    Application.launch(searchUI.class, args);
	}
	
    
}
