package com.RestClient.Services;

import com.RestClient.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    private RestTemplate restTemplate;

    private String crmRestUrl;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public CustomerServiceImpl(RestTemplate theRestTemplate,
                                         @Value("${crm.rest.url}") String theUrl) {
        restTemplate = theRestTemplate;
        crmRestUrl = theUrl;

        logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
    }

    @Override
    public List<Customer> getCustomers() {

        logger.info("In getCustomers(): Calling REST API "+crmRestUrl);

        //the REST call
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {});

        List<Customer> customers = responseEntity.getBody();

        logger.info("In getCustomers(): customers "+customers);

        return customers;
    }

    @Override
    public Customer getCustomerByID(int theId) {

        logger.info("in getCustomerById(): Calling REST API " + crmRestUrl);

        // make REST call
        Customer theCustomer =
                restTemplate.getForObject(crmRestUrl + "/" + theId,
                        Customer.class);

        logger.info("in saveCustomerById(): theCustomer=" + theCustomer);

        return theCustomer;
    }

    @Override
    public List<Customer> getCustomerByName(String searchName) {
        logger.info("in getCustomerByName(): Calling REST API " + crmRestUrl+ "/" + searchName);

        //the REST call
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl+ "/" + searchName, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {});

        List<Customer> customers = responseEntity.getBody();

        logger.info("in saveCustomerByName(): theCustomer=" + customers);

        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        logger.info("in saveCustomer(): Calling REST API " + crmRestUrl);

        int employeeId = theCustomer.getId();

        // make REST call
        if (employeeId == 0) {
            // add employee
            restTemplate.postForEntity(crmRestUrl, theCustomer, String.class);

        } else {
            // update employee
            restTemplate.put(crmRestUrl, theCustomer);
        }

        logger.info("in saveCustomer(): success");
    }

    @Override
    public void deleteCustomer(int theId) {

        logger.info("in deleteCustomer(): Calling REST API " + crmRestUrl);

        // make REST call
        restTemplate.delete(crmRestUrl + "/" + theId);

        logger.info("in deleteCustomer(): deleted customer theId=" + theId);
    }
}
