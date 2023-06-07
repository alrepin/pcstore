package ga.repin.easybot.pcstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.repin.easybot.pcstore.model.Field;
import ga.repin.easybot.pcstore.model.Product;
import ga.repin.easybot.pcstore.model.ProductType;
import ga.repin.easybot.pcstore.repository.FieldRepository;
import ga.repin.easybot.pcstore.repository.ProductRepository;
import ga.repin.easybot.pcstore.repository.ProductTypeRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final FieldRepository fieldRepository;
    private final ObjectMapper objectMapper;
    
    
    public void create(LinkedHashMap<String, String> hashMap) {
        ProductType productType = productTypeRepository
                .findFirstByName(hashMap.get("product_type")).orElse(null);
        if (null == productType) throw new RuntimeException("Not correct productType");
        Product product = new Product();
        product.setProductType(productType);
        product.setSerialNumber(hashMap.get("serial_number"));
        if (productRepository.findFirstBySerialNumberEqualsAndProductTypeEquals(product.getSerialNumber(), product.getProductType()).isPresent())
            throw new RuntimeException("Double");
        product.setManufacturer(hashMap.get("manufacturer"));
        product.setPrice(BigDecimal.valueOf(Long.parseLong(hashMap.get("price"))));
        product.setLeftovers(Integer.valueOf(hashMap.get("leftovers")));
        productType.getFields().forEach(productTypeField -> {
            Field newField = new Field();
            newField.setName(productTypeField.getName());
            newField.setIsConstant(productTypeField.getIsConstant());
            newField.setDataType(productTypeField.getDataType());
            newField.setValue(hashMap.get(newField.getName()));
            Field savedField = fieldRepository.findDistinctFirstByNameEqualsAndDataTypeEqualsAndValueEquals(newField.getName(), newField.getDataType().intValue(), newField.getValue()).orElse(null);
            product.getFields().add(isNull(savedField) ? newField : savedField);
            productRepository.save(product);
        });
        
    }
    
    @Transactional
    public void update(Long id, LinkedHashMap<String, String> hashMap) {
        Product product = productRepository.findById(id).orElse(null);
        if (null == product) throw new RuntimeException("Nothing to update");
        ProductType productType = product.getProductType();
        product.setSerialNumber(!isNull(hashMap.get("serial_number")) ? hashMap.get("serial_number") : product.getSerialNumber());

        product.setManufacturer(!isNull(hashMap.get("manufacturer")) ? hashMap.get("manufacturer") : product.getManufacturer());
        product.setPrice(!isNull(hashMap.get("price")) ? BigDecimal.valueOf(Long.parseLong(hashMap.get("price"))) : product.getPrice());
        product.setLeftovers(!isNull(hashMap.get("leftovers")) ? Integer.valueOf(hashMap.get("leftovers")) : product.getLeftovers());
        product.getFields().forEach(productField -> {
            Optional<Field> updatedField = fieldRepository.findDistinctFirstByNameEqualsAndDataTypeEqualsAndValueEquals(productField.getName(), productField.getDataType(), hashMap.get(productField.getName()));
            updatedField.ifPresent(field -> {
                product.getFields().remove(productField);
                product.getFields().add(field);
            });
            //productField.setValue(!isNull(hashMap.get(productField.getName())) ? hashMap.get(productField.getName()) : productField.getValue());
            productRepository.save(product);
        });
        
    }
    
    public LinkedHashMap<String, String> get(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (null == product) throw new RuntimeException();
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("id", product.getId().toString());
        hashMap.put("serial_number", product.getSerialNumber());
        hashMap.put("manufacturer", product.getManufacturer());
        hashMap.put("product_type", product.getProductType().getName());
        hashMap.put("price", product.getPrice().toString());
        hashMap.put("leftovers", product.getLeftovers().toString());
        product.getFields().forEach(productField -> hashMap.put(productField.getName(), productField.getValue()));
        return hashMap;
    }
    
    public ArrayList<LinkedHashMap<String, String>> get(String productTypeString) {
        ProductType productType = productTypeRepository.findFirstByName(productTypeString).orElse(null);
        if (null == productType) throw new RuntimeException();
        ArrayList<LinkedHashMap<String, String>> result = new ArrayList<>();
        productRepository.findProductsByProductTypeEquals(productType)
                .forEach(product -> result.add(get(product.getId())));
        return result;
    }
    
    private Boolean isNull(Object o) {
        return Optional.ofNullable(o).isEmpty();
    }
}