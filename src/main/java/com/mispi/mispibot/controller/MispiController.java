package com.mispi.mispibot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.mispi.mispibot.mispiApi.MispiServer;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping(path = "/mispi")
public class MispiController {

    @Autowired
    private MispiServer mispiServer;

    
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void mispiCall(@RequestParam Map<String, String> body) {
        mispiServer.sendFireAlert(Long.parseLong(body.get("mispiNum")),Long.parseLong(body.get("errorCode")) );
       
    }
    

}
