package com.sdc.study.security.voter;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Service
@RequiredArgsConstructor
public class CommonVoter implements AccessDecisionVoter<Object>{

//	@Getter @Setter(onMethod_ = @Autowired)
	private final ApplicationContext applicationContext;

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

		try {
			ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			if(ObjectUtils.isEmpty(attr) == false) {
				HttpServletRequest request = attr.getRequest();
				String url = request.getServletPath();

//				System.out.println("S");
				if(authentication.getAuthorities().stream().map(t -> t.getAuthority()).anyMatch(o -> StringUtils.equals(o, "ROLE_ANONYMOUS"))
						&& !StringUtils.equals(url, "/login.page")
						&& !StringUtils.equals(url, "/api/jwt/jwtLogin")) {
					return ACCESS_DENIED;
				};

				RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
				HandlerExecutionChain chain = requestMappingHandlerMapping.getHandler(request);
				if(chain != null) {
					if(StringUtils.isEmpty(url)) {
						return ACCESS_DENIED;
					}

					//화면에 대한 권한.
					if(StringUtils.contains(url, '.')) {
						return ACCESS_ABSTAIN;
					}
					//일반 api에 대한 권한.
					else {
						return ACCESS_ABSTAIN;
					}
				}

				else if(ObjectUtils.isEmpty(authentication) == false && authentication.getAuthorities().stream().anyMatch(o -> StringUtils.equals(o.getAuthority(), "ROLE_ANONYMOUS")) ) {
					return ACCESS_DENIED;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ACCESS_ABSTAIN;
	}
}
