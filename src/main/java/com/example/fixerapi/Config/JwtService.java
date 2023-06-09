package com.example.fixerapi.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String Secret_Key = "4226452948404D635166546A576E5A7234753778214125442A462D4A614E6452";
     public String extractUsername(String jwt){

         return extractClaim(jwt,Claims::getSubject);

     }

     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
         final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
     }

     public String generateToken(UserDetails userDetails){
         return generateToken(new HashMap<>(),userDetails);
     }

     public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails){

         return Jwts.builder()
                 .setClaims(extraClaims)
                 .setSubject(userDetails.getUsername())
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 25))
                 .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                 .compact();
     }

     public boolean isTokenValid(String token, UserDetails userDetails){
         final String username = extractUsername(token);
         return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
     }

     private boolean isTokenExpired(String token) {
         return extractExpiration(token).before(new Date());
     }

     private Date extractExpiration(String token){
         return extractClaim(token, Claims::getExpiration);
     }

     private Claims extractAllClaims(String token){
         return Jwts.parserBuilder()
                 .setSigningKey(getSignInKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
     }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Secret_Key);
         return Keys.hmacShaKeyFor(keyBytes);
    }

}
