package kr.ac.hongik.dsc2023.ydy.team1.core.util;

import io.jsonwebtoken.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public final class JWTMaker {
    private static final Map<String, Object> headers = new HashMap<>();
    static {
        headers.put("type","JWT");
        headers.put("alg","HS256");
    }
    private static final String secretKey = "D6E1B1267F050831E9CDF33AF6BD82AAF7A186B82F51EB9C6DF2CF55B3D7B4EE83120970DE4643AED81FD1193EE7B455E74F2FBA1D555CEF071080E0D8BC9871";
    private static final long ACCESS_TOKEN_LIFE = 6000000;
    public static final long REFRESH_TOKEN_LIFE = 3 * ACCESS_TOKEN_LIFE;
    public static String makeToken(int userId, boolean isRefresh){
        long lifeTimeSeconds = isRefresh ? REFRESH_TOKEN_LIFE : ACCESS_TOKEN_LIFE;
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                .setHeader(headers)
                .setSubject("access")
                .claim("userID", userId)
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusSeconds(lifeTimeSeconds)))
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setIssuer("test")
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }
    public static int getUserID(String token){
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()));
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        String userIDString = (String) claimsJws.getBody().get("userID");
        return Integer.parseInt(userIDString);
    }
}
