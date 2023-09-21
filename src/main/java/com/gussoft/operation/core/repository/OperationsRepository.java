package com.gussoft.operation.core.repository;

import com.gussoft.operation.core.models.Operations;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OperationsRepository extends ReactiveMongoRepository<Operations, String> {

  Flux<Operations> findByCredit(String id);

}
