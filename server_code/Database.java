/*
* EE422C Final Project submission by
* Replace <...> with your actual data.
* <Ahad Karedia>
* <aak3229>
* <17805>
* Spring 2021
*/


import java.util.Iterator;
import org.bson.Document;
import org.json.JSONObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.google.gson.Gson;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.*;

public class Database {
	private ConnectionString cs;
	private MongoClientSettings settings;
	private MongoClient mongoClient;
	private MongoDatabase db;
	private MongoCollection<Document> users;
	private MongoCollection<Document> items;
	
	public Database() {
		// mongodb+srv://ahad770:<password>@stackx.h7wb0.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
		//sarah1
		cs = new ConnectionString("mongodb+srv://ahad770:sarah1@stackx.h7wb0.mongodb.net/StackX?retryWrites=true&w=majority");
		settings = MongoClientSettings.builder().applyConnectionString(cs).build();
		mongoClient = MongoClients.create(settings);
		db = mongoClient.getDatabase("final");
		users = db.getCollection("users");
		items = db.getCollection("items");
	}
	
	/*
	 * getAllUsers() will return an ArrayList of Users from the database
	 */
	
	public ArrayList<User> getAllUsers(){
		Gson g = new Gson();
		ArrayList<User> users = new ArrayList<User>();
		FindIterable<Document> documents = this.users.find();
		Iterator<Document> it = documents.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			User u = g.fromJson(doc.toJson(), User.class);
			//System.out.println(u.getName());
			users.add(u);
		}
		return users;
	}
	
	/*
	 * getAllItems() will return an ArrayList of Items from the database
	 */
	public ArrayList<Item> getAllItems(){
		Gson g = new Gson();
		ArrayList<Item> items = new ArrayList<Item>();
		FindIterable<Document> documents = this.items.find();
		Iterator<Document> it = documents.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			Item i = g.fromJson(doc.toJson(), Item.class);
			System.out.println(i.getName() + " " + i.isSold());
			items.add(i);
		}
		return items;
	}
	
	/*
	 * updateUser(User user) will update the given user's history and add it to the database
	 */
	public void updateUser(User user) {
		FindIterable<Document> documents = users.find();
		Iterator<Document> it = documents.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			JSONObject docJSON = new JSONObject(doc.toJson());
			String username = docJSON.getString("username");
			//Changed below
			if(username.equals(user.getUsername())) {
				String history = user.getHistory();
				System.out.println("Updating history to: " + history);
				users.updateOne(eq("username", username),  new Document("$set", new Document("history", history)));
			}
		}
	}
	/*
	 * addUser(User user) will add the given user to the database
	 */
	public void addUser(User user) {
		Document userDoc = new Document("username", user.getUsername())
				.append("name", user.getName())
				.append("password", user.getPassword())
				.append("history", user.getHistory());
		users.insertOne(userDoc);
	}
	
	/*
	 * updateItem(Item item) will update the given Item's history and add it to the database
	 */
	public void updateItem(Item item) {
		FindIterable<Document> documents = items.find();
		Iterator<Document> it = documents.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			JSONObject docJSON = new JSONObject(doc.toJson());
			String name = docJSON.getString("name");
			if(name.equals(item.getName())) {
				String history = item.getHistory();
				items.updateOne(eq("name", name),  new Document("$set", new Document("history", history)));
				if(item.isSold()) {
					items.updateOne(eq("name", name),  new Document("$set", new Document("sold", true)));
					items.updateOne(eq("name", name),  new Document("$set", new Document("owner", item.getOwner())));
				} else {
					items.updateOne(eq("name", name),  new Document("$set", new Document("bidprice", Double.valueOf(item.getBidprice()))));
				}
			}
		}
	}
	

}
