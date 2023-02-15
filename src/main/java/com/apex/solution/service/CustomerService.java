package com.apex.solution.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apex.solution.dto.CustomerPoint;
import com.apex.solution.model.Purchase;
import com.apex.solution.repository.CustomerRepository;
/**
 * 
 * 
 * @author turik.campbell
 *
 */
@Service
public class CustomerService {

	private CustomerRepository repository;
   
	@Autowired
	public CustomerService(CustomerRepository repository)
	{
		this.repository=repository;
	}
	
    /**
     * helper routine to return summary of customer's monthly reward points
     */
    public List<CustomerPoint> getPoints(Date startDate, Date endDate)
    {
    	List<CustomerPoint> points = new ArrayList();
    	List<Purchase> p = repository.getPurchases(startDate, endDate);
    	Map<Integer,List<Purchase>> purchasesByCustomer=new HashMap<>();
        purchasesByCustomer = p.stream().collect(Collectors.groupingBy(x -> x.getCustomer().getId()));
        purchasesByCustomer.forEach(
        		(Integer key, List<Purchase> customerPurchases) ->
        		  {
        			  Map<String,List<Purchase>> monthlyPurchasesforCustomer =customerPurchases.stream().collect(Collectors.groupingBy(x -> getMonthYear(x.getOrderDate())));
        			  monthlyPurchasesforCustomer.forEach((String monthYear, List<Purchase> monthlyPurchases) ->
        			    {
        			      CustomerPoint cp = new CustomerPoint();
        			      cp.setYear(getYear(monthlyPurchases.get(0).getOrderDate()));
        			      cp.setMonth(getMonth(monthlyPurchases.get(0).getOrderDate()));
        			      cp.setCustomerId(key);
        			      cp.setFirstName(customerPurchases.get(0).getCustomer().getFirstName());
        			      cp.setLastName(customerPurchases.get(0).getCustomer().getLastName());
        			      cp.setPoints(this.calculateRewardPoints(monthlyPurchases));
        			      points.add(cp);
        			     }
        		  );  
        		  }	
        		);
        return points;
    }
    
    /**
     * 
     * @param date
     * @return
     */
    private int getYear(Date date)
    {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    	return calendar.get(Calendar.YEAR) ;
    }
    /**
     * 
     * @param date
     * @return
     */
    private int getMonth(Date date)
    {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    	return calendar.get(Calendar.MONTH);
    }
    
    /**
     * 
     * @param date
     * @return
     */
    private String getMonthYear(Date date )
    {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    	return calendar.get(Calendar.MONTH) + ""+ calendar.get(Calendar.YEAR);
    }
	/**.
	 *  
	 * helper routine to sum customer's reward point for a month
	 * 
	 * @param orders
	 * @return
	 */
	public int calculateRewardPoints(List<Purchase> orders)
	{
		Double r = orders.stream().mapToDouble( x -> calculateRewardPoints(x)).sum();
		return r.intValue();
	}
	
	/**
	 * helper routine to calculate customer's reward points
	 * @param order
	 * @return
	 */
	private double calculateRewardPoints(Purchase order)
	{
		double goldPoints=0;
		double silverPoints=0;
		double totalOrderPoints=0;
		double orderTotal = order.getTotal();
		
		//Calculate Gold points (2 points) 
		if (orderTotal> 100)
		{
			double amountOver100 = orderTotal -100;
		    goldPoints= amountOver100 * 2;
		    //silverPoints is maximum of 50;
		    silverPoints=50;
		    totalOrderPoints = goldPoints + silverPoints;
		}
		//Calculate SilverPoints (No Gold Points earned)
		else if (orderTotal > 50  && orderTotal < 100) {
			silverPoints= orderTotal - 50;
			totalOrderPoints=silverPoints;
		}
		return totalOrderPoints;
	}

}
