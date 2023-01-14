import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Server extends Observable {
	static boolean startup = false;
    static Server server;
    private static ArrayList<Item> items = new ArrayList<Item>();
    private static ArrayList<User> users = new ArrayList<User>();
    
    public static void main (String [] args) {
    	Database db = new Database();
        server = new Server();
		users = db.getAllUsers();
		items = db.getAllItems();
			
        try {
			server.SetupNetworking(db);
		} catch (IOException e) {
			System.out.println("Error");
		}
    }
    
    private void SetupNetworking(Database db) throws IOException {
        @SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(4243);
        while (true) {
            Socket clientSocket = ss.accept();
            System.out.println("Connecting to ..." + clientSocket);
            
            //ClientObserver writer = new ClientObserver(clientSocket.getOutputStream());
            //writer.flush();
            
			ClientHandler handler = new ClientHandler(clientSocket, db);
            Thread t = new Thread(handler);
            t.start();
            
            this.addObserver(handler);
            System.out.println("got a connection");
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
    
    // Continuously monitoring for incoming messages
    class ClientHandler implements Runnable, Observer{
    	Gson g = new Gson();
        private ObjectInputStream reader;
        private ObjectOutputStream writer;
        private Database db;
        
        Socket clientSocket;
		@SuppressWarnings("unused")
		private User user;
        public ClientHandler(Socket clientSocket, Database db) {
        	try {
        		writer = new ObjectOutputStream(clientSocket.getOutputStream());
				reader = new ObjectInputStream(clientSocket.getInputStream());
				this.db = db;
				System.out.println("Initialize");
			} catch (IOException e) {
				System.out.println("Client Handler Error!");
				//e.printStackTrace();
			}
        }
        // Send item info to Clients
        public void sendingItemInfo() {
        	Gson g = new Gson();
        	synchronized(items) {
	        	try {
	        		for(int i = 0; i < items.size(); i++) {
	        			JsonObject j = new JsonObject();
	        			j.addProperty("type", "item");
	        			j.add("info", g.toJsonTree(items.get(i)));
	        			writer.writeUTF(j.toString());
	        			writer.flush();
	        		}
	        	} catch (Exception e) {
	        		System.out.println(e);
	        	}
        	}
        }
        
        public void updateItemInfo(Item item, User user) {
        	for(int i = 0; i < users.size(); i++) {
        		if(users.get(i).getName().equals(user.getName())) {
        			System.out.println("Updating user info");
        			user.addHistory(item);
        			users.set(i, user);
        			System.out.println();
        			db.updateUser(user);
        		}
        	}
        	for(int i = 0; i < items.size(); i++) {
        		if(item.equals(items.get(i))) {
        			System.out.println("Server update item: " + items.get(i));
        			item.addHistory(user);
        			items.set(i, item);
        			db.updateItem(item);
        		}
        	}
        	this.user = user;
        	
        	JsonObject j = new JsonObject();
			j.addProperty("type", "updateUser");
			j.add("user", g.toJsonTree(user));
			System.out.println("Update method" + j.toString());
			String update = j.toString();
			try {
				writer.writeUTF(update);
				writer.flush();
			}catch(Exception e) {
				System.out.println(e);
			}
			
			setChanged();
			notifyObservers(item);
			
        }
        
        public void addUser(User user) {
        	boolean error = false;
        	for(User u: users) {
        		if(u.getUsername().equals(user.getUsername())) {
        			System.out.println("Error User Exists");
        			error = true;
        		}
        	}
        	JsonObject j = new JsonObject();
        	if(!error) {
        		users.add(user);
    			j.addProperty("type", "sucess");
    			j.add("info", g.toJsonTree(user));
    			db.addUser(user);
    			users = db.getAllUsers();
        	} else {
    			j.addProperty("type", "error");
    			j.add("info", g.toJsonTree(""));
        	}
        	try {
				writer.writeUTF(j.toString());
				writer.flush();
				
			} catch (IOException e) {
				System.out.println(e);
			}
        }
        
        public void verifyUser(User user) {
        	boolean found = false;
        	for(User u: users) {
        		if(u.getUsername().equals(user.getUsername())) {
        			//found = true;
        			System.out.println("Username Exists");
        			if(u.getPassword().equals(getHashPassword(user.getPassword()))) {
	        			user = u;
	        			found = true;
        			}else {
        				System.out.println("Password doesn't match");
        			}
        		}
        	}
        	JsonObject j = new JsonObject();
        	if(!found) {
        		j.addProperty("type", "loginerror");
    			j.add("info", g.toJsonTree(""));
        	} else {
        		j.addProperty("type", "loginsucess");
    			j.add("info", g.toJsonTree(user));
        	}
        	try {
				writer.writeUTF(j.toString());
				writer.flush();
				
			} catch (IOException e) {
				System.out.println(e);
			}
        }
        
        public void run() {
        	synchronized(this) {
	        	try {
	        		while(true) {
	        			try {
	        				JsonObject j = JsonParser.parseString(reader.readUTF()).getAsJsonObject();
	        				String request = j.get("type").getAsString();
	        				
	        				if(request.equals("new")) {
	        					sendingItemInfo();
	        					startup = true;
	        				}
	        				else if(request.equals("update")) {
	        					updateItemInfo(g.fromJson(j.get("info"), Item.class), g.fromJson(j.get("user"), User.class));
	        				}
	        				else if(request.equals("new_user")) {
	        					addUser(g.fromJson(j.get("info"), User.class));
	        				}
	        				else if(request.equals("login")) {
	        					verifyUser(g.fromJson(j.get("info"), User.class));
	        				}
	        			}catch (Exception e) {
	        				//System.out.println(e);
	        			}
	        		}
	        	} catch (Exception e) {
	        		//System.out.println(e);
	        	}
        	}
         }
        
		public void update(Observable o, Object arg) {
			Gson g = new Gson();
			
			if(arg instanceof Item) {
				System.out.println("Item is found in server!");
				Item item = (Item) arg;
				JsonObject j = new JsonObject();
				j.addProperty("type", "update");
				j.add("info", g.toJsonTree(item));
				System.out.println("Update method" + j.toString());
				String update = j.toString();
				try {
					writer.writeUTF(update);
					writer.flush();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			if(arg instanceof User) {
				System.out.println("User is found in server!");
				User user = (User) arg;
				JsonObject j = new JsonObject();
				j.addProperty("type", "updateUser");
				j.add("user", g.toJsonTree(user));
				System.out.println("Update method" + j.toString());
				String update = j.toString();
				try {
					writer.writeUTF(update);
					writer.flush();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
    }
}
