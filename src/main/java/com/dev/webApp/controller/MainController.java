package com.dev.webApp.controller;

import com.dev.webApp.domain.vo.RepairTypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    // private

    @GetMapping(
            value = "/test"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> test() {

        String dummyMessage = "안녕하세요";

        return new ResponseEntity<>(dummyMessage, HttpStatus.OK);
    }

    //    @GetMapping(
    //            value = "/repair-type/list"
    //            , produces = "text/plain; charset=UTF-8"
    //    )
    //    public ResponseEntity<String> getRepairTypeList() {
    //
    //        List<RepairTypeVO> repairTypeVOList =
    //
    //        return new ResponseEntity<>(dummyMessage, HttpStatus.OK);
    //    }
}
