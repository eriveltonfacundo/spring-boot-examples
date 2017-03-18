package com.example.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Product;
import com.example.spring.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Product>> getProductAll(@RequestParam(value = "search") String search) {
		Iterable<Product> products = productRepository.findAll();
		return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", params = { "page", "size" }, method = RequestMethod.GET)
	public Page<Product> getProductAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Product> products = productRepository.findAll(new PageRequest(page, size));
		return products;
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Product> getCustomer(@PathVariable("id") Long id) {
		Product product = productRepository.findOne(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
