package assessment.ssf.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<LineItem> contents =new LinkedList<LineItem>();
    
    public List<LineItem> getContents() {
        return contents;
    }

    public void setContents(List<LineItem> contents) {
        this.contents = contents;
    }

    public void addItemToCart(LineItem item){
        this.contents.add(item);
    }
    
}