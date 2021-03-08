package springmvc.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.customer.dao.CustomerDAO;
import springmvc.customer.entity.Customer;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer aCustomer) {
		customerDAO.saveCustomer(aCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int aId) {
		return customerDAO.getCustomer(aId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int aId) {
		customerDAO.deleteCustomer(aId); 
		
	}

	@Override
	@Transactional
	public List<Customer> search(String theName) {
		return customerDAO.search(theName);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomers(theSortField);
	}

}
