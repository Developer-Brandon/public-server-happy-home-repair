package com.dev.webApp.controller;

import com.dev.webApp.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/faq")
@Controller
public class FaqController {

    @Autowired
    private FaqService faqService;

    // todo: rest api 개발하는 방법을알아본 후에 추후 계속해서 개발 하기
}
