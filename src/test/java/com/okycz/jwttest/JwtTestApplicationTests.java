package com.okycz.jwttest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtTestApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
      @Test
    public void contextLoads() {
        String sign = this.restTemplate.getForObject("http://localhost:8080/login/".concat("huawei"), String.class);
        System.out.println(sign);
    }
    @Test
    public void contextLoads2() throws InterruptedException {

        String sign = this.restTemplate.getForObject("http://localhost:8080/login2/".concat("huawei"), String.class);
        System.out.println(sign);
        String result  = this.restTemplate.getForObject("http://localhost:8080/verify/".concat("huawei").concat("?jwt=").concat(sign), String.class);
        System.out.println(result);

        Thread.sleep(10000L);

        System.out.println(sign);
        result = this.restTemplate.getForObject("http://localhost:8080/verifyerror/".concat("huawei").concat("?jwt=").concat(sign), String.class);
        System.out.println(result);


    }


//    @LocalServerPort
//    private int port;



}
