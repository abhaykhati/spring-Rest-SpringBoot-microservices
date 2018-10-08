package com.master.microservices.springBoot.springCloud.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public MappingJacksonValue getSomeBean() {

		SomeBean somebean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		mapping.setFilters(filterProvider);

		return mapping;
	}

	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue getListOfSomeBean() {

		List<SomeBean> asList = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
				new SomeBean("Val44", "Val22", "Val33"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(asList);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}

}
