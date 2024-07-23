package com.ecommerce.firstversion.repositories;

import com.ecommerce.firstversion.entities.storage.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
