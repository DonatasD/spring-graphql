package com.donatasd.api.customer.service;

import com.donatasd.api.book.repository.BookRepository;
import com.donatasd.api.customer.domain.CustomerCreateDTO;
import com.donatasd.api.customer.domain.CustomerDTO;
import com.donatasd.api.customer.domain.CustomerUpdateDTO;
import com.donatasd.api.customer.mapper.CustomerMapper;
import com.donatasd.api.customer.repository.CustomerRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerMutationResolver implements GraphQLMutationResolver {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final BookRepository bookRepository;

  public CustomerMutationResolver(
      CustomerRepository customerRepository,
      CustomerMapper customerMapper,
      BookRepository bookRepository
  ) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
    this.bookRepository = bookRepository;
  }

  public CustomerDTO createCustomer(CustomerCreateDTO createDTO) {
    log.debug("Creating customer with: {}", createDTO);
    var entity = customerMapper.toCustomer(createDTO, bookRepository);
    var savedEntity = customerRepository.save(entity);
    log.debug("Created customer: {}", savedEntity);
    return customerMapper.toCustomerDTO(savedEntity);
  }

  public CustomerDTO updateCustomer(CustomerUpdateDTO updateDTO) {
    log.debug("Updating customer with: {}", updateDTO);
    if(!customerRepository.existsById(updateDTO.getId())) {
      log.error("Invalid customer id");
      throw new RuntimeException();
    }
    var existingEntity = customerRepository.getOne(updateDTO.getId());
    var mergedEntity = customerMapper.toCustomer(updateDTO, existingEntity, bookRepository);
    var updatedEntity = customerRepository.save(mergedEntity);
    log.debug("Updated customer: {}", updatedEntity);
    return customerMapper.toCustomerDTO(updatedEntity);
  }

  public Long deleteCustomer(Long id) {
    log.debug("Deleted customer with id: {}", id);
    if(!customerRepository.existsById(id)) {
      log.error("Invalid customer id");
      throw new RuntimeException();
    }
    customerRepository.deleteById(id);
    log.debug("Deleted customer: {}", id);
    return id;
  }
}
