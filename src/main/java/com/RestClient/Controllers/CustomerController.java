package com.RestClient.Controllers;

import com.RestClient.Model.Customer;
import com.RestClient.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
//    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customers-list";
    }

    @GetMapping("/showFormAddCustomer")
    public String showFormAddCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "show-form-add-customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateCustomer(@RequestParam("customerId") int customerId, Model model) {
        Customer customer = customerService.getCustomerByID(customerId);
        model.addAttribute("customer", customer);
        System.out.println("        Customer for auto-gen form:" + customer);
        return "show-form-add-customer";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }

    @GetMapping("/searchCustomerByName")
    public String searchCustomerByName(@RequestParam("searchName") String searchName, Model model) {
        List<Customer> customer = customerService.getCustomerByName(searchName);
        model.addAttribute("customers", customer);
        System.out.println("    Searched customer:" + customer);
        return "view-customer";
    }

}
