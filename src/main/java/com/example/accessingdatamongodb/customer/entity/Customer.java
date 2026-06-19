package com.example.accessingdatamongodb;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Customer {

	@Id
	public @Nullable ObjectId id;
	public String firstName;
	public String lastName;
	public List<String> languages;
}

