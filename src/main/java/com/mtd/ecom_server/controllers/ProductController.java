package com.mtd.ecom_server.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.ecom_server.models.Product;
import com.mtd.ecom_server.repos.ProductRepo;
@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger Log=LoggerFactory.getLogger(ProductController.class);
	@Autowired ProductRepo productRepo;
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		Log.info("Fetching Products");
		return productRepo.findAll();
	}
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product newproduct) {
		return productRepo.save(newproduct);
	}		
	@DeleteMapping("/product/{id}")
	
	
	public String deleteProduct (@PathVariable String id) {
		Optional<Product> findproduct =productRepo.findById(id);
		if(findproduct.isEmpty()) {
			Log.error("failed to delete product"+id);
			return "Product Deleted";
		}
		productRepo.deleteById(id);
		Log.error("deleting the product by id"+id);
		return "deleted product";
		}
	
	
	@PutMapping("/product/edit/{id}")
	public Product editProduct(@PathVariable String id, @RequestBody Product newProduct) {
	    Product findproduct = productRepo.findById(id).get();

	    findproduct.setName(newProduct.getName());
	    findproduct.setDescription(newProduct.getDescription());
	    findproduct.setCategory(newProduct.getCategory());
	    findproduct.setTags(newProduct.getTags());
	    findproduct.setPrice(newProduct.getPrice());
	    findproduct.setStock(newProduct.getStock());
		Log.info("editing Products");

	    return productRepo.save(findproduct);
	}

	
}