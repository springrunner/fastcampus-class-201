package com.fastcampus.sr.fxprovider.api.config.authentication;

import com.fastcampus.sr.fxprovider.api.common.ApiResponseGenerator;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * RequestHeaders.Authorization 값 확인
 */
@Slf4j
public class HeaderAuthorizationCheckFilter extends OncePerRequestFilter {
    private final List<String> requireAuthenticationRequirePaths;
    private final Map<String, String> authenticationKeys;

    public HeaderAuthorizationCheckFilter(List<String> requireAuthenticationRequirePaths, Map<String, String> authenticationKeys) {
        this.requireAuthenticationRequirePaths = requireAuthenticationRequirePaths;
        this.authenticationKeys = authenticationKeys;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String authKey = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(isAuthenticationRequirePath(requestUri)) {
            if(!authenticationKeys.containsValue(authKey)) {
                log.error("[fx-api][인증키 확인] 실패(uri: {}, authenticationKey: {})", requestUri, authKey);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.getWriter().write(ObjectMapperUtils.toPrettyJson(ApiResponseGenerator.of("C400", "인증키가 잘못되었습니다. 확인바랍니다.")));
                return;
            } else {
                for(Map.Entry<String, String> entry: authenticationKeys.entrySet()) {
                    if(entry.getValue().equals(authKey)) {
                        log.debug("[fx-api][인증키 확인] 성공({})", entry.getKey());
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isAuthenticationRequirePath(String requestUri) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return requireAuthenticationRequirePaths.stream()
                .anyMatch(targetUriPattern -> pathMatcher.match(targetUriPattern, requestUri));
    }


}
