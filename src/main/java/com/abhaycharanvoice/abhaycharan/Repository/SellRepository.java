package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreMst;
import com.abhaycharanvoice.abhaycharan.Entity.SellingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<SellingInfo, Long> {
    List<SellingInfo> findBySellIdAndActiveFlag(String sellingId, int i);

    List<SellingInfo> findByCreateOnlyDate(LocalDate invoiceDate);

    List<SellingInfo> findByCreateOnlyDateBetween(LocalDate fromDate, LocalDate toDate);
}
