package com.sdc.study.security.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.sdc.study.security.define.ERole;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

	private static final String AUTHORIZATION_HEADER = "x-auth-token";

	private final JwtAuthTokenProvider jwtAuthTokenProvider;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
//
//		SecurityContextHolder.getContext()
//				.setAuthentication(new UsernamePasswordAuthenticationToken("anonymous", "anonymous"));
//		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
//
//
//		filterChain.doFilter(servletRequest, servletResponse);



		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		Optional<String> tokenOpt = resolveToken(httpServletRequest);

		String token = StringUtils.EMPTY;
		if (tokenOpt.isPresent()) {
			token = tokenOpt.get();
		}
		JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token);

		if (StringUtils.isNotEmpty(token) && jwtAuthToken.validate()) {

			SecurityContextHolder.getContext().setAuthentication(jwtAuthTokenProvider.getAuthentication(jwtAuthToken));
		} else if (StringUtils.isNotEmpty(token)) {
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken("anonymous", "anonymous"));
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		}
		else if(SecurityContextHolder.getContext().getAuthentication() != null &&
				SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o ->
				StringUtils.equals(o.getAuthority(), ERole.INTERFACE.getCode()))
				) {
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private Optional<String> resolveToken(HttpServletRequest request) {
		String authToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.isNotEmpty(authToken)) {
			return Optional.of(authToken);
		} else {
			return Optional.empty();
		}
	}
}
