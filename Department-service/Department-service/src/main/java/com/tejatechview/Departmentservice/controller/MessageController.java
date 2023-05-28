package com.tejatechview.Departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping
@RestController
public class MessageController {
    @Value("${messageName}")
    private String messageName;

    @GetMapping("/user/message")
    public String getMessageName(){
        return messageName;
    }
}
