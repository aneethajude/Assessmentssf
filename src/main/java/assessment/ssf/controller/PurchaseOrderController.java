package assessment.ssf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {

    // @Autowired
	// private OrderService orderSvc;

    @GetMapping(path={"/", "/view1.html"})
	public String getIndex(Model model, HttpSession sess) {
		sess.invalidate();
	//	model.addAttribute("pizza", new Pizza());
		return "view1";
	}
    
}
