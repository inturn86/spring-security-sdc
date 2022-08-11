package com.sdc.study.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sdc.study.security.service.CustomUserDetailsService;

/**
 * 인증 절차 수행.
 * @author cjswo
 *
 */
@Component
//@RequiredArgsConstructor
//@NoArgsConstructor
public class SdcAuthenticationProvider implements AuthenticationProvider{

	private final CustomUserDetailsService customUserDetailsService;

	private final PasswordEncoder bCryptPasswordEncoder;

	public SdcAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder bCryptPasswordEncoder) {
		this.customUserDetailsService = customUserDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("username is not found. username=" + username);
        }

        if (!this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("password is not matched");
        }

        return new UsernamePasswordAuthenticationToken(password, user, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
