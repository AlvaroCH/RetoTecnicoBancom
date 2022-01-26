package com.bcp.retobcp.service;

import com.bcp.retobcp.bean.ExchangeRateBean;
import com.bcp.retobcp.bean.ExchangeRateRequest;
import com.bcp.retobcp.entity.ExchangeRate;
import com.bcp.retobcp.repository.ExchangeRateRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Override
	public Single<ExchangeRateBean> getExchangeValue(String originalCurrency, String targetCurrency, Double amount) {
		ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();
		exchangeRateRequest.setOriginalCurrency(originalCurrency);
		exchangeRateRequest.setTargetCurrency(targetCurrency);
		exchangeRateRequest.setAmount(amount);
		return findExchangeRateByCurrencyType(exchangeRateRequest);
	}

	@Override
	public Single<ExchangeRateBean> exchangeValue(ExchangeRateRequest exchangeRateRequest) {
		return findExchangeRateByCurrencyType(exchangeRateRequest);
	}

	private Single<ExchangeRateBean> findExchangeRateByCurrencyType(ExchangeRateRequest exchangeRateRequest) {
		return Single.create(singleSubscriber -> {
			Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository
					.findExchangeRateByOriginalCurrencyAndTargetCurrency(exchangeRateRequest.getOriginalCurrency(), exchangeRateRequest.getTargetCurrency());
			if (!optionalExchangeRate.isPresent())
				singleSubscriber.onError(new EntityNotFoundException("Not data found about exchange rate"));
			else {
				ExchangeRateBean exchangeRateBean = toExchangeRateResponse(optionalExchangeRate.get());
				exchangeRateBean.setAmount(optionalExchangeRate.get().getExchangeRate() * exchangeRateRequest.getAmount());
				exchangeRateBean.setOriginalAmount(exchangeRateRequest.getAmount());
				singleSubscriber.onSuccess(exchangeRateBean);
			}
		});
	}

	private ExchangeRateBean toExchangeRateResponse(ExchangeRate exchangeRate) {
		ExchangeRateBean exchangeRateBean = new ExchangeRateBean();
		BeanUtils.copyProperties(exchangeRate, exchangeRateBean);
		return exchangeRateBean;
	}

	@Override
	public Completable updateExchangeRate(ExchangeRateRequest exchangeRateRequest) {
		return updateExchangeRateToRepository(exchangeRateRequest);
	}

	private Completable updateExchangeRateToRepository(ExchangeRateRequest exchangeRateRequest) {
		return Completable.create(completableSubscriber -> {
			//Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository.findById(exchangeRateRequest.getId());
			Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository
					.findExchangeRateByOriginalCurrencyAndTargetCurrency(exchangeRateRequest.getOriginalCurrency(), exchangeRateRequest.getTargetCurrency());
			if (!optionalExchangeRate.isPresent())
				completableSubscriber.onError(new EntityNotFoundException("No data found"));
			else {
				ExchangeRate exchangeRate = optionalExchangeRate.get();
				exchangeRate.setOriginalCurrency(exchangeRateRequest.getOriginalCurrency());
				exchangeRate.setTargetCurrency(exchangeRateRequest.getTargetCurrency());
				exchangeRate.setExchangeRate(exchangeRateRequest.getExchangeRate());
				exchangeRateRepository.save(exchangeRate);
				completableSubscriber.onComplete();
			}
		});
	}

}
