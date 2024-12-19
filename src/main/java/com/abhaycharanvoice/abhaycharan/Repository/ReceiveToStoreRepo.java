package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiveToStoreRepo extends JpaRepository<ReceiveToStoreMst, Long> {

    List<ReceiveToStoreMst> findByInvoiceNoAndActiveFlag(String invoiceNo, Integer activeFlag);

    List<ReceiveToStoreMst> findByCreatedDate(LocalDate invoiceDate);

    List<ReceiveToStoreMst> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
