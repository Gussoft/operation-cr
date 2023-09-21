package com.gussoft.operation.core.business.impl;

import com.gussoft.operation.core.business.CreditService;
import com.gussoft.operation.core.models.dto.Credit;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
  @Autowired
  @Qualifier("registerWebCredits")
  private WebClient.Builder client;

  @Override
  public Mono<Credit> findById(String id) {
    return client.build().get()
      .uri("/{id}", Collections.singletonMap("id", id))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(Credit.class));
  }

  @Override
  public Mono<Map<String, Object>> findByCustomerCredit(String id) {
    return null;
  }

  @Override
  public Mono<Credit> payCreditCard(String id, BigDecimal amount) {
    return client.build().put()
      .uri("/card/{id}/payment", Collections.singletonMap("id", id))
      .body(BodyInserters.fromValue(Collections.singletonMap("amount", amount)))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(Credit.class));
  }

  @Override
  public Mono<Credit> buyCreditCard(String id, BigDecimal amount) {
    return client.build().put()
      .uri("/card/{id}/consumer", Collections.singletonMap("id", id))
      .body(BodyInserters.fromValue(Collections.singletonMap("amount", amount)))
      .accept(MediaType.APPLICATION_JSON)
      .exchangeToMono(response -> response.bodyToMono(Credit.class));
  }

}
