package com.example.accessingdatamongodb;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	@Nullable
	Optional<Customer> findByFirstName(String firstName);
	List<Customer> findByLastName(String lastName);
}
