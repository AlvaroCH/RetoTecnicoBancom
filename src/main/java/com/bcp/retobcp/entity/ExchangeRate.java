package com.bcp.retobcp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "target_currency")
	private String targetCurrency;

	@Column(name = "original_currency")
	private String originalCurrency;

	@Column(name = "exchange_rate")
	private Double exchangeRate;
}
