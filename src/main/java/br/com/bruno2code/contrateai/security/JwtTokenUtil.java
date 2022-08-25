package br.com.bruno2code.contrateai.security;

import io.jsonwebtoken.Claims;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    String TOKENKEY = "r9myNp3G641NYCTUqnyAOeaLTGLvE4B6";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        token = token.replace("Bearer ", "");
        return Jwts.parser().setSigningKey(TOKENKEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User u) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tipPes", u.getTipPes());
        claims.put("codUsu", u.getCodUsu());
        claims.put("datNas", u.getDatNas());
        claims.put("fonCel", u.getFonCel());
        claims.put("intNet", u.getIntNet());
        claims.put("nomUsu", u.getNomUsu());
        claims.put("sitPes", u.getSitPes());
        return doGenerateToken(claims, u);
    }

    private String doGenerateToken(Map<String, Object> claims, User u) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(u.getIntNet())
                .setId(String.valueOf(u.getCodUsu()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, TOKENKEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
