package com.donatasd.api.customer.service;

import com.donatasd.api.customer.domain.CustomerDTO;
import com.donatasd.api.customer.mapper.CustomerMapper;
import com.donatasd.api.customer.repository.CustomerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CustomerQueryResolver implements GraphQLQueryResolver {

  private final CustomerRepository customerRepository;

  private final CustomerMapper customerMapper;

  public CustomerQueryResolver(
      CustomerRepository customerRepository,
      CustomerMapper customerMapper
  ) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  public List<CustomerDTO> customers() {
    log.debug("Querying all customers");
    var customerEntities = customerRepository.findAll();
    var customers = customerEntities.stream()
        .map(customerMapper::toCustomerDTO)
        .collect(Collectors.toList());
    log.debug("Queried customers: {}", customers);
    return customers;
  }

  public CustomerDTO customer(Long id) {
    log.debug("Querying customer: {}", id);
    var book = customerRepository.findById(id);
    var result = book.map(customerMapper::toCustomerDTO).orElseThrow(RuntimeException::new);
    log.debug("Queried customer: {}", result);
    return result;
  }

}
