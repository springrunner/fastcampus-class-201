package com.fastcampus.sr.fxprovider.admin.domain.transfer.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CancelRequest {
    @NotEmpty
    @Max(value = 100)
    private String cancelReason;
}
