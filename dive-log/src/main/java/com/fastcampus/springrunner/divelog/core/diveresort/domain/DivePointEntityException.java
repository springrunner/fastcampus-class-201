package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import com.fastcampus.springrunner.divelog.common.exception.SystemException;

/**
 * {@link DivePoint} 엔티티에서 발생 가능한 최상위 예외 클래스
 *
 * @author springrunner.kr@gmail.com
 */
@SuppressWarnings("serial")
public class DivePointEntityException extends SystemException {
    public DivePointEntityException(String foramt, Object... args) {
        super(String.format(foramt, args));
    }
}
