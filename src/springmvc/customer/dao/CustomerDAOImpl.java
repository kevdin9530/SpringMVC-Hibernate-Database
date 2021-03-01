package springmvc.customer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvc.customer.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//create a query
		Query<Customer> aQuery = currentSession.createQuery("from Customer", Customer.class);
		//execute query and get result
		List<Customer> customers = aQuery.getResultList();
		//return result
		return customers;
	}

}
