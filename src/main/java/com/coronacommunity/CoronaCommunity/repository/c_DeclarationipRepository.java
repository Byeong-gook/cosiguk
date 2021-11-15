package com.coronacommunity.CoronaCommunity.repository;

import com.coronacommunity.CoronaCommunity.entity.c_Declarationip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface c_DeclarationipRepository extends JpaRepository<c_Declarationip, Long> {
}
