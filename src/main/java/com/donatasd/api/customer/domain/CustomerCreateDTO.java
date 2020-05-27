package com.donatasd.api.customer.domain;

import java.util.List;
import javax.validation.constraints.Max;
import lombok.Data;

@Data
public class CustomerCreateDTO {
  @Max(100)
  public String name;

  public Iterable<Long> books = List.of();
}
