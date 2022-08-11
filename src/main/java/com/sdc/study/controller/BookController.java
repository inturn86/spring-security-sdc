package com.sdc.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sdc.study.core.CommonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book")
@Secured(value = "ROLE_BOOK")
@RequiredArgsConstructor
public class BookController {

    @RequestMapping(path = "/getBookList", method = RequestMethod.POST)
    public CommonResponse getAllCoffees(HttpSession session) {
    	CommonResponse c = CommonResponse.builder().code("400").message("Success").build();

        return c;
    }

    @GetMapping(path = "/book.page")
    public String page() {

    	return "/book/book";
    }
}