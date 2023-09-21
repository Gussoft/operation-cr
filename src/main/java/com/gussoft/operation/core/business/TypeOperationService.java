package com.gussoft.operation.core.business;

import com.gussoft.operation.integration.transfer.request.TypeOperationRequest;
import com.gussoft.operation.integration.transfer.response.TypeOperationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeOperationService {

  Flux<TypeOperationResponse> findByAll();

  Mono<TypeOperationResponse> findById(String id);

  Mono<TypeOperationResponse> findByName(String name);

  Mono<TypeOperationResponse> save(Mono<TypeOperationRequest> request);

  Mono<Void> delete(String id);

}
