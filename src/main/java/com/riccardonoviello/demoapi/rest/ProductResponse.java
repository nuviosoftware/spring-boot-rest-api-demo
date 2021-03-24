package com.riccardonoviello.demoapi.rest;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;

}
