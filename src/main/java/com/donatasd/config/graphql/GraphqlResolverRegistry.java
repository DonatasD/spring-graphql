package com.donatasd.config.graphql;

import com.donatasd.api.book.service.BookMutationResolver;
import com.donatasd.api.book.service.BookQueryResolver;
import com.donatasd.api.customer.service.CustomerMutationResolver;
import com.donatasd.api.customer.service.CustomerQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GraphqlResolverRegistry {

  private final BookQueryResolver bookQueryResolver;
  private final BookMutationResolver bookMutationResolver;
  private final CustomerQueryResolver customerQueryResolver;
  private final CustomerMutationResolver customerMutationResolver;

  public GraphqlResolverRegistry(
      BookQueryResolver bookQueryResolver,
      BookMutationResolver bookMutationResolver,
      CustomerQueryResolver customerQueryResolver,
      CustomerMutationResolver customerMutationResolver
  ) {
    this.bookQueryResolver = bookQueryResolver;
    this.bookMutationResolver = bookMutationResolver;
    this.customerQueryResolver = customerQueryResolver;
    this.customerMutationResolver = customerMutationResolver;
  }

  public List<GraphQLResolver<Void>> getAll() {
    return List.of(
        bookQueryResolver,
        bookMutationResolver,
        customerQueryResolver,
        customerMutationResolver
    );
  }
}
