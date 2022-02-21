package tn.esprit.crud_product.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.crud_product.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
