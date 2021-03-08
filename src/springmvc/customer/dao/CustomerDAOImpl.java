package springmvc.customer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springdemo.ult.SortUtils;
import springmvc.customer.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	//@Transactional
	public List<Customer> getCustomers() {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//create a query and sort by Last name
		Query<Customer> aQuery = currentSession.createQuery("from Customer order by lastName", 
															Customer.class);
		//execute query and get result
		List<Customer> customers = aQuery.getResultList();
		//return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer aCustomer) {
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//save/update the customer
		currentSession.saveOrUpdate(aCustomer);
	}

	@Override
	public Customer getCustomer(int aId) {
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//get a customer according to id
		return currentSession.get(Customer.class, aId);
		
	}

	@Override
	public void deleteCustomer(int aId) {
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//delete a customer according to id
		Query theQuery = currentSession.createQuery("delete from Customer where id=:CustomerId");
		theQuery.setParameter("CustomerId", aId);
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> search(String theName) {
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//get list of customer according to the input name
		Query <Customer>theQuery = null;
		if(theName!=null || theName.trim().length()>0) {
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName",Customer.class);
			theQuery.setParameter("searchName", "%"+ theName.toLowerCase() + "%");
		}
		else{
			theQuery = currentSession.createQuery("from Customer",Customer.class);
		}
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public List<Customer> getCustomers(int theSortField) {
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		String theFieldName = null;
		switch(theSortField) {
		case SortUtils.FIRST_NAME:
			theFieldName= "firstName";
			break;
		case SortUtils.LAST_NAME:
			theFieldName= "lastName";
			break;
		case SortUtils.EMAIL:
			theFieldName= "email";
			break;
		}
		
		Query <Customer>theQuery = currentSession.createQuery("from Customer order by " + theFieldName,Customer.class);
		
		return theQuery.getResultList();
	}

}
