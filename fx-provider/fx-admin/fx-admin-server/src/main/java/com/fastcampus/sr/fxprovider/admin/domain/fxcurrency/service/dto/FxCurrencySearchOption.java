package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FxCurrencySearchOption {
    private Currency currency;
}
