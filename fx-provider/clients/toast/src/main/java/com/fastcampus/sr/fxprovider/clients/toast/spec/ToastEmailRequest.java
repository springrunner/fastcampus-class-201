package com.fastcampus.sr.fxprovider.clients.toast.spec;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class ToastEmailRequest {
    public static final String DEFAULT_USER_ID = "fx-provider"; // TODO 변경
    public static final String EMAIL_NOREPLY = "noreply@fxprovider.com"; // TODO 변경
    public static final String TEMPLATE_TYPE_DEFAULT = "DEFAULT";
    public static final String TEMPLATE_TYPE_FREEMARKER = "FREEMARKER";
    public static final String FORMAT_REQUEST_DATE = "yyyy-MM-dd HH:mm:ss";

    @NotEmpty
    private String senderAddress;

    private String senderName;

    private String requestDate; //  발송일시(yyyy-MM-dd HH:mm:ss)

    private String title; // 제목

    private String body; // 내용

    private String templateId; // 발송 템플릿ID: 템플릿 사용시 제목 및 내용은 무시됨

    private String templateType; // 템플릿유형: DEFAULT(default), FREEMARKER

    private List<Integer> attachFileIdList; // 업로드 첨부파일 ID

    private Object templateParameter; // 템플릿 데이터는 원하는 형태로 가공하여 주입한다.

    private List<ToastEmailReceiver> receiverList;

    private String senderGroupingKey;

    private String userId;  // 발송구분자

    /**
     * @param senderAddress     발신자 메일
     * @param senderName        발신자명
     * @param requestDate       요청발송일시(yyyy-MM-dd HH:mm:ss, 기본: 즉시 메시지전송)
     * @param title             제목
     * @param body              내용
     * @param attachFileIdList  업로드 첨부파일ID
     * @param templateId        발송 템플릿ID: 템플릿 사용시 제목 및 내용은 무시됨
     * @param templateType      템플릿 유형: DEFAULT, FREEMARKER
     * @param senderGroupingKey 발신자그룹키
     * @param receiverList      수신자 목록
     */
    @Builder
    public ToastEmailRequest(@NotEmpty String senderAddress,
                             String senderName,
                             String requestDate,
                             String title,
                             String body,
                             List<Integer> attachFileIdList,
                             String templateId,
                             String templateType,
                             Object templateParameter,
                             String senderGroupingKey,
                             List<ToastEmailReceiver> receiverList) {
        Assert.hasText(title, "제목(title)은 필수입력값입니다.");
        Assert.hasText(templateId, "템플릿ID(templateId)는 필수입력값입니다.");

        this.senderAddress = hasText(senderAddress) ? senderAddress : EMAIL_NOREPLY;
        this.senderName = senderName;
        this.requestDate = requestDate;
        this.title = title;
        this.body = body;
        this.attachFileIdList = attachFileIdList;
        this.templateId = templateId;
        this.templateType = hasText(templateType) ? templateType : TEMPLATE_TYPE_DEFAULT;
        this.templateParameter = templateParameter;
        this.senderGroupingKey = senderGroupingKey;
        this.receiverList = receiverList;
        this.userId = DEFAULT_USER_ID;
    }

    /**
     * 메일 템플릿 파라미터 추가
     */
    public void addTemplateParameter(Object templateParameter) {
        Assert.notNull(templateParameter, "templateParameter 는 필수입력값입니다.");
        this.templateParameter = templateParameter;
    }

    @Getter
    @ToString
    public static class ToastEmailReceiver {
        private String receiveMailAddr;
        private String receiveName;
        private String receiveType;

        @Builder
        public ToastEmailReceiver(String receiveMailAddr, String receiveName, MailReceiverType receiverType) {
            Assert.hasText(receiveMailAddr, "receiveMailAddr(수신자 이메일)은 필수입력값입니다.");

            this.receiveMailAddr = receiveMailAddr;
            this.receiveName = receiveName;
            this.receiveType = getToastMailReceiverType(receiverType);
        }

        public String getToastMailReceiverType(MailReceiverType receiverType) {
            if (isNull(receiverType)) {
                return "MRT0";
            }

            switch (receiverType) {
                case RECEIVER:
                    return "MRT0";
                case CC:
                    return "MRT1";
                case BCC:
                    return "MRT2";
                default:
                    throw new IllegalArgumentException(String.format("Not support type: %s", receiverType));
            }
        }
    }
}
