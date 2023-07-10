package com.project.trainingteam.config.security;

import com.project.trainingteam.dto.user.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
//    private String jwtSecret="sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";


    @Value("${jwt.token.secret.key}")
    private String jwtSecret;
    @Value("${jwt.token.expired}")
    private int jwtExpiration;

    // Jwt Expiration in millis
//    public static final long jwtExpiration = (24 * 60 * 60 * 1000);
    //public static final long jwtExpiration = (60 * 1000);


    private Claims parseToken(String token){
        // Create JwtParser
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build();
        try {
            return jwtParser.parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean validateToken(String token){
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token){
        //get Claims
        Claims claims = parseToken(token);
        // Extract subject
        if(claims != null){
            return claims.getSubject();
        }
        return null;
    }

//    public String generateToken(String username, String fullname, String classUser, String major, String faculty, String phone, String roleName) {
//        // Create Key
//        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//
//        // Generate Token
//        Date currentDate = new Date();
//        Date expiration = new Date(currentDate.getTime() + jwtExpiration);
//
//        return Jwts.builder()
//                .claim("fullname", fullname)
//                .claim("classUser", classUser)
//                .claim("major", major)
//                .claim("faculty", faculty)
//                .claim("phone", phone)
//                .claim("roleName", roleName)
//                .setIssuedAt(currentDate)
//                .setExpiration(expiration)
//                .signWith(key,SignatureAlgorithm.HS256)
//                .compact();
//    }

    public String generateToken(String username, String fullname, String classUser, String major, String faculty, String phone, String roleName) {
        // Create Key
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // Generate Token
        Date currentDate = new Date();
        Date expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .claim("username",username)
                .claim("fullname", fullname)
                .claim("classUser", classUser)
                .claim("major", major)
                .claim("faculty", faculty)
                .claim("phone", phone)
                .claim("roleName", roleName)
                .setIssuedAt(currentDate)
                .setExpiration(expiration)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}
