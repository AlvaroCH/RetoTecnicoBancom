package com.bcp.retobcp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRateWebResponse {

	private String id;
	private String targetCurrency;
	private String originalCurrency;
	private Double exchangeRate;
	private Double originalAmount;
	private Double amount;
}
