package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
    Optional<ProductInfo> findByProductCodeAndActiveFlag(String productCode, Integer activeFlag);
    List<ProductInfo> findAllByActiveFlag(Integer activeFlag);
}
