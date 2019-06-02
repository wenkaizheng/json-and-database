package database.db1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class searchUI extends Application {
	private TextArea show;
	//private String   aim =null;
	private TextField tf1;
	
	public static void main(String[] args) {

		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		 primaryStage.setTitle("DataBase Searcher");
		 VBox vbox = new VBox();
		 insertIntroduce(vbox);
		 insertName(vbox);
		 insertEnter(vbox);
		 Scene scene = new Scene(vbox, 574, 616);
		 show =new TextArea("No result yet");
		 insertResult(vbox,show);
	     primaryStage.setScene(scene);
	     primaryStage.setResizable(false);
	     primaryStage.show();
	}
	private void insertEnter(VBox vbox) {
		// TODO Auto-generated method stub
		 HBox hbox1 =new HBox();
		 Button button =new Button("Enter");
		 button.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent me) {
					// implements TODO
					if(tf1.getText().length()!=0) {
						// we need to do the search with db
						System.out.println("yes");
					}
				}
		 });
		 hbox1.getChildren().add(button);
		 hbox1.setPadding(new Insets(6, 6, 6, 6));
		 vbox.getChildren().add(hbox1);
		 
	}
	private void insertResult(VBox vbox,TextArea show) {
		 HBox hbox1 =new HBox();
		 hbox1.getChildren().add(show);
		 hbox1.setPadding(new Insets(6, 6, 6, 6));
		 vbox.getChildren().add(hbox1);
		
	}
	private void insertIntroduce(VBox vbox) {
		// TODO Auto-generated method stub
		HBox hbox1 =new HBox();
		Text t1 =new Text("Welcome to weapons search");
		t1.setFont(Font.font("Verdana",20));
		t1.setFill(Color.RED);
		hbox1.getChildren().add(t1);
		hbox1.setPadding(new Insets(6, 6, 6, 6));
		vbox.getChildren().add(hbox1);
		
	}
	private void insertName(VBox vbox) {
		HBox hbox1 =new HBox();
		
		Text ask1 =new Text("type the name of weapons         ");
		tf1 =new TextField();
		hbox1.getChildren().add(ask1);
		hbox1.getChildren().add(tf1);
		hbox1.setPadding(new Insets(6, 6, 6, 6));
		vbox.getChildren().add(hbox1);
		
		
		
	}

}
   