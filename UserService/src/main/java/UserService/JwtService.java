package UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    
    // Valores por defecto añadidos
    @Value("${jwt.secret:TuClaveSecretaMuySeguraYLongaParaJWT1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ}")
    private String SECRET_KEY;
    
    @Value("${jwt.expiration:86400000}") // 24 horas por defecto
    private long EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (SignatureException e) {
            // Firma inválida
            return false;
        } catch (MalformedJwtException e) {
            // Token mal formado
            return false;
        } catch (ExpiredJwtException e) {
            // Token expirado
            return false;
        } catch (UnsupportedJwtException e) {
            // Token no soportado
            return false;
        } catch (IllegalArgumentException e) {
            // Claims vacíos
            return false;
        }
    }

    public String extractUsername(String token) throws ExpiredJwtException, UnsupportedJwtException, 
            MalformedJwtException, SignatureException, IllegalArgumentException {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException, 
            UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, UnsupportedJwtException, 
            MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
    // Usa la clave tal cual como UTF-8, sin decodificar Base64
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(java.nio.charset.StandardCharsets.UTF_8));
}

    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date extractExpiration(String token) throws ExpiredJwtException {
        return extractClaim(token, Claims::getExpiration);
    }
}