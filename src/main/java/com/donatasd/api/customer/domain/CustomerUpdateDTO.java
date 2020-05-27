package com.donatasd.api.customer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerUpdateDTO extends CustomerCreateDTO {

  private Long id;
}
