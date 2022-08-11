package com.sdc.study.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sdc.study.security.service.appmapping.AppMapping;
import com.sdc.study.security.service.appmapping.AppMappingRepository;
import com.sdc.study.security.service.member.Member;
import com.sdc.study.security.service.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final AppMappingRepository appMappingRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Optional<Member> opt = memberRepository.findByUsername(username);

    	if(opt.isEmpty()) {
    		return null;
    	}
    	Optional<User> pa = opt.map(this::createSpringSecurityUser);

        return pa.get();
    }

    private User createSpringSecurityUser(Member member) {

    	List<GrantedAuthority> grantedAuthorities = appMappingRepository.findAllByRole(member.getRole()).stream().map(
    			o -> new SimpleGrantedAuthority(o.getAppId())
    	).collect(Collectors.toList());


//        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));
        //TODO: username 에 email을 넣는 방법이 적합한지?
        User user = new User(member.getEmail(), member.getPassword(), grantedAuthorities);
        return user;
    }
}
