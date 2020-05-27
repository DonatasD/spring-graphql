package com.donatasd.api.book.service;

import com.donatasd.api.book.domain.BookCreateDTO;
import com.donatasd.api.book.domain.BookDTO;
import com.donatasd.api.book.domain.BookUpdateDTO;
import com.donatasd.api.book.mapper.BookMapper;
import com.donatasd.api.book.repository.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookMutationResolver implements GraphQLMutationResolver {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  public BookMutationResolver(
      BookRepository bookRepository,
      BookMapper bookMapper
  ) {
    this.bookRepository = bookRepository;
    this.bookMapper = bookMapper;
  }

  public BookDTO createBook(BookCreateDTO createDTO) {
    log.debug("Creating book with: {}", createDTO);
    var entity = bookMapper.toBook(createDTO);
    var savedEntity = bookRepository.save(entity);
    log.debug("Created book: {}", savedEntity);
    return bookMapper.toBookDTO(savedEntity);
  }

  public BookDTO updateBook(BookUpdateDTO updateDTO) {
    log.debug("Updating book with: {}", updateDTO);
    if(!bookRepository.existsById(updateDTO.getId())) {
      log.error("Invalid book id");
      throw new RuntimeException();
    }
    var existingEntity = bookRepository.getOne(updateDTO.getId());
    var mergedEntity = bookMapper.toBook(updateDTO, existingEntity);
    var updatedEntity = bookRepository.save(mergedEntity);
    log.debug("Updated book: {}", updatedEntity);
    return bookMapper.toBookDTO(updatedEntity);
  }

  public Long deleteBook(Long id) {
    log.debug("Deleted book with id: {}", id);
    if(!bookRepository.existsById(id)) {
      log.error("Invalid book id");
      throw new RuntimeException();
    }
    bookRepository.deleteById(id);
    log.debug("Deleted book: {}", id);
    return id;
  }
}
