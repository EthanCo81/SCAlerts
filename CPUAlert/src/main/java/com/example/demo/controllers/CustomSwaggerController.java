package com.example.demo.controllers;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

/**
 *  
 * This controller overrides the Swagger2Controller behavior so the home of the docs
 * changes to our specified parameter -> SWAGGER_HOME + /v2/api-docs.
 * 
 * @author Neil Ferman
 *
 */

@Controller
@ApiIgnore
@RequestMapping(value = CustomSwaggerController.SWAGGER_HOME)
public class CustomSwaggerController extends Swagger2Controller {
	public static final String SWAGGER_HOME = "/users/documentation";
    
    public CustomSwaggerController(Environment environment, DocumentationCache documentationCache,
            ServiceModelToSwagger2Mapper mapper, JsonSerializer jsonSerializer) {
        super(environment, documentationCache, mapper, jsonSerializer);
    }
}
