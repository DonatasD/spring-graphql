package com.donatasd.api.book.service;

import com.donatasd.api.book.domain.BookDTO;
import com.donatasd.api.book.mapper.BookMapper;
import com.donatasd.api.book.repository.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookQueryResolver implements GraphQLQueryResolver {

  private final BookRepository bookRepository;

  private final BookMapper bookMapper;

  public BookQueryResolver(
      BookRepository bookRepository,
      BookMapper bookMapper
  ) {
    this.bookRepository = bookRepository;
    this.bookMapper = bookMapper;
  }

  public List<BookDTO> books() {
    log.debug("Querying all books");
    var books = bookRepository.findAll();
    var result = books.stream()
        .map(bookMapper::toBookDTO)
        .collect(Collectors.toList());
    log.debug("Queried books: {}", result);
    return result;
  }

  public BookDTO book(Long id) {
    log.debug("Querying book: {}", id);
    var book = bookRepository.findById(id);
    var result = book.map(bookMapper::toBookDTO).orElseThrow(RuntimeException::new);
    log.debug("Queried book: {}", result);
    return result;
  }
}
