package com.dev.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping(
            value = "/getText"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> getText() {

        String dummyMessage = "안녕하세요";

        return new ResponseEntity<>(dummyMessage, HttpStatus.OK);
    }
}
