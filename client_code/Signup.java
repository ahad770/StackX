/*
* EE422C Final Project submission by
* Replace <...> with your actual data.
* <Ahad Karedia>
* <aak3229>
* <17805>
* Spring 2021
*/

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;

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

public class Signup {
	private static Stage primaryStage = null;
	Timer timer = new Timer();
	static GridPane titlegp = new GridPane();
	static Text title = new Text("StackX - Sign - Up");
	Button back = new Button("Back");
	static Text error = new Text("Please Hide Password!");
	
	static BorderPane signupbp;
	static Button submit = new Button("Submit");
	
	TextField username = new TextField();
	Text usernameFail = new Text("Username must be between 6 - 15 characters");
	
	TextField name = new TextField();
	Text nameFail = new Text("Name must be letters only.");
	
	PasswordField password = new PasswordField();
	PasswordField password2 = new PasswordField();
	TextField passwordCB = new TextField();
	TextField password2CB = new TextField();
	Text passwordFail = new Text("Passwords do not match.");
	Text passwordFailLen = new Text("Password must be at least 6 characters");
	
	static Text sucess = new Text("Account successfully created!");
	static Text serverFail = new Text("Username is taken!");
	static Gson g = new Gson();
	static GridPane gp = new GridPane();
	
	GridPane gpUsername = new GridPane();
	GridPane gpName = new GridPane();
	GridPane gpPassword = new GridPane();
	
	CheckBox cb = new CheckBox("Show Password?");
	
	public Signup() {
		signupbp = new BorderPane();
	}
	
	public BorderPane getRoot() {
		return signupbp;
	}
	
	public static void setPrimaryStage(Stage s) {
		primaryStage = s;
	}
	
	public void setup() {
		// Login button & TextFields
		titlegp.add(title, 0, 0);
		titlegp.add(back, 1, 0);
		signupbp.setTop(titlegp);
		
		usernameFail.setVisible(false);
		nameFail.setVisible(false);
		sucess.setVisible(false);
		passwordFail.setVisible(false);
		passwordFailLen.setVisible(false);
		passwordCB.setVisible(false);
		password2CB.setVisible(false);
		serverFail.setVisible(false);
		error.setVisible(false);
		
		signupbp.setCenter(gp);
		
		gp.add(gpUsername, 0, 0);
		gp.add(gpName, 0, 1);
		gp.add(gpPassword, 0, 2);
		gp.add(submit, 0, 5);
		gp.add(serverFail, 0, 6);
		gp.add(error, 0, 6);
		gp.add(sucess, 0, 6);
		
		gpUsername.add(username, 0, 0);
		gpUsername.add(usernameFail, 0, 1);
		
		gpName.add(name, 0, 0);
		gpName.add(nameFail, 0, 1);
		
		gpPassword.add(password, 0, 0);
		gpPassword.add(passwordCB, 0, 0);
		gpPassword.add(password2, 0, 1);
		gpPassword.add(password2CB, 0, 1);
		gpPassword.add(passwordFail, 0, 2);
		gpPassword.add(passwordFailLen, 0, 2);
		gpPassword.add(cb, 0, 3);
		
		
		username.setPromptText("Username");
		name.setPromptText("First Name");
		password.setPromptText("Password");
		password2.setPromptText("Confirm Password");
		
		style();
		
		back.setOnAction(h ->{
			primaryStage.setWidth(900);
			primaryStage.setHeight(1000);
			titlegp.getChildren().clear();
			gp.getChildren().clear();
			signupbp.getChildren().clear();
			primaryStage.getScene().setRoot(Client.bp);
		});
		
		cb.setOnAction(e ->{
			if(cb.isSelected()) {
				passwordCB.setVisible(true);
				password.setVisible(false);
				password2CB.setVisible(true);
				password2.setVisible(false);
				passwordCB.setText(password.getText());
				password2CB.setText(password2.getText());
			}else {
				passwordCB.setVisible(false);
				password.setVisible(true);
				password2CB.setVisible(false);
				password2.setVisible(true);
				password.setText(passwordCB.getText());
				password2.setText(password2CB.getText());
			}
		});
	}
	
	public void submit() {
		// Form validation
		boolean formValidation = true;
		
		if(!password.getText().equals(password2.getText())) {
			passwordFail.setVisible(true);
			formValidation = false;
		} else {passwordFail.setVisible(false);}
		if(password.getText().length() < 6) {
			passwordFailLen.setVisible(true);
			formValidation = false;
		} else { passwordFailLen.setVisible(false);}
		if(username.getText().length() < 6 || username.getText().length() > 15) {
			usernameFail.setVisible(true);
			formValidation = false;
		} else {usernameFail.setVisible(false);}
		if(!name.getText().matches("[a-zA-Z]+")){
			nameFail.setVisible(true);
			formValidation = false;
		} else {nameFail.setVisible(false);}
		
		if(formValidation) {
			User user = new User(name.getText(), username.getText(), getHashPassword(password.getText()));
			JsonObject j = new JsonObject();
			j.addProperty("type", "new_user");
			j.add("info", g.toJsonTree(user));
			String update = j.toString();
			System.out.println("Trying to create: " + update);
			try {
				Client.toServer.writeUTF(update);
				Client.toServer.flush();
			} catch (IOException e1) {
				System.out.println(e1);
				e1.printStackTrace();
			}
			
		}
	}
	
	public static String getHashPassword(String password) {
		MessageDigest md;
		try {
			md = java.security.MessageDigest.getInstance("MD5");
			
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger bigInt = new BigInteger(1, messageDigest);
			return bigInt.toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return null;
	}
	
	static public void recieveFail() {
		serverFail.setVisible(true);
	}
	
	static public void recieveSucess() {
		sucess.setVisible(true);
		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		delay.setOnFinished(e -> {
			primaryStage.setWidth(900);
			primaryStage.setHeight(1000);
			titlegp.getChildren().clear();
			gp.getChildren().clear();
			signupbp.getChildren().clear();
			primaryStage.getScene().setRoot(Client.bp);
		});
		delay.play();
	}
	
	public void style() {
		gp.setPadding(new Insets(10, 25, 10, 25));
		gp.setVgap(7);
		//(top/right/bottom/left)
		
		// Title GridPane
		titlegp.setPadding(new Insets(10, 25, 10, 25));
		titlegp.setHgap(30);
		title.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 35));
		back.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 12));
		
		// Feedback messages
		usernameFail.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		nameFail.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		passwordFail.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		passwordFailLen.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		serverFail.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		sucess.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		usernameFail.setFill(Color.web("#D0342C"));
		nameFail.setFill(Color.web("#D0342C"));
		passwordFail.setFill(Color.web("#D0342C"));
		passwordFailLen.setFill(Color.web("#D0342C"));
		serverFail.setFill(Color.web("#D0342C"));
		sucess.setFill(Color.web("#4BB543"));
		
		// TextFeilds
		username.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		name.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		password.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		password2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		passwordCB.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		password2CB.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		
		username.setPrefWidth(350);
		name.setPrefWidth(350);
		password.setPrefWidth(350);
		password2.setPrefWidth(350);
		passwordCB.setPrefWidth(350);
		password2CB.setPrefWidth(350);
		
		// Username
		gpUsername.setVgap(10);
		
		// Name
		gpName.setVgap(10);
		
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
