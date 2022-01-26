package com.bcp.retobcp.repository;

import com.bcp.retobcp.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {

	Optional<ExchangeRate> findExchangeRateByOriginalCurrencyAndTargetCurrency(String targetCurrency, String originalCurrency);

}
