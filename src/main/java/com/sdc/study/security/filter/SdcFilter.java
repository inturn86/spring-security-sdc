package com.sdc.study.security.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * filter는 기본적으로 security 에 의해 존재하지만 추가적인 인증을 필요로 하는 부분에 있어 filter를 추가하여 구현.
 * @author cjswo
 *
 */
//@Component
@RequiredArgsConstructor
public class SdcFilter extends GenericFilter {

	private final ApplicationContext context;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Map<String,Object> beans = context.getBeansWithAnnotation(RequestMapping.class);

		System.out.println("1");
		// TODO Auto-generated method stub

//		UserDetails authentication = customUserDetailsService.loadUserByUsername("sdc");
//        UsernamePasswordAuthenticationToken auth =
//                //여기있는 super.setAuthenticated(true); 를 타야함.
//                new UsernamePasswordAuthenticationToken(authentication.getUsername(), null, authentication.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//		chain.doFilter(request, response);
	}

}
