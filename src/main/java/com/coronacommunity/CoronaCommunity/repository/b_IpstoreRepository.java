package com.coronacommunity.CoronaCommunity.repository;

import com.coronacommunity.CoronaCommunity.entity.b_Ipstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface b_IpstoreRepository extends JpaRepository<b_Ipstore, Long> {
}
