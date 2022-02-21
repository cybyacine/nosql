package tn.esprit.crud_product.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.crud_product.model.Product;


@Repository
public interface ProductRepository extends CassandraRepository<Product,Integer> {
}
