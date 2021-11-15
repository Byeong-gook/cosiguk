package com.coronacommunity.CoronaCommunity.repository;

import com.coronacommunity.CoronaCommunity.entity.c_Ipstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface c_IpstoreRepository extends JpaRepository<c_Ipstore, Long> {

}
