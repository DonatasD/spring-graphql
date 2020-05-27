package com.donatasd.config.graphql;

import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlSchemaConfig {

  private final GraphqlResolverRegistry graphqlResolverRegistry;

  public GraphqlSchemaConfig(
      GraphqlResolverRegistry graphqlResolverRegistry
  ) {
    this.graphqlResolverRegistry = graphqlResolverRegistry;
  }

  @Bean
  public GraphQLSchema schema() {
    return SchemaParser
        .newParser()
        .files("graphql/schema.graphqls", "graphql/customer.graphqls", "graphql/book.graphqls")
        .resolvers(graphqlResolverRegistry.getAll())
        .build()
        .makeExecutableSchema();
  }
}
