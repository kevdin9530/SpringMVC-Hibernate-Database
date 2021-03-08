package springmvc.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.customer.dao.CustomerDAO;
import springmvc.customer.entity.Customer;
import springmvc.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	
	//inject Customer Service layer
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//get customer from DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
}
