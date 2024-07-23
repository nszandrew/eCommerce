package com.ecommerce.firstversion.entities.user_storage;

import com.ecommerce.firstversion.entities.storage.Storage;
import com.ecommerce.firstversion.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users_storage")
@Getter
@Setter
public class UserStorage {

    @EmbeddedId
    private UserStorageId id;

    @Column(name = "product_code")
    private String productCode;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "users_fk", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("storageId")
    @JoinColumn(name = "storage_fk", insertable = false, updatable = false)
    private Storage storage;

}
