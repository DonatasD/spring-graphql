package com.donatasd.api.customer.domain;

import com.donatasd.api.book.domain.BookDTO;
import lombok.Data;

@Data
public class CustomerDTO {
  private Long id;

  private String name;

  private Iterable<BookDTO> books;
}
