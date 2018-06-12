package com.okycz.jwttest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;


/**
 * @author huawei
 * @create 2018-06-12
 **/
@RestController
public class JwtTestContorller {

    @RequestMapping("/hello/{name}")
    public String helloWorld(@PathVariable("name") String name,String sign){
        return  "hello,"+name;

    }
    @RequestMapping("/login/{name}")
    public String login(@PathVariable("name") String name){
        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return  compactJws;

    }


}
