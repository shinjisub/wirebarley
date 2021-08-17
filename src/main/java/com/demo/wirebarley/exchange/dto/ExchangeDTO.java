package com.demo.wirebarley.exchange.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ExchangeDTO {

    private long timestamp;

    private Boolean success;

    private HashMap quotes;

    private Map<String, Object> error;
}
