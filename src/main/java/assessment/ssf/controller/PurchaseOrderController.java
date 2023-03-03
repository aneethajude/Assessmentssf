package assessment.ssf.controller;

import assessment.ssf.model.Quotation;

import assessment.ssf.service.QuotationService;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assessment.ssf.model.LineItem;
import assessment.ssf.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseOrderController {

    @Autowired
	private QuotationService quoteSvc;

	@GetMapping("/index")
	String index(Model model, HttpSession session) {
		ArrayList<LineItem> arrayList = new ArrayList<>();
		model.addAttribute("items", arrayList);
		session.setAttribute("itemList", arrayList);
		return "index";
	}
    
    @RequestMapping(value="/items", method=RequestMethod.POST, params="action=add")
    public String add(@ModelAttribute("item") LineItem item, Model model, HttpSession session) {
    	ArrayList<LineItem> arrayList = new ArrayList<>();
    	
    	if(session.getAttribute("itemList") != null)
    		arrayList = (ArrayList<LineItem>) session.getAttribute("itemList");
    	
    	arrayList.add(item);
    	model.addAttribute("items", arrayList);
    	session.setAttribute("itemList", arrayList);
		return "index";
    }

    @RequestMapping(value="/items", method=RequestMethod.POST, params="action=next")
    public String next(@ModelAttribute User user, BindingResult bindingResult, Model model, HttpSession session) {
    	ArrayList<LineItem> arrayList = new ArrayList<>();
    	
    	if(session.getAttribute("itemList") != null)
    		arrayList = (ArrayList<LineItem>) session.getAttribute("itemList");
    	
    	if (bindingResult.hasErrors()) {
            //errors processing
        } 
    	
    	model.addAttribute("getdata", user.toString());
    	model.addAttribute("user", user);
    	session.setAttribute("itemList", arrayList);
    	return "view2";
    }
    
    @RequestMapping(value="/checkout", method=RequestMethod.POST, params="action=back")
    public String back(@ModelAttribute("item") LineItem item, BindingResult bindingResult, Model model, HttpSession session) {
    	ArrayList<LineItem> arrayList = new ArrayList<>();
    	
    	if(session.getAttribute("itemList") != null)
    		arrayList = (ArrayList<LineItem>) session.getAttribute("itemList");
            // List<ObjectError> errors = quoteSvc.validateOrder(item);
            // if (!errors.isEmpty()) {
            //     for (ObjectError err: errors)
            //         bindingResult.addError(err);
            //     return "index";
            // }
    	
    	arrayList.add(item);
    	model.addAttribute("items", arrayList);
    	session.setAttribute("itemList", arrayList);
		return "index";
    }

    @RequestMapping(value="/checkout", method=RequestMethod.POST, params="action=checkout")
    public String checkout(@ModelAttribute User user, BindingResult bindingResult, Model model, HttpSession session) {
    	ArrayList<LineItem> arrayList = new ArrayList<>();
    	
    	if(session.getAttribute("itemList") != null)
    		arrayList = (ArrayList<LineItem>) session.getAttribute("itemList");
    	
    	if (bindingResult.hasErrors()) {
            //errors processing
        } 
    	
      //  Quotation quotation = quoteSvc.saveCartOrder(user,arrayList);


    	model.addAttribute("getdata", user.toString());
    	model.addAttribute("user", user);
    	model.addAttribute("itemList", arrayList);
    	session.setAttribute("itemList", arrayList);
    	return "view3";
    }
    
}
