package com.ecommerce.firstversion.entities.user_storage;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UserStorageId implements Serializable {

    @Column(name = "users_fk")
    private Long userId;

    @Column(name = "storage_fk")
    private Long storageId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStorageId that = (UserStorageId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(storageId, that.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, storageId);
    }
}
