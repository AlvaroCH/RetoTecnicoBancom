package com.bcp.retobcp.service;

import com.bcp.retobcp.bean.ExchangeRateBean;
import com.bcp.retobcp.bean.ExchangeRateRequest;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface ExchangeRateService {

	Single<ExchangeRateBean> getExchangeValue(String originalCurrency, String targetCurrency, Double amount);

	Single<ExchangeRateBean> exchangeValue(ExchangeRateRequest exchangeRateRequest);

	Completable updateExchangeRate(ExchangeRateRequest exchangeRateRequest);

}
