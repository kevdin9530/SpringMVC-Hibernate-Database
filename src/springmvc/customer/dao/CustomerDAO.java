package springmvc.customer.dao;

import java.util.List;

import springmvc.customer.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer aCustomer);

	public Customer getCustomer(int aId);

	public void deleteCustomer(int aId);

	public List<Customer> search(String theName);

	public List<Customer> getCustomers(int theSortField);
}
