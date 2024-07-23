package com.ecommerce.firstversion.entities.storage;

import com.ecommerce.firstversion.entities.user_storage.UserStorage;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "storage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Storage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productDescription;

    private String imageDirectory;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @CreationTimestamp
    private Instant creationTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "id.storageId")
    private List<UserStorage> userStorages;

    public Storage(String productName, String productDescription, String imageDirectory, Double price, Integer quantity, ProductType productType) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageDirectory = imageDirectory;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
    }
}
