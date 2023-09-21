package com.gussoft.operation.core.business;

import com.gussoft.operation.core.models.dto.Credit;
import java.math.BigDecimal;
import java.util.Map;
import reactor.core.publisher.Mono;

public interface CreditService {

  Mono<Credit> findById(String id);

  Mono<Map<String,Object>> findByCustomerCredit(String id);

  Mono<Credit> payCreditCard(String id, BigDecimal amount);

  Mono<Credit> buyCreditCard(String id, BigDecimal amount);

}
