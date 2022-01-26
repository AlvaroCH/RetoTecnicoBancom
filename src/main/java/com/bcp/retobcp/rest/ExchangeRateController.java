package com.bcp.retobcp.rest;

import com.bcp.retobcp.bean.ExchangeRateBean;
import com.bcp.retobcp.bean.ExchangeRateRequest;
import com.bcp.retobcp.service.ExchangeRateService;
import com.bcp.retobcp.response.BaseWebResponse;
import com.bcp.retobcp.response.ExchangeRateWebResponse;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/exchangeRate")
public class ExchangeRateController {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/getExchangeValue")
	public Single<ExchangeRateBean> getExchangeRateBean(@RequestParam(value = "originalCurrency") String originalCurrency,
														@RequestParam(value = "targetCurrency") String targetCurrency,
														@RequestParam(value = "amount") Double amount) {
		return exchangeRateService.getExchangeValue(originalCurrency, targetCurrency, amount)
				.subscribeOn(Schedulers.io());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<BaseWebResponse>> exchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest) {
		return exchangeRateService.exchangeValue(exchangeRateRequest)
				.subscribeOn(Schedulers.io()).map(exchangeRateBean -> ResponseEntity
						.ok(BaseWebResponse.successWithData(toExchangeRateWebResponse(exchangeRateBean))));
	}

	private ExchangeRateWebResponse toExchangeRateWebResponse(ExchangeRateBean exchangeRateBean) {
		ExchangeRateWebResponse exchangeRateWebResponse = new ExchangeRateWebResponse();
		BeanUtils.copyProperties(exchangeRateBean, exchangeRateWebResponse);
		return exchangeRateWebResponse;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<BaseWebResponse>> updateExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest) {
		return exchangeRateService.updateExchangeRate(exchangeRateRequest).subscribeOn(Schedulers.io())
				.toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
	}

}
