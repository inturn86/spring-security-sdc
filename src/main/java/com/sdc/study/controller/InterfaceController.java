package com.sdc.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdc.study.core.CommonResponse;
import com.sdc.study.service.CoffeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/interface")
//@Secured(value = "ROLE_COFFEE")
@RequiredArgsConstructor
public class InterfaceController {

	private final CoffeeService coffeeService;

    @PostMapping(path = "/getCoffeeList")
    public CommonResponse getAllCoffees(HttpSession session) {
    	CommonResponse c = CommonResponse.builder().code("400").message("Success").build();

//    	coffeeService.sendOutbound("COFFEE", "test");
    	return c;
    }

}