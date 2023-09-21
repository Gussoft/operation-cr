package com.gussoft.operation.integration.transfer.response;

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
public class OperationsResponse {

  private String id;
  private String credit;
  private String number;
  private BigDecimal amount;
  private TypeOperationResponse type;
  private Date createAt;
  private String detail;

}
