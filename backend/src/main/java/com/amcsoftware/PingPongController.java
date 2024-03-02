package com.amcsoftware;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {
    record PingPong(String result){}
    @GetMapping("/ping")
    public PingPong getPingPong() {
        return new PingPong("Pong");

    }

    @GetMapping("/k")
    public PingPong rano() {
        return new PingPong("new resta was sammie");
    }



}
