package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.DamageStockDetail;
import com.abhaycharanvoice.abhaycharan.Entity.DamageStockMst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DamageStockRepo extends JpaRepository<DamageStockMst, Long> {
    List<DamageStockMst> findByInvoiceNoAndActiveFlag(String invoiceNo, Integer activeFlag);
    List<DamageStockMst> findByCreatedDate(LocalDate invoiceDate);
    List<DamageStockMst> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
