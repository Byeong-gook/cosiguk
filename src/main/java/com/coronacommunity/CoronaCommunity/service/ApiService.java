package com.coronacommunity.CoronaCommunity.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ApiService {

    @Value("${external.api.statusApiServiceKey}")
    private String statusApiServiceKey;

}