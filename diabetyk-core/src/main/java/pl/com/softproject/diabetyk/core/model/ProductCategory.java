package pl.com.softproject.diabetyk.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Class ProductCategory
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Entity
@Table(name = "category", schema = "public")
@SuppressWarnings("PersistenceUnitPresent")
public class ProductCategory extends BaseEntity {

    @Column(length = 256, name = "name")
    @Size(min = 3, max = 256)
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private ProductCategory parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public ProductCategory() {

    }

    public ProductCategory(Long id) {

        super(id);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public ProductCategory getParent() {

        return parent;
    }

    public void setParent(ProductCategory parent) {

        this.parent = parent;
    }

    public Set<Product> getProducts() {

        return products;
    }

    public void setProducts(Set<Product> products) {

        this.products.clear();
        this.products.addAll(products);
    }

    @Override
    public String toString() {

        return "ProductCategory{" +
               "name='" + name + '\'' +
               '}';
    }
}
