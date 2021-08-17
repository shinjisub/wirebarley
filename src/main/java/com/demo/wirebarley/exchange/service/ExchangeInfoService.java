package com.demo.wirebarley.exchange.service;

import com.demo.wirebarley.common.CommonCode;
import com.demo.wirebarley.exchange.dto.ExchangeCoutryDTO;
import com.demo.wirebarley.exchange.dto.ResponseDTO;

public interface ExchangeInfoService extends CommonCode {

    public String getApiFullRequestUrl();

    public ResponseDTO<ExchangeCoutryDTO> callApi();
}
