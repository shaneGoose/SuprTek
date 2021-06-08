package com.guzman.z.shane.suprtek;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuprTekController {

    @GetMapping("/test")
    public String tester() {
        return "<h1>Hello there!<h1>";
    }
}
