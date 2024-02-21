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
    @GetMapping("/p")
    public PingPong getPingPon() {
        return new PingPong("Pon");

    }

    @GetMapping("/k")
    public PingPong getPin() {
        return new PingPong("in");

    }

    @GetMapping("/sil")
    public PingPong getPi() {
        return new PingPong("silly rabbit");

    }

}
