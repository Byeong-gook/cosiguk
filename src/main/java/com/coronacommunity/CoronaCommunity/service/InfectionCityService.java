package com.coronacommunity.CoronaCommunity.service;


import com.coronacommunity.CoronaCommunity.dto.InfectionCityDto;
import com.coronacommunity.CoronaCommunity.entity.InfectionCity;
import com.coronacommunity.CoronaCommunity.repository.InfectionCityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InfectionCityService {
    private final EntityManager em;

    @Autowired
    InfectionCityRepository infectionCityRepository;

    public void InfectionCityApiDataAdd(InfectionCity infectionCity, String OneDay, String gubun) {

        Query DateCompareDBDate = em.createQuery("SELECT COUNT(*) FROM infectioncity WHERE createDt = :createDtValue AND gubun = :gubunValue");
        DateCompareDBDate.setParameter("createDtValue", OneDay);
        DateCompareDBDate.setParameter("gubunValue", gubun);

        //해당 데이터가 있으면 데이터 삽입 x 데이터가 없으면 삽입
        Long totalCount = (Long) DateCompareDBDate.getSingleResult();

        if(totalCount == 1) {

        } else {
            infectionCityRepository.save(infectionCity);

        }
    }

    public List<InfectionCityDto> InfectionCityAPiDataCall() {
        String q = "SELECT i.createDt,i.gubun,i.deathcnt,i.defcnt FROM infectioncity i WHERE createDt BETWEEN DATE_ADD(NOW(), INTERVAL -2 WEEK ) AND NOW()";

        Query query = em.createNativeQuery(q, "InfectionCityDtoMapping");

        List<InfectionCityDto> infectionCityList = query.getResultList();

        return infectionCityList;
    }
}
