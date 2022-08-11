package com.sdc.study.security.handler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdc.study.core.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 인가가 되지 않은 사용자가 접근할 떄 .
 * @author cjswo
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SdcAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub

		log.error("Forbidden!!! message : " + accessDeniedException.getMessage());

        //response에 넣기
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());

//		CommonResponse c = CommonResponse.builder().code(String.valueOf(HttpStatus.FORBIDDEN.value())).message(HttpStatus.FORBIDDEN.getReasonPhrase()).build();
//
//        try (OutputStream os = response.getOutputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(os, c);
//            os.flush();
//        }

		request.getRequestDispatcher("/deny.page").forward(request, response);

	}

}
