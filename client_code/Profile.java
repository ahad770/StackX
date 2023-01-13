/*
* EE422C Final Project submission by
* Replace <...> with your actual data.
* <Ahad Karedia>
* <aak3229>
* <17805>
* Spring 2021
*/

import com.google.gson.Gson;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Profile {
	private User user;
	private static Stage primaryStage = null;
	
	static GridPane titlegp = new GridPane();
	static Text title = new Text("StackX");
	Button back = new Button("Back");
	
	static BorderPane profilebp;
	
	static Text history = new Text("Bid/Buy History");
	static Text items;
	
	static GridPane historygp = new GridPane();
	int gpidx = 1;
	
	static Gson g = new Gson();
	
	
	public Profile(User user) {
		profilebp = new BorderPane(); 
		this.user = user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public BorderPane getRoot() {
		return profilebp;
	}

	public void setup() {
		titlegp.add(title, 0, 0);
		titlegp.add(back, 1, 0);
		profilebp.setTop(titlegp);
		
		
		profilebp.setCenter(historygp);
		
		historygp.add(history, 0, 0);
		
		items = new Text(user.getHistory());
		historygp.add(items, 0, 1);
		//gp.add(, 0, 1);
		
		style();
		
		back.setOnAction(h ->{
			primaryStage.setWidth(900);
			primaryStage.setHeight(1000);
			titlegp.getChildren().clear();
			historygp.getChildren().clear();
			profilebp.getChildren().clear();
			primaryStage.getScene().setRoot(Client.bp);
		});
		
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Profile.primaryStage = primaryStage;
	}
	
	public void style() {
		historygp.setPadding(new Insets(10, 25, 10, 25));
		historygp.setVgap(15);
		//(top/right/bottom/left)
		
		// Title GridPane
		titlegp.setPadding(new Insets(10, 25, 10, 25));
		titlegp.setHgap(40);
		title.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 35));
		back.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
		
		// Feedback messages
		items.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		history.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		//serverFail.setFill(Color.web("#D0342C"));
		//sucess.setFill(Color.web("#4BB543"));
		
		//GridPane.setHalignment(sucess, HPos.CENTER);
	}
}