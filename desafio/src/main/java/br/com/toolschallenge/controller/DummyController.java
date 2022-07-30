package br.com.toolschallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/teste")
public class DummyController {

    @GetMapping
    public ResponseEntity<String> helloWord(){
        return ResponseEntity.ok("Hello Word");
    }
}
