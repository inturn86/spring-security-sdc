package com.sdc.study.security;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sdc.study.security.handler.SdcAccessDeniedHandler;
import com.sdc.study.security.handler.SdcAuthenticationEntryPoint;
import com.sdc.study.security.handler.SdcSuccessHandler;
import com.sdc.study.security.jwt.JWTFilter;
import com.sdc.study.security.jwt.JwtAuthTokenProvider;
import com.sdc.study.security.provider.SdcAuthenticationProvider;
import com.sdc.study.security.service.CustomUserDetailsService;
import com.sdc.study.security.voter.CommonVoter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final CustomUserDetailsService customUserDetailsService;
//
//	@Setter(onMethod_ = @Autowired)
//	private AuthenticationSuccessHandler successHandler;

	private final SdcAuthenticationEntryPoint sdcAuthenticationEntryPoint;

	private final SdcAccessDeniedHandler sdcAccessDeniedHandler;

	private final SdcSuccessHandler sdcSuccessHandler;

	@Setter(onMethod_ = @Autowired(required = false))
	private JwtAuthTokenProvider jwtAuthTokenProvider;


	private final ApplicationContext applicationContext;

//	private final JWTFilter jwtFilter;

//	private final SdcFailureHandler sdcFailureHandler;

//	private final SdcFilter sdcFilter;

//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//
//    }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.addFilterBefore(new JWTFilter(jwtAuthTokenProvider), UsernamePasswordAuthenticationFilter.class);
        httpSecurity
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//                .and()

                .authorizeRequests()
//                .antMatchers("/api/v1/login/**").permitAll()
                	.antMatchers("/**")
                		.permitAll()
                	.antMatchers("/*")
                		.permitAll()
//                	.antMatchers("/**/*")
//                		.permitAll()
//                	.antMatchers("/security/*")
//                		.permitAll()
//                    .antMatchers("/api/v1/coffees/**").hasRole("ADMIN")
                	.anyRequest().authenticated()
//                	.expressionHandler(expressionHandler())
                	.accessDecisionManager(accessDeisionManager())

                .and()
                .formLogin()
                	.loginPage("/login.page")
                	.loginProcessingUrl("/login")
                	.usernameParameter("username")
                	.passwordParameter("password")
                	.successHandler(sdcSuccessHandler)
//                	.failureHandler(sdcFailureHandler)
//                	.defaultSuccessUrl("/mainHome.page")
                	.permitAll()
                .and()
                	.logout().permitAll()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/login.page")

                .and().csrf().disable()

                .exceptionHandling()

                .accessDeniedHandler(sdcAccessDeniedHandler)
                .and()


                .httpBasic()
//                .disable()

                .authenticationEntryPoint((request, response, e) ->
        	    {
        	        response.setContentType("application/json;charset=UTF-8");
        	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	        response.getWriter().write(new JSONObject()
        	                .put("timestamp", LocalDateTime.now())
        	                .put("message", "Access denied")
        	                .toString());
        	    })
               ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthenticationSuccessHandler getSuccessHandler() {
		return new SdcSuccessHandler();
	}

	@Bean
    public AuthenticationProvider authenticationProvider() {
        return new SdcAuthenticationProvider(customUserDetailsService, passwordEncoder());
    }

	//AccessDecisionManager 를 customize
//	.anyRequest().authenticated()
//	.accessDecisionManager(accessDeisionManager())
	public AccessDecisionManager accessDeisionManager() {
		RoleHierarchyImpl roleHisHierarchy = new RoleHierarchyImpl();

		//ROLE_ADMIN은 ROLE_USER의 상위 권한 정의
		roleHisHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

		//WEBExpression VOter는 expression Hanlder를 사용
		//Voter가 사용하는 expressionHanlder에게 Hiserachy를 설정
		DefaultWebSecurityExpressionHandler securityExpressionHanlder = new DefaultWebSecurityExpressionHandler();

		securityExpressionHanlder.setRoleHierarchy(roleHisHierarchy);

		List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(new WebExpressionVoter(), new RoleVoter(), new AuthenticatedVoter(), new CommonVoter(applicationContext));

		return new UnanimousBased(voters);
	}


	//securityExpressionHanlder 를 customize
//	.anyRequest().authenticated()
//	.expressionHandler(expressionHandler())
	public DefaultWebSecurityExpressionHandler expressionHandler() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

		DefaultWebSecurityExpressionHandler securityExpressionHanlder = new DefaultWebSecurityExpressionHandler();
		securityExpressionHanlder.setRoleHierarchy(roleHierarchy);
		return securityExpressionHanlder;
	}

}
