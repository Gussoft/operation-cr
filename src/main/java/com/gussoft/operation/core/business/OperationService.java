package com.gussoft.operation.core.business;

import com.gussoft.operation.integration.transfer.request.OperationsRequest;
import com.gussoft.operation.integration.transfer.response.OperationsResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {

  Flux<OperationsResponse> findByAll();

  Mono<OperationsResponse> findById(String id);

  Flux<OperationsResponse> findByAccount(String account);

  Mono<OperationsResponse> save(Mono<OperationsRequest> request);

  Mono<OperationsResponse> update(Mono<OperationsRequest> request, String id);

  Mono<Void> delete(String id);

}
