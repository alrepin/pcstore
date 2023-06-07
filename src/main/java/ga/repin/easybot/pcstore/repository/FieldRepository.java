package ga.repin.easybot.pcstore.repository;

import ga.repin.easybot.pcstore.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    Optional<Field> findDistinctFirstByNameEqualsAndDataTypeEqualsAndValueEquals(String name, Integer dataType, String value);
}
