package com.fastcampus.sr.fxprovider.core.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FxCurrencyRepository extends JpaRepository<FxCurrency, Long> {
}
