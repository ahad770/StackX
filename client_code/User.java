/*
* EE422C Final Project submission by
* Replace <...> with your actual data.
* <Ahad Karedia>
* <aak3229>
* <17805>
* Spring 2021
*/

public class User {
	private String name;
	private String username;
	private String password;
	private String history;
	
	public User(String name, String username, String password) {
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		history = "";
	}
	
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public void addHistory(Item item) {
		// Making history so most recent is first
		String tmp = "";
		if(item.isSold()) {
			tmp = item.getName() + "\t" + item.getBuypriceView() + "\t(bought)" + "\n";
		} else {
			tmp = item.getName() + "\t" + item.getBidpriceView() + "\n";
		}
		tmp += getHistory();
		history = tmp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		if(name == null) {
			return username + " " + password;
		}
		return name + " "  + username + " " + password;
	}

	public String getHistory() {
		return history;
	}
}


