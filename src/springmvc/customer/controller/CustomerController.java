package springmvc.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springdemo.ult.SortUtils;
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
	public String listCustomers(Model model, @RequestParam(required=false) String sort) {
		
		List<Customer> theCustomers=null;
		if(sort!=null) {
				int theSortField = Integer.parseInt(sort);
				theCustomers = customerService.getCustomers(theSortField);
		}
		else
		//get customer from DAO
		theCustomers= customerService.getCustomers(SortUtils.LAST_NAME);
		
		//add the customers to the model
		model.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showAddCustomerForm")
	public String showAddCustomerForm(Model model) {
		Customer aCustomer = new Customer();
		//create model attribute to bind form data
		model.addAttribute("customer",aCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer aCustomer) {
		
		//save customer using our service
		customerService.saveCustomer(aCustomer);
		
		return "redirect:/customer/list";
	}
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("customerId") int aId, Model model) {
		//get customer from the database
		Customer aCustomer = customerService.getCustomer(aId);
		
		//set customer as a model attribute to pre-populate the form
		model.addAttribute("customer",aCustomer);
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int aId,Model model) {
		//delete customer
		customerService.deleteCustomer(aId);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String deleteCustomer(@RequestParam("theSearchName") String theName, Model model) {
		List<Customer> customers = customerService.search(theName);
		model.addAttribute("customers", customers);
		return "list-customers";
	}
}
