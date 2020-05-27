package com.donatasd.api.book.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookUpdateDTO extends BookCreateDTO {

  private Long id;
}
