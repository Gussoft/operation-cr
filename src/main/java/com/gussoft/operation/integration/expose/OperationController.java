package com.gussoft.operation.integration.expose;

import com.gussoft.operation.core.business.OperationService;
import com.gussoft.operation.integration.transfer.request.OperationsRequest;
import com.gussoft.operation.integration.transfer.response.OperationsResponse;
import java.net.URI;
import java.util.Date;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class OperationController {

  @Autowired
  private OperationService service;

  @GetMapping("/operations")
  public Mono<ResponseEntity<Flux<OperationsResponse>>> findAll() {
    return Mono.just(
      ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findByAll()));
  }

  @GetMapping("/operations/{id}")
  public Mono<ResponseEntity<OperationsResponse>> findById(@PathVariable String id) {
    return service.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/operations/accounts/{id}")
  public Flux<ResponseEntity<OperationsResponse>> findByIdAccount(@PathVariable String id) {
    return service.findByAccount(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping("/operations/credits/buypay")
  public Mono<ResponseEntity<OperationsResponse>> createOperationBuyPay(
    @Valid @RequestBody Mono<OperationsRequest> request) {
    return request.flatMap(operation -> {
      if (operation.getCreateAt() == null) {
        operation.setCreateAt(new Date());
      }
      return service.save(Mono.just(operation)).map(ope -> ResponseEntity
        .created(URI.create("/api/v1/operations/" + ope.getId())).body(ope));
    });
  }
}
