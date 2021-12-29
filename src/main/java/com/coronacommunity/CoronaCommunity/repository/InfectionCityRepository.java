package com.coronacommunity.CoronaCommunity.repository;

import com.coronacommunity.CoronaCommunity.entity.InfectionCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfectionCityRepository extends JpaRepository<InfectionCity, Long> {

}
