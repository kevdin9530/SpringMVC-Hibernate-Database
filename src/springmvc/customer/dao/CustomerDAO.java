package springmvc.customer.dao;

import java.util.List;

import springmvc.customer.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
}
