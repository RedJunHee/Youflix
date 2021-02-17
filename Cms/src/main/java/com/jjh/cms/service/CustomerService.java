package com.jjh.cms.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.jjh.cms.model.Customer;

@Service
public class CustomerService {

	private int customerIdCount = 1;
	private  List<Customer> customerList = new CopyOnWriteArrayList<>();
	
	public Customer addCustomer(Customer customer)
	{
		customer.setCustomerId(customerIdCount);
		customerList.add(customer);
		customerList.add(customer);
		customerIdCount++;
		return customer;
	}
	
	
	public List<Customer> getCustomers()
	{
		return customerList;
	}
	
	public Customer getCustomer ( int customerId)
	{
		return customerList
				.stream()
				.filter(c -> c.getCustomerId() == customerId)
				.findFirst()
				.get();
	}
	
	public Customer updateCustomer(int customerId, Customer customer){
		customerList
				.stream()
				.forEach(c-> {
					if(c.getCustomerId() == customerId) {
						c.setCustomerEmail(customer.getCustomerEmail());
						c.setCustomerFirstName(customer.getCustomerFirstName());
						c.setCustomerLastName(customer.getCustomerLastName());
					}
				});
		return customerList
					.stream()
					.filter(c -> c.getCustomerId() == customerId)
					.findFirst()
					.get();
	}
	
	
	public void deleteCustomer(int customerId)
	{
		customerList
				.stream()
				.forEach(c -> {
					if(c.getCustomerId() == customerId)
						customerList.remove(c);
				});
	}
	
}
