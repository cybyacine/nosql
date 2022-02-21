package tn.esprit.crud_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.crud_product.exception.ResouceNotFoundException;
import tn.esprit.crud_product.model.Product;
import tn.esprit.crud_product.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		productRepository.save(product);
		return product;

	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResouceNotFoundException("Product not found" + productId));
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/products")
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@PutMapping("products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Integer productId,
			@RequestBody Product productDetails) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResouceNotFoundException("Product not found for this id :: " + productId));
		product.setName(productDetails.getName());
		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);

	}

	@DeleteMapping("products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResouceNotFoundException("Product not found::: " + productId));
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}

}
