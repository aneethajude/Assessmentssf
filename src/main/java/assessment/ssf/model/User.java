package assessment.ssf.model;

import jakarta.json.JsonObject;

public class User {
	private String name;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    public static User create(JsonObject json) {
		User user = new User();
		user.setName(json.getString("name"));
		user.setAddress(json.getString("address"));
		return user;
	}
}
