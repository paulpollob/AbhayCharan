package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreMst;
import com.abhaycharanvoice.abhaycharan.Entity.SellingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<SellingInfo, Long> {
}
