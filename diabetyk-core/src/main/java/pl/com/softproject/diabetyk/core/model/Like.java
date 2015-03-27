package pl.com.softproject.diabetyk.core.model;

import pl.com.softproject.diabetyk.core.enums.LikeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Class Like
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Entity
@Table(name = "like", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {
        "userdata_id", "product_id", "type"}))
@SuppressWarnings("PersistenceUnitPresent")
public class Like extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userdata_id")
    private UserData author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private LikeType type;

    public Like() {

    }

    public Like(Long id) {

        super(id);
    }

    public UserData getAuthor() {

        return author;
    }

    public void setAuthor(UserData author) {

        this.author = author;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
    }

    public LikeType getType() {

        return type;
    }

    public void setType(LikeType type) {

        this.type = type;
    }
}
