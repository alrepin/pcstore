package ga.repin.easybot.pcstore.repository;

import ga.repin.easybot.pcstore.model.Product;
import ga.repin.easybot.pcstore.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findFirstBySerialNumberEqualsAndProductTypeEquals(String serialNumber,  ProductType productTypeName);
    
    ArrayList<Product> findProductsByProductTypeEquals(ProductType productType);
}
