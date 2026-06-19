package com.example.accessingdatamongodb;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class CustomerService{

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerMapper mapper;

    public List<CustomerDTO> elencoCustomers() throws ServiceException{
        try {
            List<Customer> customers = repository.findAll();
            List<CustomerDTO> customersDTO = new ArrayList<CustomerDTO>();
            for (Customer c : customers) {
                customersDTO.add(mapper.convertEntityToDto(c));
            }
            return customersDTO;
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public CustomerDTO trovaCustomerNome(String firstName) throws ServiceException{
        try {
            Optional<Customer> customer = repository.findByFirstName(firstName);
            if (customer.isPresent()) {
                return mapper.convertEntityToDto(customer.get());
            }
            throw new ServiceException("Nessun Customer Corrispondente!");
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public String crea(CustomerDTO customerDto) throws ServiceException{
        try{
            Customer customer = mapper.convertDtoToEntity(customerDto);
            var res = repository.save(customer);
            return res.getId().toHexString();
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void aggiornaCustomer(String firstName, CustomerDTO oldCustomerDto) throws ServiceException{
        try{
            Optional<Customer> oldCustomer = repository.findByFirstName(firstName);
            if(oldCustomer.isPresent()) {
                Customer newCustomer = mapper.convertDtoToEntity(oldCustomerDto);
                newCustomer.setId(oldCustomer.get().getId());
                repository.save(newCustomer);
            }else{
                throw new ServiceException("Nessun Customer Corrispondente!");
            }
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void cancellaCustomer(String firstName)throws ServiceException{
        try{
            Optional<Customer> customer = repository.findByFirstName(firstName);
            if(customer.isPresent()){
                repository.delete(customer.get());
            }else{
                throw new ServiceException("Nessun Customer Corrispondente!");
            }
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void cancellaCustomerId(String idCustomer) throws ServiceException{
        try{
            Optional<Customer> customer = repository.findById(idCustomer);
            if(customer.isPresent()){
                repository.delete(customer.get());
            }else{
                throw new ServiceException("Nessun Customer Corrispondente!");
            }
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }
}