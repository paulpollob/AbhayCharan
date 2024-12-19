package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.DamageStockDetail;
import com.abhaycharanvoice.abhaycharan.Entity.DamageStockMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DamagedStockDetailRepo extends JpaRepository<DamageStockDetail, Long> {

    List<DamageStockDetail> findByInvoiceNoAndActiveFlag(String invoiceNo, Integer activeFlag);
    List<DamageStockDetail> findByCreatedDate(LocalDate invoiceDate);
    List<DamageStockDetail> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
