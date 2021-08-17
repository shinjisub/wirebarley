package com.demo.wirebarley.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ResponseDTO<D> {

    private int resultCode;

    private D resultData;

}

