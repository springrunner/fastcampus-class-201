package com.fastcampus.sr.fxprovider.common.log;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class WebTraceLog extends TraceLog {
    private String remoteAddress;
    private String forwarded;
    private String httpMethod;
    private String requestUrl;
    private String queryString;
    private String requestBody;
    private int httpStatus;
    private String responseBody;
    private String error;
    private String responseCode;
    private String responseMessage;
    private String apiName;

    public String toRequestUrl() {
        if (Objects.isNull(queryString) || "".equals(queryString)) {
            return requestUrl;
        } else {
            return requestUrl + "?" + queryString;
        }
    }

    @Override
    public String toString() {
        return "WebTransactionLog [remoteAddress=" + remoteAddress + ", forwarded=" + forwarded + ", httpMethod="
                + httpMethod + ", requestUrl=" + requestUrl + ", queryString=" + queryString + ", requestBody="
                + requestBody + ", httpStatus=" + httpStatus + ", responseBody=" + responseBody + ", error=" + error
                + ", responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", apiName=" + apiName
                + "]";
    }
}
