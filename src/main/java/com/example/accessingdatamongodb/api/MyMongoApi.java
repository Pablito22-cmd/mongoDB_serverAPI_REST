package com.example.accessingdatamongodb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;


@RestController
public class MyMongoApi {

    @Autowired
    private CustomerService service;

    //CREATE
    @PostMapping(value = {""})
    public ResponseEntity<URI> creaUtente(@RequestBody CustomerDTO customerDTO) throws ApplicationException{
        try {
            String idCustomer = service.crea(customerDTO);
            System.out.println("Creato customer " + customerDTO.getFirstName() + " con id " + idCustomer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idCustomer}").buildAndExpand(idCustomer).toUri();
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //READ ALL
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() throws ApplicationException{
        try{
            System.out.println("Recupero elenco degli Utenti");
            List<CustomerDTO> customers = service.elencoCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //READ ONE
    @GetMapping(value = "/{name}")
    public ResponseEntity<CustomerDTO> findByFirstName(@PathVariable("name") String firstName) throws ApplicationException{
        try{
            System.out.println("Recupero customer " + firstName);
            CustomerDTO customerdto = service.trovaCustomerNome(firstName);
            return new ResponseEntity<>(customerdto,HttpStatus.OK);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //READ ONE'S LANGUAGES
    @GetMapping(value = "/{firstName}/lang")
    public ResponseEntity<List<String>> findLangByFirstName(@PathVariable("firstName") String firstName) throws ApplicationException{
        try{
            System.out.println("Recupero lingue customer " + firstName);
            CustomerDTO customerdto = service.trovaCustomerNome(firstName);
            return new ResponseEntity<>(customerdto.getLanguages(),HttpStatus.OK);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //UPDATE
    @PutMapping(value = {"/{firstName}"})
    public ResponseEntity<String> aggiornaCustomer(@PathVariable("firstName") String firstName, @RequestBody CustomerDTO customerDto) throws ApplicationException{
         try{
            service.aggiornaCustomer(firstName, customerDto);
            System.out.println("Aggiornato customer " + customerDto.getFirstName());
            return new ResponseEntity<>("Customer Aggiornato!",HttpStatus.NO_CONTENT);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //DELETE BY NAME
    @DeleteMapping(value = "/{firstName}")
    public ResponseEntity<String> eliminaCustomerByName(@PathVariable("firstName") String firstName) throws ApplicationException{
        try{
            service.cancellaCustomer(firstName);
            System.out.println("Customer " + firstName + " eliminato");
            return new ResponseEntity<>("Customer Eliminato",HttpStatus.NO_CONTENT);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }

    //DELETE BY ID
    @DeleteMapping(value = "/id/{idCustomer}")
    public ResponseEntity<String> eliminaCustomerById(@PathVariable("idCustomer") String idCustomer) throws ApplicationException{
        try{
            service.cancellaCustomerId(idCustomer);
            System.out.println("Customer " + idCustomer + " eliminato");
            return new ResponseEntity<>("Customer Eliminato",HttpStatus.NO_CONTENT);
        }catch (ServiceException e){
            throw new ApplicationException(e);
        }
    }
}