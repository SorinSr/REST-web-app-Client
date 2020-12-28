package com.RestClient.Services;

import com.RestClient.Model.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    Customer getCustomerByID(int customerId);

    void deleteCustomer(int customerId);

    List <Customer> getCustomerByName(String searchName);
}
