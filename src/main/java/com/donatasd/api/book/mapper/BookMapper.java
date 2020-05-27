package com.donatasd.api.book.mapper;

import com.donatasd.api.book.domain.BookCreateDTO;
import com.donatasd.api.book.domain.BookDTO;
import com.donatasd.api.book.domain.BookUpdateDTO;
import com.donatasd.api.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

  @Mapping(target = "id", ignore = true)
  Book toBook(BookCreateDTO dto);

  @Mapping(target = "name", source = "dto.name")
  @Mapping(target = "id", source = "dto.id")
  Book toBook(BookUpdateDTO dto, Book entity);

  BookDTO toBookDTO(Book book);
}
