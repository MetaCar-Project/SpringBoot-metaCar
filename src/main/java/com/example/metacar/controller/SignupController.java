package com.example.metacar.controller;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metaCar")
public class SignupController {

    @Autowired
    UserService service;
    @PostMapping("/checkid")
    public ResponseEntity<String> checkId(@RequestBody Socar_MemberDTO sm){
        System.out.println(sm);
        String id = sm.getId();
        if(service.getId(id)) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail",HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity addAccount(@RequestBody Socar_MemberDTO sm) {
        System.out.println(sm);
        service.createUser(sm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
