package com.donatasd.api.customer.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.donatasd.api.book.entity.Book;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;

  @OneToMany
  private List<Book> books;
}
