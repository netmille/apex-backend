package com.apex.solution.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.apex.solution.model.Customer;
import com.apex.solution.model.Purchase;
/**
 * 
 * @author turik.campbell
 *
 */
@Repository
public class CustomerRepository {

   
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate)
    {
    	this.jdbcTemplate=jdbcTemplate;
    }
    
    private static final String GET_PURCHASES="SELECT C.FIRST_NAME, C.LAST_NAME, P.ID, P.CUSTOMER_ID, P.ORDER_TOTAL, P.ORDER_DATE  FROM CUSTOMER C, PURCHASE P WHERE C.ID=P.CUSTOMER_ID AND ( P.ORDER_DATE >= ? AND P.ORDER_DATE < ? )";
	
    
    /**
	 *  map record to Purchase domain object
	 *  
	 * @return
	 */
	public List<Purchase> getPurchases(Date startDate, Date endDate) {
		
		return jdbcTemplate.query(GET_PURCHASES, new PreparedStatementSetter() {
            
        public void setValues(PreparedStatement preparedStatement) throws SQLException {
           preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
           preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
        }
     }, new RowMapper<Purchase>() {

			public Purchase mapRow(ResultSet rs, int arg1) throws SQLException {
				Customer c = new Customer();
			    c.setLastName(rs.getString("LAST_NAME"));
			    c.setFirstName(rs.getString("FIRST_NAME"));
			    c.setId(rs.getInt("CUSTOMER_ID"));
			    	
			    Purchase p = new Purchase();
			    p.setCustomer(c);
			    p.setOrderDate(rs.getDate("ORDER_DATE"));
			    p.setOrderId(rs.getInt("ID"));
			    p.setTotal(rs.getDouble("ORDER_TOTAL"));
	
				return p;
			}

		} );
	}
  

}
