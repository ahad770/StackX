import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ClientUIStyle {
	public static void style() {
		Client.bidtext1.setPrefWidth(80);
		Client.bidtext2.setPrefWidth(80);
		Client.bidtext3.setPrefWidth(80);
		Client.bidtext4.setPrefWidth(80);
		Client.bidtext5.setPrefWidth(80);
		Client.bidtext6.setPrefWidth(80);
		
		Client.buy1.setPrefSize(100, 100);
		Client.buy2.setPrefSize(100, 100);
		Client.buy3.setPrefSize(100, 100);
		Client.buy4.setPrefSize(100, 100);
		Client.buy5.setPrefSize(100, 100);
		Client.buy6.setPrefSize(100, 100);
		
		Client.bid1.setPrefSize(100, 72);
		Client.bid2.setPrefSize(100, 72);
		Client.bid3.setPrefSize(100, 72);
		Client.bid4.setPrefSize(100, 72);
		Client.bid5.setPrefSize(100, 72);
		Client.bid6.setPrefSize(100, 72);
		
		Client.sold1.setVisible(false);
		Client.sold2.setVisible(false);
		Client.sold3.setVisible(false);
		Client.sold4.setVisible(false);
		Client.sold5.setVisible(false);
		Client.sold6.setVisible(false);
		
		Client.bid1price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.bid2price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.bid3price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.bid4price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.bid5price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.bid6price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		
		Client.buy1price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.buy2price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.buy3price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.buy4price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.buy5price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		Client.buy6price.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		
		Client.name1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		Client.name2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		Client.name3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		Client.name4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		Client.name5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		Client.name6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 20));
		
		Client.bid1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.bid2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.bid3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.bid4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.bid5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.bid6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		
		Client.buy1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.buy2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.buy3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.buy4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.buy5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.buy6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		
		Client.bid1.setStyle("-fx-background-color: #ff6961");
		Client.bid2.setStyle("-fx-background-color: #ff6961");
		Client.bid3.setStyle("-fx-background-color: #ff6961");
		Client.bid4.setStyle("-fx-background-color: #ff6961");
		Client.bid5.setStyle("-fx-background-color: #ff6961");
		Client.bid6.setStyle("-fx-background-color: #ff6961");
		
		Client.buy1.setStyle("-fx-background-color: #77dd77");
		Client.buy2.setStyle("-fx-background-color: #77dd77");
		Client.buy3.setStyle("-fx-background-color: #77dd77");
		Client.buy4.setStyle("-fx-background-color: #77dd77");
		Client.buy5.setStyle("-fx-background-color: #77dd77");
		Client.buy6.setStyle("-fx-background-color: #77dd77");
		
		Client.bidtext1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		Client.bidtext2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		Client.bidtext3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		Client.bidtext4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		Client.bidtext5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		Client.bidtext6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 10));
		
		Client.bidtext1.setAlignment(Pos.CENTER);
		Client.bidtext2.setAlignment(Pos.CENTER);
		Client.bidtext3.setAlignment(Pos.CENTER);
		Client.bidtext4.setAlignment(Pos.CENTER);
		Client.bidtext5.setAlignment(Pos.CENTER);
		Client.bidtext6.setAlignment(Pos.CENTER);
		
		Client.sold1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.sold2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.sold3.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.sold4.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.sold5.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.sold6.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		Client.sold1.setFill(Color.web("#ff6961"));
		Client.sold2.setFill(Color.web("#ff6961"));
		Client.sold3.setFill(Color.web("#ff6961"));
		Client.sold4.setFill(Color.web("#ff6961"));
		Client.sold5.setFill(Color.web("#ff6961"));
		Client.sold6.setFill(Color.web("#ff6961"));
		
		Client.item1gp.setStyle("-fx-background-color: #C0C0C0");
		Client.item2gp.setStyle("-fx-background-color: #C0C0C0");
		Client.item3gp.setStyle("-fx-background-color: #C0C0C0");
		Client.item4gp.setStyle("-fx-background-color: #C0C0C0");
		Client.item5gp.setStyle("-fx-background-color: #C0C0C0");
		Client.item6gp.setStyle("-fx-background-color: #C0C0C0");
		
		//Client.bid1price.setTextAlignment(CENTER);
		GridPane.setHalignment(Client.bid1price, HPos.CENTER);
		GridPane.setHalignment(Client.bid2price, HPos.CENTER);
		GridPane.setHalignment(Client.bid3price, HPos.CENTER);
		GridPane.setHalignment(Client.bid4price, HPos.CENTER);
		GridPane.setHalignment(Client.bid5price, HPos.CENTER);
		GridPane.setHalignment(Client.bid6price, HPos.CENTER);
		
		GridPane.setHalignment(Client.buy1price, HPos.CENTER);
		GridPane.setHalignment(Client.buy2price, HPos.CENTER);
		GridPane.setHalignment(Client.buy3price, HPos.CENTER);
		GridPane.setHalignment(Client.buy4price, HPos.CENTER);
		GridPane.setHalignment(Client.buy5price, HPos.CENTER);
		GridPane.setHalignment(Client.buy6price, HPos.CENTER);
		
		/*
		
		GridPane.setHalignment(Client.buy1, HPos.RIGHT);
		GridPane.setHalignment(Client.buy2, HPos.RIGHT);
		GridPane.setHalignment(Client.buy3, HPos.RIGHT);
		GridPane.setHalignment(Client.buy4, HPos.RIGHT);
		GridPane.setHalignment(Client.buy5, HPos.RIGHT);
		GridPane.setHalignment(Client.buy6, HPos.RIGHT);
		
		/*
		Client.item1gp.setPadding(new Insets(10, 30, 10, 30));
		Client.item2gp.setPadding(new Insets(10, 30, 10, 30));
		Client.item3gp.setPadding(new Insets(10, 30, 10, 30));
		Client.item4gp.setPadding(new Insets(10, 30, 10, 30));
		Client.item5gp.setPadding(new Insets(10, 30, 10, 30));
		Client.item6gp.setPadding(new Insets(10, 30, 10, 30));
		*/
		
		Client.bidtext1.setPromptText("Bid Amount ($):");
		Client.bidtext2.setPromptText("Bid Amount ($):");
		Client.bidtext3.setPromptText("Bid Amount ($):");
		Client.bidtext4.setPromptText("Bid Amount ($):");
		Client.bidtext5.setPromptText("Bid Amount ($):");
		Client.bidtext6.setPromptText("Bid Amount ($):");
		
		
		Client.title.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 35));
		
		GridPane.setHalignment(Client.sold1, HPos.CENTER);
		GridPane.setHalignment(Client.sold2, HPos.CENTER);
		GridPane.setHalignment(Client.sold3, HPos.CENTER);
		GridPane.setHalignment(Client.sold4, HPos.CENTER);
		GridPane.setHalignment(Client.sold5, HPos.CENTER);
		GridPane.setHalignment(Client.sold6, HPos.CENTER);
		
		GridPane.setHalignment(Client.name1, HPos.CENTER);
		GridPane.setHalignment(Client.name2, HPos.CENTER);
		GridPane.setHalignment(Client.name3, HPos.CENTER);
		GridPane.setHalignment(Client.name4, HPos.CENTER);
		GridPane.setHalignment(Client.name5, HPos.CENTER);
		GridPane.setHalignment(Client.name6, HPos.CENTER);
		
		
		// Feedback messages
		Client.guesterror1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.guesterror2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.guesterror3.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror3.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.guesterror4.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror4.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.guesterror5.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror5.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.guesterror6.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		Client.biderror6.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 10));
		
		Client.guesterror1.setFill(Color.web("#D0342C"));
		Client.biderror1.setFill(Color.web("#D0342C"));
		Client.guesterror2.setFill(Color.web("#D0342C"));
		Client.biderror2.setFill(Color.web("#D0342C"));
		Client.guesterror3.setFill(Color.web("#D0342C"));
		Client.biderror3.setFill(Color.web("#D0342C"));
		Client.guesterror4.setFill(Color.web("#D0342C"));
		Client.biderror4.setFill(Color.web("#D0342C"));
		Client.guesterror5.setFill(Color.web("#D0342C"));
		Client.biderror5.setFill(Color.web("#D0342C"));
		Client.guesterror6.setFill(Color.web("#D0342C"));
		Client.biderror6.setFill(Color.web("#D0342C"));
		
		GridPane.setHalignment(Client.guesterror1, HPos.CENTER);
		GridPane.setHalignment(Client.guesterror2, HPos.CENTER);
		GridPane.setHalignment(Client.guesterror3, HPos.CENTER);
		GridPane.setHalignment(Client.guesterror4, HPos.CENTER);
		GridPane.setHalignment(Client.guesterror5, HPos.CENTER);
		GridPane.setHalignment(Client.guesterror6, HPos.CENTER);
		
		GridPane.setHalignment(Client.biderror1, HPos.CENTER);
		GridPane.setHalignment(Client.biderror2, HPos.CENTER);
		GridPane.setHalignment(Client.biderror3, HPos.CENTER);
		GridPane.setHalignment(Client.biderror4, HPos.CENTER);
		GridPane.setHalignment(Client.biderror5, HPos.CENTER);
		GridPane.setHalignment(Client.biderror6, HPos.CENTER);
		
		Client.back1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.back2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.back3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.back4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.back5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.back6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		
		Client.info1.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.info2.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.info3.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.info4.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.info5.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		Client.info6.setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 15));
		
		Client.history1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.history2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.history3.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.history4.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.history5.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		Client.history6.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		//gp.setPadding(new Insets(30, 50, 30, 50));
		//(top/right/bottom/left)
		
		// Comment out below 
		/*
		Client.item1gp.setAlignment(Pos.CENTER);
		Client.item2gp.setAlignment(Pos.CENTER);
		Client.item3gp.setAlignment(Pos.CENTER);
		Client.item4gp.setAlignment(Pos.CENTER);
		Client.item5gp.setAlignment(Pos.CENTER);
		Client.item6gp.setAlignment(Pos.CENTER);
		*/
	}
}
