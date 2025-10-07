package br.com.amisahdev.spring_pilot.api.hello.controllers;

import br.com.amisahdev.spring_pilot.api.hello.Hello;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/hello")
    public Hello helloName() {
        return new Hello(counter.incrementAndGet(), String.format(template, "World"));

    }

    @GetMapping("/hello/{name}")
    public Hello helloName(@PathVariable String name) {
        return new Hello(counter.incrementAndGet(), String.format(template, name));
    }
}
