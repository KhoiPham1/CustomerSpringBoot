package com.codegym.Customer.controller;

import com.codegym.Customer.model.Customer;
import com.codegym.Customer.model.Province;
import com.codegym.Customer.service.CustomerService;
import com.codegym.Customer.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;
    @ModelAttribute("province")
    public Iterable<Province> provinces(){return provinceService.findAll();}

    @GetMapping("/create-customers")
    public ModelAndView formCustomer(){
        ModelAndView modelAndView = new ModelAndView("create","customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customers")
    public ModelAndView createCustomer(@Validated @ModelAttribute("customer")Customer customer, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasFieldErrors()){
            return modelAndView;
        }else {
            customerService.save(customer);
            modelAndView.addObject("mess","Create Customer Successfully!");
//
            return modelAndView;
        }
    }
    @GetMapping("/list-customers")
    public ModelAndView listCustomer( @RequestParam("search")Optional<String>search ,@PageableDefault(5)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Customer> customers ;
        if (search.isPresent()){
            customers = customerService.findByNameContains(search.get(),pageable);
        }else {
            customers = customerService.findAll(pageable);
        }
        modelAndView.addObject("customer", customers);
        return modelAndView;
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView formEditCustomer(@PathVariable Long id){
        Customer customer =customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit","customer",customer);
        return modelAndView;
    }
    @PostMapping("edit-customer")
    public ModelAndView updateCustomer(@Validated @ModelAttribute("customer") Customer customer,BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("edit");
        if (bindingResult.hasFieldErrors()){
            return modelAndView;
        }else {
            customerService.save(customer);
            modelAndView.addObject("message","Update Succesfully");
            return modelAndView;
        }
    }
    @GetMapping("delete-customer/{id}")
    public ModelAndView formDeleteCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("delete","customer",customer);
        return modelAndView;
    }
    @PostMapping("delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.delete(customer.getId());
        return "redirect:list-customers";
    }
}
