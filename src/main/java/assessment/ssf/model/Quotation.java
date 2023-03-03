package assessment.ssf.model;
// TODO: Add your package here

// DO NOT MODIFY THIS FILE

import java.util.HashMap;
import java.util.Map;

import java.io.StringReader;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Quotation {

    private String quoteId;
    private Map<String, Float> quotations = new HashMap<>();
    private float totalCost  ;

    private final User user;
	private final LineItem lineitem;

    public Quotation(User user, LineItem lineitem) {
		this.user = user;
		this.lineitem = lineitem;
	}

    public void setTotalCost(float totalCost) { this.totalCost = totalCost; }
	public float getTotalCost() { return this.totalCost; }

    public String getQuoteId() {
        return quoteId;
    }
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public Map<String, Float> getQuotations() {
        return quotations;
    }
    public void setQuotations(Map<String, Float> quotations) {
        this.quotations = quotations;
    }
    public void addQuotation(String item, Float unitPrice) {
        this.quotations.put(item, unitPrice);
    }
    public Float getQuotation(String item) {
        return this.quotations.getOrDefault((Object)item, -1000000f);
    }

  	public String getItem() { return this.lineitem.getItem(); }
	public int getQuantity() { return this.lineitem.getQuantity(); }
	public String getName() { return this.user.getName(); }
	public String getAddress() { return this.user.getAddress(); }

    public static JsonObject toJSON(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		return reader.readObject();
	}

    public static Quotation create(String str) {
		JsonObject json = toJSON(str);
		User user = User.create(json);
		LineItem lineitem = LineItem.create(json);
		Quotation quotation = new Quotation(user, lineitem);
		quotation.setQuoteId(json.getString("orderId"));
		quotation.setTotalCost((float)json.getJsonNumber("total").doubleValue());
		return quotation;
	}

    public JsonObject toJSON() {
		return Json.createObjectBuilder()
			.add("orderId", quoteId)
			.add("name", getName())
			.add("size", getItem())
			.add("quantity", getQuantity())
			.add("total", totalCost)
			.build();
	}


}
