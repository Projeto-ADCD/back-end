package com.api.tasteful.utils;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    
	 @Override
	    public void contribute(MetadataBuilder metadataBuilder) {
	        metadataBuilder.applySqlFunction("query_recipe",
	                new SQLFunctionTemplate(BooleanType.INSTANCE,
	                        "to_tsvector('Portuguese', recipe_json) @@ to_tsquery('Portuguese', ?1)"));
	        
	        metadataBuilder.applySqlFunction("query_name",
	                new SQLFunctionTemplate(BooleanType.INSTANCE,
	                        "to_tsvector('Portuguese', recipe_json -> 'nome_receita') @@ to_tsquery('Portuguese', ?1)"));
	    }
	}

