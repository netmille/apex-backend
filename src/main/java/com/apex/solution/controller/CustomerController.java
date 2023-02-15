package com.apex.solution.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apex.solution.service.CustomerService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author turik.campbell
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    
    private Logger logger
    = LoggerFactory.getLogger(CustomerController.class);
    
    @ApiOperation(value = "Get Customer Points by startDate & endDate ", response = Iterable.class, tags = "Customer Reward Points")
    @GetMapping("/customer/points/{startDate}/{endDate}")
    public List getCustomerPoints(@PathVariable String startDate, @PathVariable String endDate) throws Exception {
    	
        Date sDate =new SimpleDateFormat("MM-yyyy").parse(startDate); 
        Date eDate =  new SimpleDateFormat("MM-yyyy").parse(endDate); 
        
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(eDate);
        
        calEnd.add(Calendar.MONTH, 1);
        eDate = calEnd.getTime();
        
        if(sDate.compareTo(eDate) > 0) {
        	 String msg ="End Date must be equal are after start Date";
        	 
        	 logger.error(msg);
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
        }
  
        return customerService.getPoints(sDate , eDate);
    }
    
}