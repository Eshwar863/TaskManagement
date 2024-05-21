package com.TaskManagement.ServiceImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl {

    public String generateJwtToken(UserDetails userDetails) {

        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSiginKey())
                .compact();
    }

    // returns the  decoded Key using  the SeceretKey
    private Key getSiginKey() {
        byte[] key = Decoders.BASE64.decode("ERRIPUKSAMS");
        return Keys.hmacShaKeyFor(key);
    }

    // returns all the claims from the token
    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }

    // returns the Body from claims present in the Generated Token(Particular Claim)
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaim(token);
        return claimsResolvers.apply(claims);
    }

    // returns the Email present in the Generated Token(username)
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}











//    public String generateToken(UserDetails userDetails){
//
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime()+360000);
//        String token = Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(currentDate)
//                .setExpiration(expireDate)
//                .signWith(SignatureAlgorithm.HS512,"JWTSecretKey")
//                .compact();
//        return token;
//    }
//
//    public String getEmailFromToken(String token){
//        Claims claims= Jwts.parser().setSigningKey("JWTSecretKey")
//                .parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
