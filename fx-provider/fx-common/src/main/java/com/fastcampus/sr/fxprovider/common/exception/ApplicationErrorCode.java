package com.fastcampus.sr.fxprovider.common.exception;

public enum ApplicationErrorCode {
    SUCCESS("0000", "OK"),
    FAILURE("9999", "시스템 오류가 발생했습니다. 담당자에게 문의바랍니다."),
    NOT_SUPPORT_CURRENCY("9001", "지원하지 않는 통화입니다."),
    FEIGN_CLIENT_NOT_FOUND_TARGET_SERVER("F404", "대상서버를 찾을 수 없습니다."),
    FEIGN_CLIENT_API_EXCEPTION("F400", "Feign 클라이언트에서 오류가 발생했습니다."),
    NOT_FOUND_FX_CURRENCY("S411", "요청한 환율정보를 찾을 수 없습니다."),
    FAILURE_API_RESPONSE_JSON_PROCESSING("S412", "JSON 데이터 처리중 오류가 발생했습니다.");
    ;
    private String code;
    private String message;

    ApplicationErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
