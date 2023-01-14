public class Item {
	private String name;
	private String description;
	private double bidprice;
	private double buyprice;
	private boolean sold;
	private String image;
	private String history = "";
	private String owner = null;
	
	public Item(String name, double bidprice, double buyprice, String image, String description) {
		this.setName(name);
		this.setBidprice(bidprice);
		this.setBuyprice(buyprice);
		setSold(false, null);
		this.image = image;
		this.description = description;
	}
	
	public void addHistory(User user) {
		// Making history so most recent is first
			String tmp = "";
			if(isSold()) {
				tmp = user.getName() + "\t" + this.getBuypriceView() + "\t(bought)" + "\n";
			} else {
				tmp = user.getName() + "\t" + this.getBidpriceView() + "\n";
			}
			System.out.println(tmp);
			tmp += getHistory();
			history = tmp;
	}

	public String getHistory() {
		// TODO Auto-generated method stub
		return history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBidpriceView() {
		bidprice+=.00;
		return "$" + String.format("%.02f", bidprice);
	}
	
	public String getBidprice() {
		return "" + bidprice;
	}
	
	public String getBuyprice() {
		return "" + buyprice;
	}

	public void setBidprice(double bidprice) {
		this.bidprice = bidprice;
	}

	public String getBuypriceView() {
		buyprice+=.00;
		return "$" + String.format("%.02f", buyprice);
	}

	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}
	
	public String getImage() {
		return image;
	}
	
	
	public String toString() {
		return name + " " + bidprice + " " + buyprice + " " + description;
	}
	
	public boolean equals(Item i) {
        return (this.name.equals(i.name));
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold, User user) {
		if(sold == true) {setOwner(user.getName());}
		this.sold = sold;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}