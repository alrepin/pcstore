package ga.repin.easybot.pcstore.repository;

import ga.repin.easybot.pcstore.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    
    
    Optional<ProductType> findFirstByName(String name);
    
    
}
