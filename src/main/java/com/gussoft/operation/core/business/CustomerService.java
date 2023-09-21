package com.gussoft.operation.core.business;

import com.gussoft.operation.core.models.dto.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {

  Mono<Customer> findById(String id);

}
