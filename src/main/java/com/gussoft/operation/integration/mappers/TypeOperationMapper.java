package com.gussoft.operation.integration.mappers;

import com.gussoft.operation.core.models.TypeOperation;
import com.gussoft.operation.integration.transfer.request.TypeOperationRequest;
import com.gussoft.operation.integration.transfer.response.TypeOperationResponse;
import org.springframework.beans.BeanUtils;

public class TypeOperationMapper {

  public TypeOperationMapper() {
  }

  public static TypeOperation toTypeOperationRequest(TypeOperationRequest request) {
    TypeOperation entity = new TypeOperation();
    BeanUtils.copyProperties(request, entity);
    return entity;
  }

  public static TypeOperationResponse toTypeOperationResponse(TypeOperation request) {
    TypeOperationResponse response = new TypeOperationResponse();
    BeanUtils.copyProperties(request, response);
    return response;
  }

}
