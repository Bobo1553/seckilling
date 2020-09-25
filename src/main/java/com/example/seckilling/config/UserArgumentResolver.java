package com.example.seckilling.config;

import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.service.SeckillingUserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xiao Yijia
 * @create 2020/9/25 22:22
 */

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final SeckillingUserService seckillingUserService;

    public UserArgumentResolver(SeckillingUserService seckillingUserService) {
        this.seckillingUserService = seckillingUserService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == SeckillingUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        if (request == null) {
            return null;
        }
        String paramToken = request.getParameter(SeckillingUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, SeckillingUserService.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return seckillingUserService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
