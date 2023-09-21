package com.gussoft.operation.core.business.impl;

import com.gussoft.operation.core.business.TypeOperationService;
import com.gussoft.operation.core.repository.TypeOperationsRepository;
import com.gussoft.operation.integration.mappers.TypeOperationMapper;
import com.gussoft.operation.integration.transfer.request.TypeOperationRequest;
import com.gussoft.operation.integration.transfer.response.TypeOperationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TypeOperationServiceImpl implements TypeOperationService {

  @Autowired
  private TypeOperationsRepository repo;

  @Override
  public Flux<TypeOperationResponse> findByAll() {
    return repo.findAll().map(TypeOperationMapper::toTypeOperationResponse);
  }

  @Override
  public Mono<TypeOperationResponse> findById(String id) {
    return repo.findById(id).map(TypeOperationMapper::toTypeOperationResponse);
  }

  @Override
  public Mono<TypeOperationResponse> findByName(String name) {
    return repo.findByName(name).map(TypeOperationMapper::toTypeOperationResponse);
  }

  @Override
  public Mono<TypeOperationResponse> save(Mono<TypeOperationRequest> request) {
    return request.map(TypeOperationMapper::toTypeOperationRequest)
      .flatMap(repo::save)
      .map(TypeOperationMapper::toTypeOperationResponse);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }
}
