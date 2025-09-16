package com.example.petback.utils;

import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.*;

import java.sql.Date;

public class JWTUtil {
    private static long time=1000*60*60*2;
    private static String signature="admin"; //签名

   public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();

        //jwt 由header playload sign 组成
        String jwtToken=jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload 载荷
                .claim("username","hzw")
                .claim("role","admin")
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature 签名
                .signWith(SignatureAlgorithm.HS256,signature)
                //将三部分连接起来
                .compact();
return jwtToken;
    }

    public void parse() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Imh6dyIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NzExMjAxOTEsImp0aSI6ImQxMGYwNjIwLThjM2ItNGIyMC1iYzUwLTE4YzE5OTIxNjM0MyJ9.wFQzUPi4t-5DJBxbuZksEHy2rm60ZWjl5FtMMUAEEzI";
        JwtParser parser = Jwts.parser();
        //
        Jws<Claims> claimsJws = parser.setSigningKey(signature).parseClaimsJws(token);
        //get
        Claims claims = claimsJws.getBody();
        Object username = claims.get("username");
        System.out.println(username);
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());

    }
}



