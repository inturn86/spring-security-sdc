package com.sdc.study.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;

/**

 * 인증되지 않았을 경우(비로그인) AuthenticationEntryPoint 부분에서 AuthenticationException 발생시키면서
 * 비로그인 상태에서 인증실패 시, AuthenticationEntryPoint 로 핸들링되어 이곳에서 처리

 * @author cjswo
 *
 */
@Component
@RequiredArgsConstructor
public class SdcAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		handlerExceptionResolver.resolveException(request, response, null, authException);

//		response.sendRedirect( response.getContentType());

		request.getRequestDispatcher("/login.page").forward(request, response);
	}


}
