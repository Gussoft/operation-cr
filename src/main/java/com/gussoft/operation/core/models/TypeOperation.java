package com.gussoft.operation.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "type_operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOperation {

  private String id;

  private String name;

}
