package com.shayan.project.repo;
import com.shayan.project.model.CustomerTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by sraouf on 11/9/17.
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO{

    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void insert(CustomerTemp employee){
        String sql = "INSERT INTO Customers " +
        "(UUID, LastName, FirstName, Address, City, PostalCode, Country) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, createCustomerFieldObjectArray(employee));
    }

    private Object[] createCustomerFieldObjectArray(CustomerTemp customer) {
        return new Object[]{
                customer.getUuid(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getAddress(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getCountry()
        };
    }

    public List<CustomerTemp> getAllPerson() {
        return jdbcTemplate.query("SELECT * FROM person", new RowMapper<CustomerTemp>() {
            public CustomerTemp mapRow(ResultSet rs, int arg1) throws SQLException {
                CustomerTemp customerTemp = new CustomerTemp();
                customerTemp.setUuid(rs.getInt("UUID"));
                customerTemp.setLastName(rs.getString("LastName"));
                customerTemp.setFirstName(rs.getString("FirstName"));
                customerTemp.setAddress(rs.getString("Address"));
                customerTemp.setCity(rs.getString("City"));
                customerTemp.setPostalCode(rs.getString("PostalCode"));
                customerTemp.setCountry(rs.getString("Country"));
                return customerTemp;
            }

        });
    }
}
