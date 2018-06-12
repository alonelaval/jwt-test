package com.okycz.jwttest.controller;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


/**
 * @author huawei
 * @create 2018-06-12
 **/
@RestController
public class JwtTest2Contorller {

    public static final String JWT_SECERT ="tesaerta";
//
    @RequestMapping("/verify/{name}")
    public String verify(@PathVariable("name") String name,String jwt){
        Claims body = null;
        try {

            body = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();


            return "验证通过！欢迎你回来："+body.getSubject();

        } catch (Exception e) {

            return "验证失败！";
        }
    }

    @RequestMapping("/verifyerror/{name}")
    public String verifyerror(@PathVariable("name") String name,String jwt){
        Claims body = null;
        try {

            body = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();


            return "验证通过！欢迎你回来："+body.getSubject();

        } catch (Exception e) {

            return "验证失败！";
        }
    }

    @RequestMapping("/login2/{name}")
    public String login(@PathVariable("name") String name){

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + 5000;
        Date exp = new Date(expMillis);


        String compactJws = Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.HS512,generalKey())
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .compact();

        return  compactJws;

    }
    public static SecretKey generalKey() {
        byte[] encodedKey = new byte[0];
        try {
            encodedKey = Base64.decode(JWT_SECERT);
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

}
