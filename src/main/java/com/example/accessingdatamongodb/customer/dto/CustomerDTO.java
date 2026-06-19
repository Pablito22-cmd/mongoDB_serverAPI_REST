package com.example.accessingdatamongodb;


import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO{

    private String id;
    private String firstName;
    private String lastName;
    private List<String> languages = new ArrayList();
}