package com.example.email.controller;

import com.example.email.dto.EmailRequestDto;
import com.example.email.service.CommunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/email")
public class CommunicationController {

    private final CommunicationService commService;

    @Autowired
    public CommunicationController(CommunicationService commService) {
        this.commService = commService;
    }

    @PostMapping("/simple")
    public ResponseEntity<Void> sendWelcomeEmail(@RequestBody EmailRequestDto dto){
        commService.sendSimpleWelcome(dto.getEmail(), dto.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/template")
    public ResponseEntity<Void> sendWelcomeEmailFromTemplate(@RequestBody EmailRequestDto dto){
        commService.sendWelcomeMailFromTemplate(dto.getEmail(), dto.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pretty")
    public ResponseEntity<Void> sendPrettyWelcomeEmailFromTemplate(@RequestBody EmailRequestDto dto){
        commService.sendPrettyWelcomeMailFromTemplate(dto.getEmail(), dto.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
