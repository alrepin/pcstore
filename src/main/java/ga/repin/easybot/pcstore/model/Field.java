package ga.repin.easybot.pcstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pcstore_field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String value;
    
    private Boolean isConstant;
    
    private Integer dataType;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field field)) return false;
    
        if (getId() != null ? !getId().equals(field.getId()) : field.getId() != null) return false;
        if (getName() != null ? !getName().equals(field.getName()) : field.getName() != null) return false;
        if (getValue() != null ? !getValue().equals(field.getValue()) : field.getValue() != null) return false;
        if (getIsConstant() != null ? !getIsConstant().equals(field.getIsConstant()) : field.getIsConstant() != null) return false;
        return getDataType() != null ? getDataType().equals(field.getDataType()) : field.getDataType() == null;
    }
    
    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getIsConstant() != null ? getIsConstant().hashCode() : 0);
        result = 31 * result + (getDataType() != null ? getDataType().hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", isConstant=" + isConstant +
                ", dataType=" + dataType +
                '}';
    }
    
    @Getter
    @RequiredArgsConstructor
    public enum DataType {
        NUMERIC(100),
        STRING(200);
        private final Integer dataType;
    }
    
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "pcstore_product_type_fields",
            joinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_type_id", referencedColumnName = "id"))
//    @ManyToMany(mappedBy = "fields")
    private Set<ProductType> productTypes = new HashSet<>();
    
    
    public void addProductType(ProductType productType) {
        productTypes.add(productType);
        productType.getFields().add(this);
    }
    
   /* public void removeProductType(ProductType productType) {
        productTypes.remove(productType);
        productType.getFields().remove(this);
    }*/
    
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "pcstore_product_fields",
            joinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products = new HashSet<>();
    
}
