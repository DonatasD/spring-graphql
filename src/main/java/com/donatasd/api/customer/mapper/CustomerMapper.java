package com.donatasd.api.customer.mapper;

import com.donatasd.api.book.entity.Book;
import com.donatasd.api.book.mapper.BookMapper;
import com.donatasd.api.book.repository.BookRepository;
import com.donatasd.api.customer.domain.CustomerCreateDTO;
import com.donatasd.api.customer.domain.CustomerDTO;
import com.donatasd.api.customer.domain.CustomerUpdateDTO;
import com.donatasd.api.customer.entity.Customer;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface CustomerMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "books", source = "dto.books", qualifiedByName = "resolveBooks")
  Customer toCustomer(CustomerCreateDTO dto, @Context BookRepository bookRepository);


  @Mapping(target = "books", source = "dto.books", qualifiedByName = "resolveBooks")
  @Mapping(target = "name", source = "dto.name")
  @Mapping(target = "id", source = "dto.id")
  Customer toCustomer(
      CustomerUpdateDTO dto,
      Customer entity,
      @Context BookRepository bookRepository
  );

  CustomerDTO toCustomerDTO(Customer customer);

  @Named("resolveBooks")
  default List<Book> resolveBooks(Iterable<Long> ids, @Context BookRepository bookRepository) {
    return bookRepository.findAllById(ids);
  }
}
