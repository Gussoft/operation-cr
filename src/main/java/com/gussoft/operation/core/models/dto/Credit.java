package com.gussoft.operation.core.models.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {

  private String id;
  private String customer;
  private String numberAccount;
  private TypeCredit type;
  private Date createAt;
  private BigDecimal amount;
  private BigDecimal benefit;
  private Integer day;
  private BigDecimal consume;

}
