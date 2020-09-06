package com.example.demo.until;

import com.example.demo.model.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Date;
@Configuration
public class JwtUtils {

    public String createJWT(String id, String subject, long ttlMillis, Admin admin) {
        //SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        String secretKey = "pass123";
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .claim("roleId", admin.getRoleId())
                .claim("shopId", admin.getShopId())
                .signWith(SignatureAlgorithm.HS256, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    public Claims parseJWT(String jwt) throws Exception {
        String secretKey = "pass123";
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
