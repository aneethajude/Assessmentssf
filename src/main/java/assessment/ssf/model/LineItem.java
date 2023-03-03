package assessment.ssf.model;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class LineItem {
    private String item;
    private int quantity;

    public LineItem(){

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public static LineItem create(JsonObject json) {
        LineItem lineItem = new LineItem();
        lineItem.setItem(json.getString("item"));
        lineItem.setQuantity(json.getInt("quantity"));
        return lineItem;
    }

}
    

