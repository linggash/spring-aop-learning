package com.linggash.spring_aop_learning.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testHelloService() {
        var name = "Bambang";
        var lastname = "Udin";
        Assertions.assertEquals("Hello " + name, helloService.hello(name));
        Assertions.assertEquals("Hello " + name + " " + lastname, helloService.hello(name, lastname));
        Assertions.assertEquals("Bye " + name, helloService.bye(name));

        helloService.test();
    }
}
