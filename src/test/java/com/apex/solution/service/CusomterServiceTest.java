package com.apex.solution.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apex.solution.dto.CustomerPoint;
import com.apex.solution.model.Customer;
import com.apex.solution.model.Purchase;
import com.apex.solution.repository.CustomerRepository;
import com.apex.solution.repository.CustomerRepositoryTest;
import com.apex.solution.service.CustomerService;


/**
 * 
 * @author turik.campbell
 *
 */
public class CusomterServiceTest {

	private Logger logger
    = LoggerFactory.getLogger(CusomterServiceTest.class);
	
@Test
public void testGetCustomerPoints() throws Exception
{
    CustomerRepository repository;
	CustomerService service;
	 
	Customer c1= new Customer();
	c1.setFirstName("Tony");
	c1.setLastName("Stark");
	c1.setId(1);
	
	Customer c2= new Customer();
	c2.setFirstName("Nick");
	c2.setLastName("Fury");
	c2.setId(2);
	
	Purchase order1 = new Purchase();
	Date order1Date=new SimpleDateFormat("dd/MM/yyyy").parse("02/17/2023");  
	
	order1.setOrderId(1);
	order1.setTotal(120);
	order1.setCustomer(c1);
	order1.setOrderDate(order1Date);
	
	
	Purchase order2 = new Purchase();
	Date order2Date=new SimpleDateFormat("dd/MM/yyyy").parse("01/17/2023");  
	
	order2.setOrderId(1);
	order2.setTotal(110);
	order2.setCustomer(c1);
	order2.setOrderDate(order2Date);
	
	Purchase order3 = new Purchase();
	Date order3Date=new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2023");  
	
	order3.setOrderId(1);
	order3.setTotal(105);
	order3.setCustomer(c2);
	order3.setOrderDate(order3Date);

	List<Purchase> expected = Arrays.asList(
				order1,
				order2,
				order3
			);
	
    repository = Mockito.mock(CustomerRepository.class);
    
    service = new CustomerService(repository);
    
    
    when(repository.getPurchases(anyObject(),anyObject())).thenReturn(expected);
    
    Date startDate =new SimpleDateFormat("MM-yyyy").parse("01-2023"); 
    Date endDate =  new SimpleDateFormat("MM-yyyy").parse("02-2023"); 
    
    List<CustomerPoint> customerPoints = service.getPoints(startDate, endDate);
    
     customerPoints.forEach(
    		        x -> logger.debug(x.toString())
    		     );
     
    assertEquals(2, customerPoints.size());
    
}
/**
 * 
 * 
 * 
 */
 
@Test
 public void testCustomerRewards()
 {
	 CustomerRepository rep = new CustomerRepository(null);
	 CustomerService service = new CustomerService(rep);
	 
     Customer c1= new Customer();
     c1.setFirstName("Tony");
     c1.setLastName("Stark");
	 c1.setId(1);
		
		
	 Purchase order1 = new Purchase();
	 order1.setOrderId(1);
	 order1.setTotal(120);
	 order1.setCustomer(c1);
	 
	 Purchase order2 = new Purchase();
	 order2.setOrderId(2);
	 order2.setTotal(70);
	 order2.setCustomer(c1);
	 
	 Purchase order3 = new Purchase();
	 order3.setOrderId(3);
	 order3.setTotal(80);
	 order3.setCustomer(c1);
	 
	 List<Purchase> list = Arrays.asList(
				order1,
				order2,
				order3
			);
	 
	 list.forEach( x -> logger.debug(x.toString()));
	 
	 
	 int rewardPoints = service.calculateRewardPoints(list);
	 
	 
	 assertEquals (140, rewardPoints);
	 
 }

@Test
public void testCustomerRewardsforSinglePurchase()
{
	CustomerRepository rep = new CustomerRepository(null);
    CustomerService service = new CustomerService(rep);
	 
    Customer c1= new Customer();
    c1.setFirstName("Tony");
    c1.setLastName("Stark");
	 c1.setId(1);
		
		
	 Purchase order1 = new Purchase();
	 order1.setOrderId(1);
	 order1.setTotal(120);
	 order1.setCustomer(c1);
	 
	 
	 List<Purchase> list = Arrays.asList(
				order1
			);
	 int rewardPoints = service.calculateRewardPoints(list);
	 
	 assertEquals (90, rewardPoints);
}
}
