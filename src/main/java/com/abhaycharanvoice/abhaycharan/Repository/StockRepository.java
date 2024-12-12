package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.SellingInfo;
import com.abhaycharanvoice.abhaycharan.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findTopByProductCodeAndActiveFlagOrderByUpdatedTimeDesc(String productCode, Integer activeFlag);
}
