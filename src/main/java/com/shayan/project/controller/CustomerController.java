package com.shayan.project.controller;

import com.shayan.project.model.CustomerTemp;
import com.shayan.project.repo.CustomerDAOImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by sraouf on 9/23/17.
 */
@RestController
@RequestMapping(value = "/customer", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(tags = {"Customer API"}, description = "Customer Endpoints")
public class CustomerController {

//    @Autowired
//    CustomerService customerService;
//
//    @Autowired
//    @Qualifier("branchDao")
    @Autowired
    CustomerDAOImpl customerDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/GET")
    @ApiOperation(value = "Gets all customers", notes = "TEST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "TEST")
    })
    public ResponseEntity<Map<String,String>> get() throws Exception{

        Map<String,String> map = new HashMap<>();

        map.put("name", "welcome to the pos_service customer page");
        map.put("response", HttpStatus.OK.toString());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ApiOperation(value = "Creates a new Customer")
    public ResponseEntity<CustomerTemp> createCustomer(
            @ApiParam(value = "customer", required = true)
            @RequestBody
            final CustomerTemp customer) throws Exception{
        customerDAO.insert(customer);
        return new ResponseEntity<CustomerTemp>(customer, HttpStatus.CREATED);
    }

}
