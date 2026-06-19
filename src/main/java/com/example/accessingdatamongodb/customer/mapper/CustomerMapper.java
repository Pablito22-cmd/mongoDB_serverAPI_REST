package com.example.accessingdatamongodb;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO convertEntityToDto(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId().toHexString())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .languages(entity.getLanguages())
                .build();
    }

    public Customer convertDtoToEntity(CustomerDTO dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .languages(dto.getLanguages())
                .build();
    }
}