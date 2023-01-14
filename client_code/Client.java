import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Client extends Application { 
	// I/O streams 
	static ObjectOutputStream toServer = null; 
	static ObjectInputStream fromServer = null;
	static Gson g = new Gson();

	static Item item1;
	static boolean slot1 = false;
	static Text name1 = new Text();
	static TextField bidtext1 = new TextField();
	static Text buy1price = new Text();
	static Text bid1price = new Text();
	
	static Item item2;
	static boolean slot2 = false;
	static Text name2 = new Text();
	static TextField bidtext2 = new TextField();
	static Text buy2price = new Text();
	static Text bid2price = new Text();
	
	static Item item3;
	static boolean slot3 = false;
	static Text name3 = new Text();
	static TextField bidtext3 = new TextField();
	static Text buy3price = new Text();
	static Text bid3price = new Text();
	
	static Item item4;
	static boolean slot4 = false;
	static Text name4 = new Text();
	static TextField bidtext4 = new TextField();
	static Text buy4price = new Text();
	static Text bid4price = new Text();
	
	static Item item5;
	static boolean slot5 = false;
	static Text name5 = new Text();
	static TextField bidtext5 = new TextField();
	static Text buy5price = new Text();
	static Text bid5price = new Text();
	
	static Item item6;
	static boolean slot6 = false;
	static Text name6 = new Text();
	static TextField bidtext6 = new TextField();
	static Text buy6price = new Text();
	static Text bid6price = new Text();
	
	static GridPane top = new GridPane(); 
	static GridPane item1gp = new GridPane(); 
	static GridPane item2gp = new GridPane(); 
	static GridPane item3gp = new GridPane(); 
	static GridPane item4gp = new GridPane(); 
	static GridPane item5gp = new GridPane(); 
	static GridPane item6gp = new GridPane(); 

	static Button buy1 = new Button("Buy Now");
	static Button buy2 = new Button("Buy Now");
	static Button buy3 = new Button("Buy Now");
	static Button buy4 = new Button("Buy Now");
	static Button buy5 = new Button("Buy Now");
	static Button buy6 = new Button("Buy Now");
	
	static Button bid1 = new Button("Bid");
	static Button bid2 = new Button("Bid");
	static Button bid3 = new Button("Bid");
	static Button bid4 = new Button("Bid");
	static Button bid5 = new Button("Bid");
	static Button bid6 = new Button("Bid");
	
	static Button quit = new Button("Quit");
	
	static Button info1 = new Button("More Info");
	static Button info2 = new Button("More Info");
	static Button info3 = new Button("More Info");
	static Button info4 = new Button("More Info");
	static Button info5 = new Button("More Info");
	static Button info6 = new Button("More Info");
	
	static Button back1 = new Button("Back");
	static Button back2 = new Button("Back");
	static Button back3 = new Button("Back");
	static Button back4 = new Button("Back");
	static Button back5 = new Button("Back");
	static Button back6 = new Button("Back");
	
	static Text sold1 = new Text("Sold");
	static Text sold2 = new Text("Sold");
	static Text sold3 = new Text("Sold");
	static Text sold4 = new Text("Sold");
	static Text sold5 = new Text("Sold");
	static Text sold6 = new Text("Sold");
	
	static Text history1 = new Text("");
	static Text history2 = new Text("");
	static Text history3 = new Text("");
	static Text history4 = new Text("");
	static Text history5 = new Text("");
	static Text history6 = new Text("");
	
	static ImageView img1;
	static ImageView img2;
	static ImageView img3;
	static ImageView img4;
	static ImageView img5;
	static ImageView img6;
	
	static Text title;
	
	// Login System
	static Button signup = new Button("Sign-Up");
	static Button login = new Button("Login");
	static Button back = new Button("Back");
	static Button logout = new Button("Logout");
	static Button profile = new Button("My Profile");
	
	static User user = new User();
	static Profile profile1 = null;
	static Text welcome = new Text("");
	
	static BorderPane bp = new BorderPane(); 
	static GridPane headerButtons = new GridPane();
	
	static Text biderror1 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	static Text biderror2 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	static Text biderror3 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	static Text biderror4 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	static Text biderror5 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	static Text biderror6 = new Text("Bid's must be [<] Current Bid and [>] Buy Now");
	
	static Text guesterror1 = new Text("You must login to place a bid!");
	static Text guesterror2 = new Text("You must login to place a bid!");
	static Text guesterror3 = new Text("You must login to place a bid!");
	static Text guesterror4 = new Text("You must login to place a bid!");
	static Text guesterror5 = new Text("You must login to place a bid!");
	static Text guesterror6 = new Text("You must login to place a bid!");
	
	public String randomWelcome() {
		String[] s = {"Welcome, ", "Hi, ", "Hello, ", "Hey, "};
		int rand = ThreadLocalRandom.current().nextInt(0, 3 + 1);
		return s[rand];
	}
	
	@Override
	public void start(Stage primaryStage) { 
		// Take out
		user = null;
		welcome.setText(randomWelcome() + "Guest");
		
		welcome.setVisible(true);
		logout.setVisible(false);
		profile.setVisible(false);
		
		biderror1.setVisible(false);
		biderror2.setVisible(false);
		biderror3.setVisible(false);
		biderror4.setVisible(false);
		biderror5.setVisible(false);
		biderror6.setVisible(false);
		
		guesterror1.setVisible(false);
		guesterror2.setVisible(false);
		guesterror3.setVisible(false);
		guesterror4.setVisible(false);
		guesterror5.setVisible(false);
		guesterror6.setVisible(false);
		
		back1.setVisible(false);
		back2.setVisible(false);
		back3.setVisible(false);
		back4.setVisible(false);
		back5.setVisible(false);
		back6.setVisible(false);
		
		item1gp.add(history1, 1, 1);
		item2gp.add(history2, 1, 1);
		item3gp.add(history3, 1, 1);
		item4gp.add(history4, 1, 1);
		item5gp.add(history5, 1, 1);
		item6gp.add(history6, 1, 1);
	
		history1.setVisible(false);
		history2.setVisible(false);
		history3.setVisible(false);
		history4.setVisible(false);
		history5.setVisible(false);
		history6.setVisible(false);
		
		// Sound
		String file = "ka-ching.mp3";
		Media sound= new Media(new File(file).toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);
		
		Scene scene = new Scene(bp, 900, 1000); 
		primaryStage.setTitle("StackX"); // Set the stage title 
		primaryStage.setScene(scene); // Place the scene in the stage 
		
		title = new Text("StackX");
		
		headerButtons.add(login, 0, 0);
		headerButtons.add(logout, 0, 0);
		headerButtons.add(signup, 0, 1);
		headerButtons.add(profile, 0, 1);
		
		top.add(title, 0, 0);
		top.add(welcome, 1, 0);
		top.add(headerButtons, 2, 0);
		bp.setTop(top); 
		
		bp.setBottom(quit);
		
		quit.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		quit.setPrefWidth(150);
		
		quit.setOnAction(e ->{
			System.exit(0);
		});
		
		signup.setOnAction(e ->{
			clearError();
			Signup signup = new Signup();
			primaryStage.getScene().setRoot(signup.getRoot());
			primaryStage.setWidth(400);
			primaryStage.setHeight(475);
			Signup.setPrimaryStage(primaryStage);
			signup.setup();
			Signup.submit.setOnAction(f ->{
				Signup.serverFail.setVisible(false);
				Signup.error.setVisible(false);
				if(signup.passwordCB.isVisible()) {
					signup.error();
				}else {
					signup.submit();
				}
				
			});
		});
		
		login.setOnAction(e ->{
			clearError();
			Login login = new Login();
			primaryStage.getScene().setRoot(login.getRoot());
			primaryStage.setWidth(400);
			primaryStage.setHeight(475);
			Login.setPrimaryStage(primaryStage);
			login.setup();
			
			login.submit.setOnAction(f ->{
				Login.serverFail.setVisible(false);
				Login.error.setVisible(false);
				if(login.passwordCB.isVisible()) {
					login.error();
				}else {
					login.submit();
				}
			});
		});
		
		logout.setOnAction(e ->{
			clearError();
			welcome.setText(randomWelcome() + "Guest");
			login.setVisible(true);
			logout.setVisible(false);
			signup.setVisible(true);
			profile.setVisible(false);
			user = null;
			profile1 = null;
		});
		
		profile.setOnAction(e ->{
			clearError();
			primaryStage.getScene().setRoot(profile1.getRoot());
			primaryStage.setWidth(400);
			primaryStage.setHeight(475);
			Profile.setPrimaryStage(primaryStage);
			profile1.setup();
		});
		
		GridPane gp = new GridPane();
		
		GridPane item1gpBid = new GridPane(); 
		GridPane item2gpBid = new GridPane(); 
		GridPane item3gpBid = new GridPane(); 
		GridPane item4gpBid = new GridPane(); 
		GridPane item5gpBid = new GridPane(); 
		GridPane item6gpBid = new GridPane();
		
		ClientUIStyle.style();
		style();
		
		gp.setPadding(new Insets(10, 20, 10, 20));
		gp.setHgap(10);
		gp.setVgap(10);
		
		info1.setOnAction(e ->{
			history1.setVisible(true);
			buy1price.setVisible(false);
			bid1price.setVisible(false);
			bidtext1.setVisible(false);
			buy1.setVisible(false);
			bid1.setVisible(false);
			sold1.setVisible(false);
			img1.setVisible(false);
			info1.setVisible(false);
			
			back1.setVisible(true);
		});
		
		back1.setOnAction(e ->{
			if(item1.isSold()) {
				img1.setVisible(true);
				sold1.setVisible(true);
				info1.setVisible(true);
				history1.setVisible(false);
				back1.setVisible(false);
			}else {
				buy1price.setVisible(true);
				bid1price.setVisible(true);
				bidtext1.setVisible(true);
				buy1.setVisible(true);
				bid1.setVisible(true);
				sold1.setVisible(false);
				img1.setVisible(true);
				info1.setVisible(true);
				history1.setVisible(false);
				back1.setVisible(false);
			}
		});
		
		info2.setOnAction(e ->{
			history2.setVisible(true);
			buy2price.setVisible(false);
			bid2price.setVisible(false);
			bidtext2.setVisible(false);
			buy2.setVisible(false);
			bid2.setVisible(false);
			sold2.setVisible(false);
			img2.setVisible(false);
			info2.setVisible(false);
			back2.setVisible(true);
		});
		
		back2.setOnAction(e ->{
			if(item2.isSold()) {
				img2.setVisible(true);
				sold2.setVisible(true);
				info2.setVisible(true);
				history2.setVisible(false);
				back2.setVisible(false);
			}else {
				buy2price.setVisible(true);
				bid2price.setVisible(true);
				bidtext2.setVisible(true);
				buy2.setVisible(true);
				bid2.setVisible(true);
				sold2.setVisible(false);
				img2.setVisible(true);
				info2.setVisible(true);
				history2.setVisible(false);
				back2.setVisible(false);
			}
		});
		
		info3.setOnAction(e ->{
			history3.setVisible(true);
			buy3price.setVisible(false);
			bid3price.setVisible(false);
			bidtext3.setVisible(false);
			buy3.setVisible(false);
			bid3.setVisible(false);
			sold3.setVisible(false);
			img3.setVisible(false);
			info3.setVisible(false);
			back3.setVisible(true);
		});
		
		back3.setOnAction(e ->{
			if(item3.isSold()) {
				img3.setVisible(true);
				sold3.setVisible(true);
				info3.setVisible(true);
				history3.setVisible(false);
				back3.setVisible(false);
			}else {
				buy3price.setVisible(true);
				bid3price.setVisible(true);
				bidtext3.setVisible(true);
				buy3.setVisible(true);
				bid3.setVisible(true);
				sold3.setVisible(false);
				img3.setVisible(true);
				info3.setVisible(true);
				history3.setVisible(false);
				back3.setVisible(false);
			}
		});
		
		info4.setOnAction(e ->{
			history4.setVisible(true);
			buy4price.setVisible(false);
			bid4price.setVisible(false);
			bidtext4.setVisible(false);
			buy4.setVisible(false);
			bid4.setVisible(false);
			sold4.setVisible(false);
			img4.setVisible(false);
			info4.setVisible(false);
			
			back4.setVisible(true);
		});
		
		back4.setOnAction(e ->{
			if(item4.isSold()) {
				img4.setVisible(true);
				sold4.setVisible(true);
				info4.setVisible(true);
				history4.setVisible(false);
				back4.setVisible(false);
			}else {
				buy4price.setVisible(true);
				bid4price.setVisible(true);
				bidtext4.setVisible(true);
				buy4.setVisible(true);
				bid4.setVisible(true);
				sold4.setVisible(false);
				img4.setVisible(true);
				info4.setVisible(true);
				history4.setVisible(false);
				back4.setVisible(false);
			}
		});
		info5.setOnAction(e ->{
			history5.setVisible(true);
			buy5price.setVisible(false);
			bid5price.setVisible(false);
			bidtext5.setVisible(false);
			buy5.setVisible(false);
			bid5.setVisible(false);
			sold5.setVisible(false);
			img5.setVisible(false);
			info5.setVisible(false);
			
			back5.setVisible(true);
		});
		
		back5.setOnAction(e ->{
			if(item5.isSold()) {
				img5.setVisible(true);
				sold5.setVisible(true);
				info5.setVisible(true);
				history5.setVisible(false);
				back5.setVisible(false);
			}else {
				buy5price.setVisible(true);
				bid5price.setVisible(true);
				bidtext5.setVisible(true);
				buy5.setVisible(true);
				bid5.setVisible(true);
				sold5.setVisible(false);
				img5.setVisible(true);
				info5.setVisible(true);
				history5.setVisible(false);
				back5.setVisible(false);
			}
		});
		
		info6.setOnAction(e ->{
			history6.setVisible(true);
			buy6price.setVisible(false);
			bid6price.setVisible(false);
			bidtext6.setVisible(false);
			buy6.setVisible(false);
			bid6.setVisible(false);
			sold6.setVisible(false);
			img6.setVisible(false);
			info6.setVisible(false);
			
			back6.setVisible(true);
		});
		
		back6.setOnAction(e ->{
			if(item6.isSold()) {
				img6.setVisible(true);
				sold6.setVisible(true);
				info6.setVisible(true);
				history6.setVisible(false);
				back6.setVisible(false);
			}else {
				buy6price.setVisible(true);
				bid6price.setVisible(true);
				bidtext6.setVisible(true);
				buy6.setVisible(true);
				bid6.setVisible(true);
				sold6.setVisible(false);
				img6.setVisible(true);
				info6.setVisible(true);
				history6.setVisible(false);
				back6.setVisible(false);
			}
		});
		
		/*
		 *  https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		 *  Change once a user is created 
		 *  Currently won't allow more than 2000
		*/
		
		bidtext1.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext1.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") && !newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext1.setText(oldValue);
		        }
		    }
		});
		
		bidtext2.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext2.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") &&!newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext2.setText(oldValue);
		        }
		    }
		});
		bidtext3.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext3.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") &&!newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext3.setText(oldValue);
		        }
		    }
		});
		
		bidtext4.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext4.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") &&!newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext4.setText(oldValue);
		        }
		    }
		});
		bidtext5.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext5.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") &&!newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext5.setText(oldValue);
		        }
		    }
		});
		
		bidtext6.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d+\\.\\d+")) {
		        	bidtext6.setText(newValue.replaceAll("\\d+\\.\\d+", ""));
		        }
		        if(!newValue.equals(" ") &&!newValue.equals("") && Double.valueOf(newValue) > 2000) {
		        	bidtext6.setText(oldValue);
		        }
		    }
		});
		
		item1gp.setHgap(5);
		item2gp.setHgap(5);
		item3gp.setHgap(5);
		item4gp.setHgap(5);
		item5gp.setHgap(5);
		item6gp.setHgap(5);
		
		//Column Row
		item1gp.add(name1, 1, 0);
		item2gp.add(name2, 1, 0);
		item3gp.add(name3, 1, 0);
		item4gp.add(name4, 1, 0);
		item5gp.add(name5, 1, 0);
		item6gp.add(name6, 1, 0);
		
		item1gp.add(sold1, 1, 2);
		item2gp.add(sold2, 1, 2);
		item3gp.add(sold3, 1, 2);
		item4gp.add(sold4, 1, 2);
		item5gp.add(sold5, 1, 2);
		item6gp.add(sold6, 1, 2);
		
		item1gp.add(back1, 2, 0);
		item2gp.add(back2, 2, 0);
		item3gp.add(back3, 2, 0);
		item4gp.add(back4, 2, 0);
		item5gp.add(back5, 2, 0);
		item6gp.add(back6, 2, 0);
		
		item1gp.add(info1, 2, 0);
		item2gp.add(info2, 2, 0);
		item3gp.add(info3, 2, 0);
		item4gp.add(info4, 2, 0);
		item5gp.add(info5, 2, 0);
		item6gp.add(info6, 2, 0);
		
		item1gpBid.add(bidtext1, 0, 0);
		item1gpBid.add(bid1, 0, 1);
		item1gp.add(item1gpBid, 0, 2);
		item1gp.add(biderror1, 1, 3);
		item1gp.add(guesterror1, 1, 3);
		
		item2gpBid.add(bidtext2, 0, 0);
		item2gpBid.add(bid2, 0, 1);
		item2gp.add(item2gpBid, 0, 2);
		item2gp.add(biderror2, 1, 3);
		item2gp.add(guesterror2, 1, 3);
		
		item3gpBid.add(bidtext3, 0, 0);
		item3gpBid.add(bid3, 0, 1);
		item3gp.add(item3gpBid, 0, 2);
		item3gp.add(biderror3, 1, 3);
		item3gp.add(guesterror3, 1, 3);
		
		item4gpBid.add(bidtext4, 0, 0);
		item4gpBid.add(bid4, 0, 1);
		item4gp.add(item4gpBid, 0, 2);
		item4gp.add(biderror4, 1, 3);
		item4gp.add(guesterror4, 1, 3);
		
		item5gpBid.add(bidtext5, 0, 0);
		item5gpBid.add(bid5, 0, 1);
		item5gp.add(item5gpBid, 0, 2);
		item5gp.add(biderror5, 1, 3);
		item5gp.add(guesterror5, 1, 3);
		
		item6gpBid.add(bidtext6, 0, 0);
		item6gpBid.add(bid6, 0, 1);
		item6gp.add(item6gpBid, 0, 2);
		item6gp.add(biderror6, 1, 3);
		item6gp.add(guesterror6, 1, 3);
		
		bid1.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item1.getBidprice()) < Double.valueOf(bidtext1.getText()) && Double.valueOf(bidtext1.getText()) < Double.valueOf(item1.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item1.setBidprice(Double.valueOf(bidtext1.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item1));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror1.setVisible(true);}
			}else {guesterror1.setVisible(true);}
		});
		
		bid2.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item2.getBidprice()) < Double.valueOf(bidtext2.getText()) && Double.valueOf(bidtext2.getText()) < Double.valueOf(item2.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item2.setBidprice(Double.valueOf(bidtext2.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item2));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror2.setVisible(true);}
			}else {guesterror2.setVisible(true);}
		});
		
		bid3.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item3.getBidprice()) < Double.valueOf(bidtext3.getText()) && Double.valueOf(bidtext3.getText()) < Double.valueOf(item3.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item3.setBidprice(Double.valueOf(bidtext3.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item3));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror3.setVisible(true);}
			}else {guesterror3.setVisible(true);}
		});
		
		bid4.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item4.getBidprice()) < Double.valueOf(bidtext4.getText()) && Double.valueOf(bidtext4.getText()) < Double.valueOf(item4.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item4.setBidprice(Double.valueOf(bidtext4.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item4));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror4.setVisible(true);}
			}else {guesterror4.setVisible(true);}
		});
		
		bid5.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item5.getBidprice()) < Double.valueOf(bidtext5.getText()) && Double.valueOf(bidtext5.getText()) < Double.valueOf(item5.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item5.setBidprice(Double.valueOf(bidtext5.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item5));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror5.setVisible(true);}
			}else {guesterror5.setVisible(true);}
		});
		
		bid6.setOnAction(e ->{
			clearError();
			if(user != null) {
				if((Double.valueOf(item6.getBidprice()) < Double.valueOf(bidtext6.getText()) && Double.valueOf(bidtext6.getText()) < Double.valueOf(item6.getBuyprice()))) {
					player.seek(Duration.ZERO);
					player.play();
					item6.setBidprice(Double.valueOf(bidtext6.getText()));
					JsonObject j = new JsonObject();
					j.addProperty("type", "update");
					j.add("user", g.toJsonTree(user));
					j.add("info", g.toJsonTree(item6));
					String update = j.toString();
					System.out.println("Updating: " + update);
					try {
						toServer.writeUTF(update);
						toServer.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else {biderror6.setVisible(true);}
			}else {guesterror6.setVisible(true);}
		});
		
		item1gp.add(bid1price, 0, 1);
		item2gp.add(bid2price, 0, 1);
		item3gp.add(bid3price, 0, 1);
		item4gp.add(bid4price, 0, 1);
		item5gp.add(bid5price, 0, 1);
		item6gp.add(bid6price, 0, 1);
	
		img1 = new ImageView();
		img2 = new ImageView();
		img3 = new ImageView();
		img4 = new ImageView();
		img5 = new ImageView();
		img6 = new ImageView();
		
		item1gp.add(img1, 1, 1);
		item2gp.add(img2, 1, 1);
		item3gp.add(img3, 1, 1);
		item4gp.add(img4, 1, 1);
		item5gp.add(img5, 1, 1);
		item6gp.add(img6, 1, 1);
		
		item1gp.add(buy1, 2, 2);
		item2gp.add(buy2, 2, 2);
		item3gp.add(buy3, 2, 2);
		item4gp.add(buy4, 2, 2);
		item5gp.add(buy5, 2, 2);
		item6gp.add(buy6, 2, 2);
		
		buy1.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item1.setOwner(user.getName());
				item1.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item1));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
			}else {guesterror1.setVisible(true);}
		});
		
		buy2.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item2.setOwner(user.getName());
				item2.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item2));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					//System.out.println(e1);
					//e1.printStackTrace();
				}
			}else {guesterror2.setVisible(true);}
		});
		
		buy3.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item3.setOwner(user.getName());
				item3.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item3));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					e1.printStackTrace();
				}
			}else {guesterror3.setVisible(true);}
		});
		
		buy4.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item4.setOwner(user.getName());
				item4.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item4));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					e1.printStackTrace();
				}
			}else {guesterror4.setVisible(true);}
		});
		
		buy5.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item5.setOwner(user.getName());
				item5.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item5));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					e1.printStackTrace();
				}
			}else {guesterror5.setVisible(true);}
		});
		
		buy6.setOnAction(e ->{
			clearError();
			if(user != null) {
				player.seek(Duration.ZERO);
				player.play();
				item6.setOwner(user.getName());
				item6.setSold(true, user);
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item6));
				j.add("user", g.toJsonTree(user));
				String update = j.toString();
				try {
					toServer.writeUTF(update);
					toServer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					e1.printStackTrace();
				}
			}else {guesterror6.setVisible(true);}
		});
		
		item1gp.add(buy1price, 2, 1);
		item2gp.add(buy2price, 2, 1);
		item3gp.add(buy3price, 2, 1);
		item4gp.add(buy4price, 2, 1);
		item5gp.add(buy5price, 2, 1);
		item6gp.add(buy6price, 2, 1);
		
		gp.add(item1gp, 0, 0); 
	    gp.add(item2gp, 1, 0); 
	    gp.add(item3gp, 0, 1);       
	    gp.add(item4gp, 1, 1); 
	    gp.add(item5gp, 0, 2); 
	    gp.add(item6gp, 1, 2); 
	    
	    
	    gp.setPrefSize(950, 950);
	    
		bp.setCenter(gp);
		
		primaryStage.show();
		setupNetwork();
		
		JsonObject j = new JsonObject();
		j.addProperty("type", "new");
		String startup = j.toString();
		try {
			System.out.println("Start up" + startup);
			toServer.writeUTF(startup);
			toServer.flush();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		new Thread(()->{
			try {
				while(true) {
					String serverInput = fromServer.readUTF();
					System.out.println(serverInput);
					input(serverInput);
				}
			}
			catch(Exception e) {
				System.out.println("nothing found");
			}
		}).start();
		
		GridPane.setHalignment(Client.img1, HPos.CENTER);
		GridPane.setHalignment(Client.img2, HPos.CENTER);
		GridPane.setHalignment(Client.img3, HPos.CENTER);
		GridPane.setHalignment(Client.img4, HPos.CENTER);
		GridPane.setHalignment(Client.img5, HPos.CENTER);
		GridPane.setHalignment(Client.img6, HPos.CENTER);
	}
	
	public void clearError() {
		biderror1.setVisible(false);
		biderror2.setVisible(false);
		biderror3.setVisible(false);
		biderror4.setVisible(false);
		biderror5.setVisible(false);
		biderror6.setVisible(false);
		
		guesterror1.setVisible(false);
		guesterror2.setVisible(false);
		guesterror3.setVisible(false);
		guesterror4.setVisible(false);
		guesterror5.setVisible(false);
		guesterror6.setVisible(false);
	}
	
	public void input(String serverInput) {
		try {
			JsonObject j = JsonParser.parseString(serverInput).getAsJsonObject();
			String t = j.get("type").getAsString();	
			if (t.equals("item")){
				itemHandler(g.fromJson(j.get("info"), Item.class));
			}else if(t.equals("updateUser")) {
				user = g.fromJson(j.get("user"), User.class);
				profile1.setUser(user);
			}
			else if(t.equals("update")) {
				Item i = g.fromJson(j.get("info"), Item.class);
				itemUpdateHandler(i);
			}
			else if(t.equals("error")) {
				System.out.println("Existing User!");
				Signup.recieveFail();
			}
			else if(t.equals("sucess")) {
				Signup.recieveSucess();
				
			}
			else if(t.equals("loginerror")) {
				Login.recieveFail();
			}
			else if(t.equals("loginsucess")) {
				Login.recieveSucess();
				user = g.fromJson(j.get("info"), User.class);
				profile1 = new Profile(user);
				welcome.setText(randomWelcome() + user.getName());
				welcome.setVisible(true);
				logout.setVisible(true);
				login.setVisible(false);
				signup.setVisible(false);
				profile.setVisible(true);
				
			}
		}catch(Exception e) {
				System.out.println(e);
			}
	}
	
	public static void itemHandler(Item item) {
		if(!slot1) {
			item1= item;
			history1.setText(item1.getHistory());
			name1.setText(item.getName());
			img1.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy1price.setText(item.getBuypriceView());
			bid1price.setText(item.getBidpriceView());
			slot1 = true;
			if(item.isSold()) {
				buy1price.setVisible(false);
				bid1price.setVisible(false);
				bidtext1.setVisible(false);
				buy1.setVisible(false);
				bid1.setVisible(false);
				sold1.setText(sold1.getText() + " to " + item1.getOwner() + " for " + item1.getBuypriceView());
				sold1.setVisible(true);
			}
		} else if(!slot2) {
			item2= item;
			history2.setText(item2.getHistory());
			name2.setText(item.getName());
			img2.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy2price.setText(item.getBuypriceView());
			bid2price.setText(item.getBidpriceView());
			slot2 = true;
			if(item.isSold()) {
				buy2price.setVisible(false);
				bid2price.setVisible(false);
				bidtext2.setVisible(false);
				buy2.setVisible(false);
				bid2.setVisible(false);
				sold2.setText(sold2.getText() + " to " + item2.getOwner()+ " for " + item2.getBuypriceView());
				sold2.setVisible(true);
			}
		} else if(!slot3) {
			item3= item;
			history3.setText(item3.getHistory());
			name3.setText(item.getName());
			img3.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy3price.setText(item.getBuypriceView());
			bid3price.setText(item.getBidpriceView());
			slot3 = true;
			if(item.isSold()) {
				buy3price.setVisible(false);
				bid3price.setVisible(false);
				bidtext3.setVisible(false);
				buy3.setVisible(false);
				bid3.setVisible(false);
				sold3.setText(sold3.getText() + " to " + item3.getOwner() + " for " + item3.getBuypriceView());
				sold3.setVisible(true);
			}
		} else if(!slot4) {
			item4= item;
			history4.setText(item4.getHistory());
			name4.setText(item.getName());
			img4.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy4price.setText(item.getBuypriceView());
			bid4price.setText(item.getBidpriceView());
			slot4 = true;
			if(item.isSold()) {
				buy4price.setVisible(false);
				bid4price.setVisible(false);
				bidtext4.setVisible(false);
				buy4.setVisible(false);
				bid4.setVisible(false);
				sold4.setText(sold4.getText() + " to " + item4.getOwner() + " for " + item4.getBuypriceView());
				
				sold4.setVisible(true);
			}
		} else if(!slot5) {
			item5= item;
			history5.setText(item5.getHistory());
			name5.setText(item.getName());
			img5.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy5price.setText(item.getBuypriceView());
			bid5price.setText(item.getBidpriceView());
			slot5 = true;
			if(item.isSold()) {
				buy5price.setVisible(false);
				bid5price.setVisible(false);
				bidtext5.setVisible(false);
				buy5.setVisible(false);
				bid5.setVisible(false);
				sold5.setText(sold5.getText() + " to " + item5.getOwner() + " for " + item5.getBuypriceView());
				
				sold5.setVisible(true);
			}
		} else if(!slot6) {
			item6= item;
			history6.setText(item6.getHistory());
			name6.setText(item.getName());
			img6.setImage(new Image(new File(item.getImage()).toURI().toString(), 150, 150, true , false));
			buy6price.setText(item.getBuypriceView());
			bid6price.setText(item.getBidpriceView());
			slot6 = true;
			if(item.isSold()) {
				buy6price.setVisible(false);
				bid6price.setVisible(false);
				bidtext6.setVisible(false);
				buy6.setVisible(false);
				bid6.setVisible(false);
				sold6.setText(sold6.getText() + " to " + item6.getOwner() + " for " + item6.getBuypriceView());
				
				sold6.setVisible(true);
			}
		}
	}
	
	public static void itemUpdateHandler(Item item) {
		if(item.equals(item1)) {
			item1= item;
			history1.setText(item1.getHistory());
			//item1.addHistory(user);
			if(!item.isSold()) {
				name1.setText(item.getName());
				buy1price.setText(item.getBuypriceView());
				bid1price.setText(item.getBidpriceView());
			}else {
				buy1price.setVisible(false);
				bid1price.setVisible(false);
				bidtext1.setVisible(false);
				buy1.setVisible(false);
				bid1.setVisible(false);
				sold1.setText(sold1.getText() + " to " + item1.getOwner() + " for " + item1.getBuypriceView());
				sold1.setVisible(true);
			}
		}
		else if(item.equals(item2)) {
			item2= item;
			history2.setText(item2.getHistory());
			if(!item.isSold()) {
				name2.setText(item.getName());
				buy2price.setText(item.getBuypriceView());
				bid2price.setText(item.getBidpriceView());
			}else {
				buy2price.setVisible(false);
				bid2price.setVisible(false);
				bidtext2.setVisible(false);
				buy2.setVisible(false);
				bid2.setVisible(false);
				sold2.setText(sold2.getText() + " to " + item2.getOwner()+ " for " + item2.getBuypriceView());
				sold2.setVisible(true);
			}
		}
		else if(item.equals(item3)) {
			item3= item;
			history3.setText(item3.getHistory());
			if(!item.isSold()) {
				name3.setText(item.getName());
				buy3price.setText(item.getBuypriceView());
				bid3price.setText(item.getBidpriceView());
			}else {
				buy3price.setVisible(false);
				bid3price.setVisible(false);
				bidtext3.setVisible(false);
				buy3.setVisible(false);
				bid3.setVisible(false);
				sold3.setText(sold3.getText() + " to " + item3.getOwner() + " for " + item3.getBuypriceView());
				sold3.setVisible(true);
			}
		}
		else if(item.equals(item4)) {
			item4= item;
			history4.setText(item4.getHistory());
			if(!item.isSold()) {
				
				name4.setText(item.getName());
				buy4price.setText(item.getBuypriceView());
				bid4price.setText(item.getBidpriceView());
			}else {
				buy4price.setVisible(false);
				bid4price.setVisible(false);
				bidtext4.setVisible(false);
				buy4.setVisible(false);
				bid4.setVisible(false);
				sold4.setText(sold4.getText() + " to " + item4.getOwner() + " for " + item4.getBuypriceView());
				sold4.setVisible(true);
			}
		}
		else if(item.equals(item5)) {
			item5= item;
			history5.setText(item5.getHistory());
			if(!item.isSold()) {
				name5.setText(item.getName());
				buy5price.setText(item.getBuypriceView());
				bid5price.setText(item.getBidpriceView());
			}else {
				buy5price.setVisible(false);
				bid5price.setVisible(false);
				bidtext5.setVisible(false);
				buy5.setVisible(false);
				bid5.setVisible(false);
				sold5.setText(sold5.getText() + " to " + item5.getOwner() + " for " + item5.getBuypriceView());
				sold5.setVisible(true);
			}
		}
		else if(item.equals(item6)) {
			item6= item;
			history6.setText(item6.getHistory());
			if(!item.isSold()) {
				name6.setText(item.getName());
				buy6price.setText(item.getBuypriceView());
				bid6price.setText(item.getBidpriceView());
			}else {
				buy6price.setVisible(false);
				bid6price.setVisible(false);
				bidtext6.setVisible(false);
				buy6.setVisible(false);
				bid6.setVisible(false);
				sold6.setText(sold6.getText() + " to " + item6.getOwner() + " for " + item6.getBuypriceView());
				sold6.setVisible(true);
			}
		}
	}
	
	public void style() {
		top.setPadding(new Insets(10, 45, 10, 45));
		top.setHgap(200);
		
		title.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 35));
		welcome.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		login.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		logout.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		signup.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		profile.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		
		login.setPrefWidth(150);
		logout.setPrefWidth(150);
		signup.setPrefWidth(150);
		profile.setPrefWidth(150);
	}
	
	
	public void setupNetwork(){
		try { 
			// Create a socket to connect to the server 
			@SuppressWarnings("resource")
			Socket sock = new Socket("localhost", 4243);
			System.out.println("Connecting to ..." + sock);
			
			// Create an input stream to receive data from the server 
			fromServer = new ObjectInputStream(sock.getInputStream());
			// Create an output stream to send data to the server 
			toServer = new ObjectOutputStream(sock.getOutputStream()); 

			System.out.println("networking established");
		} catch (IOException ex) { 
			System.out.println("Error on Client Side Connection");
		}
	}
		
	public static void main(String[] args) {
		launch(args);
	}

}


