package com.ecommerce.firstversion.entity.storage;

import com.ecommerce.firstversion.entity.user_storage.UserStorage;
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
}
