package com.sdc.study.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sdc.study.core.CommonResponse;
import com.sdc.study.security.jwt.JwtAuthToken;
import com.sdc.study.security.jwt.JwtService;
import com.sdc.study.security.service.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jwt")
//@Secured(value = "LOGIN")
@RequiredArgsConstructor
public class LoginController {

//    private final LoginService loginService;

	private final ApplicationContext context;

	private final JwtService jwtService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public CommonResponse login(HttpSession session) {
    	CommonResponse c = CommonResponse.builder().code("400").message("Success").build();

    	return c;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/jwtLogin")
    public CommonResponse jwtLogin(@RequestBody MemberDTO loginRequestDTO) {

        JwtAuthToken jwtAuthToken = (JwtAuthToken) jwtService.createAuthToken(loginRequestDTO);

        return CommonResponse.builder()
                .code("LOGIN_SUCCESS")
                .status(200)
                .message(jwtAuthToken.getToken())
                .build();
    }
//
    public static void main(String[] args) {
    	AntPathMatcher s = new AntPathMatcher();
    	System.out.println(s.match("/*/*", "/t/t"));
    	System.out.println(s.match("/**", "/t/t"));
    	System.out.println(s.match("/t?/**", "/t1/t/t"));

    	String packageName = "com.sdc.study";
    	final List<Class<?>> result = new LinkedList<Class<?>>();
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		provider.addIncludeFilter(new AnnotationTypeFilter(RequestMapping.class));
		for (BeanDefinition beanDefinition : provider
				.findCandidateComponents(packageName)) {
			try {
				result.add(Class.forName(beanDefinition.getBeanClassName()));
			} catch (ClassNotFoundException e) {
			}
		}

		Class<?>[] arr = result.toArray(new Class<?>[result.size()]);
		List<Class<?>> arrList =  Arrays.asList(arr);
		for(Class<?> c : arrList) {
			RequestMapping ano = c.getAnnotation(RequestMapping.class);
//			ano.value()
			System.out.println("S");
//			for(Annotation a : ano) {
//				a.annotationType().
//				System.out.println("s");
//			}
		}
		System.out.println("S");
	}

//    public Class<?>[] findAllConfigurationClassesInPackage(String packageName) {
//		final List<Class<?>> result = new LinkedList<Class<?>>();
//		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
//				true, new StandardServletEnvironment());
//		provider.addIncludeFilter(new AnnotationTypeFilter(Configuration.class));
//		for (BeanDefinition beanDefinition : provider
//				.findCandidateComponents(packageName)) {
//			try {
//				result.add(Class.forName(beanDefinition.getBeanClassName()));
//			} catch (ClassNotFoundException e) {
//			}
//		}
//		return result.toArray(new Class<?>[result.size()]);
//	}
}
