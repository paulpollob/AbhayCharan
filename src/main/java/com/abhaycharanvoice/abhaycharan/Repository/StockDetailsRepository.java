package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.StockDetails;
import com.abhaycharanvoice.abhaycharan.Enum.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {
    List<StockDetails> findByInvoiceNoAndInvoiceTypeAndActiveFlag(String invoiceNo, InvoiceType invoiceType, Integer activeFlag);
    List<StockDetails> findByInvoiceNoAndActiveFlag(String invoiceNo, Integer activeFlag);
    List<StockDetails> findByCreatedDate(LocalDate createdDate);
    List<StockDetails> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
