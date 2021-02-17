package com.jjh.cms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjh.cms.model.Customer;
import com.jjh.cms.service.CustomerService;

@RestController
@RequestMapping(value= "/customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}
}
