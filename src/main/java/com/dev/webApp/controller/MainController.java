package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final RepairService repairService;

    @ResponseBody
    @GetMapping(
            value = "/test"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> test() {

        String dummyMessage = "안녕하세요";

        return new ResponseEntity<>(dummyMessage, HttpStatus.OK);
    }
}
