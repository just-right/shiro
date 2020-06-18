package com.example.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Md5Hash("password", "helloworld", 1024).toString());
    }

}
