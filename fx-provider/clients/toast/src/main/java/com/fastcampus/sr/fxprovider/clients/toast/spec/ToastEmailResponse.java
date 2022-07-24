package com.fastcampus.sr.fxprovider.clients.toast.spec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@NoArgsConstructor
public class ToastEmailResponse {
    private ToastEmailResponseHeader header;
    private ToastEmailResponseBody body;

    public boolean isSuccess() {
        return isNull(getHeader()) ? false : getHeader().getSuccessful();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ToastEmailResponseHeader {
        @JsonProperty("isSuccessful")
        private Boolean successful;
        private Integer resultCode;
        private String resultMessage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ToastEmailResponseBody {
        private ToastEmailResponseBodyData data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ToastEmailResponseBodyData {
        private String requestId;
        private List<ToastEmailSendResult> results;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ToastEmailSendResult {
        private String receiveMailAddr;
        private String receiveName;
        private String receiveType;
        private Integer resultCode;
        private String resultMessage;
    }
}
