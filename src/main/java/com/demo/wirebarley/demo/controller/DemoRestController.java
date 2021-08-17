package com.demo.wirebarley.demo.controller;

import com.demo.wirebarley.exchange.dto.ExchangeCoutryDTO;
import com.demo.wirebarley.exchange.dto.ResponseDTO;
import com.demo.wirebarley.exchange.service.ExchangeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax")
public class DemoRestController {

    //### ExchangeInfoService
    private final ExchangeInfoService exchangeInfoService;


    /**
     * Ajax Api Call
     * @return
     */
    @GetMapping("/getApiCall")
    @ResponseBody
    public ResponseDTO<ExchangeCoutryDTO> getApiCall() {
        return exchangeInfoService.callApi();
    }


}
