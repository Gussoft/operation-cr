package com.gussoft.operation.core.business.impl;

import com.gussoft.operation.core.business.CreditService;
import com.gussoft.operation.core.business.OperationService;
import com.gussoft.operation.core.repository.OperationsRepository;
import com.gussoft.operation.core.utils.Constrains;
import com.gussoft.operation.integration.mappers.OperationsMapper;
import com.gussoft.operation.integration.transfer.request.OperationsRequest;
import com.gussoft.operation.integration.transfer.response.OperationsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

  @Autowired
  private OperationsRepository repo;

  @Autowired
  private CreditService creditService;

  @Override
  public Flux<OperationsResponse> findByAll() {
    return repo.findAll().map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<OperationsResponse> findById(String id) {
    return repo.findById(id).map(OperationsMapper::toOperationsResponse)
      .switchIfEmpty(Mono.error(new RuntimeException("Id no encontrado " + id)));
  }

  @Override
  public Flux<OperationsResponse> findByAccount(String credit) {
    return repo.findByCredit(credit).map(OperationsMapper::toOperationsResponse);
  }

  @Override
  @Transactional
  public Mono<OperationsResponse> save(Mono<OperationsRequest> request) {
    return request.map(OperationsMapper::toOperationsRequest)
      .flatMap(operation -> {
        creditService.findById(operation.getCredit()).flatMap(credit -> {
          if(operation.getType().getName().equalsIgnoreCase(Constrains.COMPRAS)) {
            if (credit.getConsume().add(operation.getAmount()).compareTo(credit.getAmount()) <= 0) {
              creditService.buyCreditCard(operation.getCredit(), operation.getAmount()).subscribe();
              log.info("Compra se a Realizado con exito de ".concat(operation.getAmount().toString()));
              return repo.save(operation);
            }
          } else if(operation.getType().getName().equalsIgnoreCase(Constrains.PAGOS)) {
            creditService.payCreditCard(operation.getCredit(), operation.getAmount()).subscribe();
            log.info("Pago se Realizado con exito de ".concat(operation.getAmount().toString()));
            return repo.save(operation);
          } else {
            return Mono.error(new RuntimeException("Operacion no se pudo realizar!"));
          }
          return Mono.just(credit);
        }).subscribe();
        return Mono.just(operation);
      })
      .map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<OperationsResponse> update(Mono<OperationsRequest> request, String id) {
    return repo.findById(id)
      .flatMap(c -> request.map(OperationsMapper::toOperationsRequest)
        .doOnNext(e -> {
          e.setId(id);
          log.info(e.toString());
        }))
      .flatMap(repo::save)
      .map(OperationsMapper::toOperationsResponse);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }
}
