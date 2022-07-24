package com.fastcampus.sr.fxprovider.clients.toast.spec;

/**
 * @see <a href="https://docs.toast.com/ko/Notification/Email/ko/api-guide/">Notification > Email > API v2.0 가이드</a>
 */
public interface ToastEmailClient {
    ToastEmailResponse send(ToastEmailRequest request);
}
