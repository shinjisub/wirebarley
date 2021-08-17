package com.demo.wirebarley.exchange.service;

import com.demo.wirebarley.exchange.dto.ExchangeCoutryDTO;
import com.demo.wirebarley.exchange.dto.ExchangeDTO;
import com.demo.wirebarley.exchange.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExchangeInfoServiceImpl implements ExchangeInfoService {

    private final Logger logger = LoggerFactory.getLogger(ExchangeInfoServiceImpl.class);

    @Value("${currencylayer.accessKey}")
    private String accessKey;

    @Value("${currencylayer.baseUrl}")
    private String baseUrl;

    @Value("${currencylayer.endPoint}")
    private String endPoint;

    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;


    /**
     * API Request Full URL (baseUrl + ENDPOINT +  accessKey )
     *
     * @return
     */
    @Override
    public String getApiFullRequestUrl() {
        return Objects.toString(
                new StringBuffer()
                        .append(baseUrl)
                        .append(endPoint)
                        .append("?access_key=")
                        .append(accessKey), ""
        );
    }


    /**
     * Ajax Call Api
     * @return
     */
    @Override
    public ResponseDTO<ExchangeCoutryDTO> callApi() {
        int resultCode               = FAIL_CODE;
        HttpHeaders headers          = new HttpHeaders();
        HttpEntity<?> entity         = new HttpEntity<>(headers);
        ExchangeCoutryDTO resultData = null;

        //### Api Call
        ResponseEntity<ExchangeDTO> exchange = restTemplate.exchange(this.getApiFullRequestUrl(), HttpMethod.GET, entity, ExchangeDTO.class);
        resultCode = exchange.getBody().getSuccess() ? SUCCESS_CODE : FAIL_CODE;
        logger.info("### ExchangeInfoServiceImpl.callApi Api rtnCode {}", exchange.getStatusCodeValue());
        logger.info("###### ExchangeInfoServiceImpl.callApi getSuccess {}", exchange.getBody().getSuccess());

        if (resultCode == SUCCESS_CODE && exchange.getBody().getError() == null) {
            resultData = modelMapper.map(exchange.getBody().getQuotes(), ExchangeCoutryDTO.class);
            resultData.setTimeStamp(exchange.getBody().getTimestamp());
        }

        return new ResponseDTO<ExchangeCoutryDTO>(resultCode, resultData);
    }
}
