package com.demo.wirebarley.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class ExchangeCoutryDTO {
    private double usdKrw;
    private double usdPhp;
    private double usdJpy;
    private long timeStamp;
    private long serverTimeStamp;
}
