package com.abhaycharanvoice.abhaycharan.Repository;

import com.abhaycharanvoice.abhaycharan.Entity.ReceiveToStoreMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveToStoreRepo extends JpaRepository<ReceiveToStoreMst, Long> {

}
