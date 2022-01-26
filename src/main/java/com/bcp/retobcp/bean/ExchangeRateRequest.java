package com.bcp.retobcp.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequest {

	private String id;
	private String targetCurrency;
	private String originalCurrency;
	private Double exchangeRate;
	private Double amount;


}
