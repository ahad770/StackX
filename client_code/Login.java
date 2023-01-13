/*
* EE422C Final Project submission by
* Replace <...> with your actual data.
* <Ahad Karedia>
* <aak3229>
* <17805>
* Spring 2021
*/

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login {
	private static Stage primaryStage = null;
	
	static GridPane titlegp = new GridPane();
	static Text title = new Text("StackX - Log - In");
	Button back = new Button("Back");
	
	static BorderPane loginbp;
	Button submit = new Button("Submit");
	
	TextField username = new TextField();
	TextField passwordCB = new TextField();
	PasswordField password = new PasswordField();
	CheckBox cb = new CheckBox("Show Password?");
	
	static Text serverFail = new Text("Username and/or Password not found!");
	static Text error = new Text("Please Hide Password!");
	static Text sucess = new Text("Sucessfully Logged-In!");
	
	GridPane gpPassword = new GridPane();
	static GridPane gp = new GridPane();
	
	static Gson g = new Gson();
	
	public Login() {
		loginbp = new BorderPane(); 
	}
	
	public BorderPane getRoot() {
		return loginbp;
	}
	
	public void setup() {
		titlegp.add(title, 0, 0);
		titlegp.add(back, 1, 0);
		loginbp.setTop(titlegp);
		
		gpPassword.add(password, 0, 0);
		gpPassword.add(passwordCB, 0, 0);
		gpPassword.add(cb, 0, 1);
		
		serverFail.setVisible(false);
		sucess.setVisible(false);
		error.setVisible(false);
		passwordCB.setVisible(false);
		
		loginbp.setCenter(gp);
		
		gp.add(username, 0, 0);
		gp.add(gpPassword, 0, 1);
		gp.add(serverFail, 0, 2);
		gp.add(error, 0, 2);
		gp.add(sucess, 0, 2);
		gp.add(submit, 0, 3);
		
		username.setPromptText("Username");
		password.setPromptText("Password");
		
		style();
		
		back.setOnAction(h ->{
			primaryStage.setWidth(900);
			primaryStage.setHeight(1000);
			titlegp.getChildren().clear();
			gp.getChildren().clear();
			loginbp.getChildren().clear();
			primaryStage.getScene().setRoot(Client.bp);
		});
		
		cb.setOnAction(e ->{
			if(cb.isSelected()) {
				passwordCB.setVisible(true);
				password.setVisible(false);
				passwordCB.setText(password.getText());
			}else {
				passwordCB.setVisible(false);
				password.setVisible(true);
				password.setText(passwordCB.getText());
			}
		});
	}
	
	public void submit() {
		// Check if passwords match
		User user = new User(username.getText(), password.getText());
		// Test
		//System.out.println("Login " + user.toString());
		
		JsonObject j = new JsonObject();
		j.addProperty("type", "login");
		j.add("info", g.toJsonTree(user));
		String update = j.toString();
		System.out.println("Login attempt: " + update);
		try {
			Client.toServer.writeUTF(update);
			Client.toServer.flush();
		} catch (IOException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		
	}
	static public void recieveFail() {
		serverFail.setVisible(true);
	}
	
	static public void recieveSucess() {
		serverFail.setVisible(false);
		sucess.setVisible(true);
		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		delay.setOnFinished(e -> {
			primaryStage.setWidth(900);
			primaryStage.setHeight(1000);
			titlegp.getChildren().clear();
			gp.getChildren().clear();
			loginbp.getChildren().clear();
			primaryStage.getScene().setRoot(Client.bp);
		});
		delay.play();
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Login.primaryStage = primaryStage;
	}
	
	public void style() {
		gp.setPadding(new Insets(10, 25, 10, 25));
		gp.setVgap(15);
		//(top/right/bottom/left)
		
		// Title GridPane
		titlegp.setPadding(new Insets(10, 25, 10, 25));
		titlegp.setHgap(40);
		title.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 35));
		back.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
		
		// Feedback messages
		serverFail.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		sucess.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		error.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		serverFail.setFill(Color.web("#D0342C"));
		error.setFill(Color.web("#D0342C"));
		sucess.setFill(Color.web("#4BB543"));
		
		// TextFeilds
		username.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		password.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		passwordCB.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		
		username.setPrefWidth(350);
		password.setPrefWidth(350);
		password.setPrefWidth(350);
		
		// Passwords
		gpPassword.setVgap(10);
		cb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 14));
	
		// Submit
		submit.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
		submit.setPrefWidth(175);
		GridPane.setHalignment(submit, HPos.CENTER);
		GridPane.setHalignment(sucess, HPos.CENTER);
	}

	public void error() {
		// TODO Auto-generated method stub
		error.setVisible(true);
	}
}

