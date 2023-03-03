package assessment.ssf.service;

import assessment.ssf.repository.QuotationRepository;

import assessment.ssf.model.Quotation;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import assessment.ssf.model.*;
import java.util.Set;

@Service
public class QuotationService {

    @Autowired 
	private QuotationRepository quotationRepo;

    public static final String[] ITEM_NAMES = {
		"Apple", "Orange", "Bread", "Cheese", "Chicken","Mineral Water","Instant Noodles"
	};

    private final Set<String> itemNames;

    public QuotationService() {
		itemNames = new HashSet<>(Arrays.asList(ITEM_NAMES));
		
	}

    public Quotation getQuotations(List<String> items) throws Exception{

        return null;
    }
    public Quotation saveCartOrder(User user, LineItem item) {
		Quotation quotation = createCartOrder(user, item);
		calculateCost(quotation);
		quotationRepo.save(quotation);
		return quotation;
	}

	public Quotation createCartOrder(User user, LineItem item) {
		String quoteId = UUID.randomUUID().toString().substring(0, 8);
		Quotation quotation = new Quotation(user, item);
		quotation.setQuoteId(quoteId);
		return quotation;
	}

	public float calculateCost(Quotation quotation) {
		float total = 0f;

		switch (quotation.getItem()) {
			case "Apple":
            float Appprice = 0.3f;
            total = total + (Appprice *(quotation.getQuantity()));
		//	break;
			case "Orange":
            float Oraprice = 0.5f;
            total = total + (Oraprice *(quotation.getQuantity()));
		//		break;
			case "Bread":
            float Brprice = 0.9f;
            total = total + (Brprice *(quotation.getQuantity()));
		//		break;
            case "Cheese":
            float Chprice = 0.5f;
            total = total + (Chprice *(quotation.getQuantity()));
            case "Chicken":
            float Chiprice = 2.5f;
            total = total + (Chiprice *(quotation.getQuantity()));
            case "Mineral Water":
            float water = 0.9f;
            total = total + (water *(quotation.getQuantity()));
            case "Instant Noodles":
            float noodles = 0.9f;
            total = total + (noodles *(quotation.getQuantity()));
			default:
		}

		quotation.setTotalCost(total);

		return total;
	}

    public List<ObjectError> validateOrder(LineItem item) {
		List<ObjectError> errors = new LinkedList<>();
		FieldError error;

		if (!itemNames.contains(item.getItem().toLowerCase())) {
			error = new FieldError("pizza", "pizza"
					, "We do not have %s pizza".formatted(item.getItem()));
			errors.add(error);
		}


		return errors;
	}
    
}
