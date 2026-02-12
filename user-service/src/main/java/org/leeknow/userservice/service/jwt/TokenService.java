package org.leeknow.userservice.service.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    private final RSAKey rsaKey;

    private final String issuer;

    private final long validityPeriod;

    public TokenService(RSAKey rsaKey,
                        @Value("${app.jwt.issuer}") String issuer,
                        @Value("${app.jwt.access-ttl-seconds}") long validityPeriod) {
        this.rsaKey = rsaKey;
        this.issuer = issuer;
        this.validityPeriod = validityPeriod;
    }

    public String generateAccessToken(String email, List<String> roles) throws JOSEException {
        Instant now = Instant.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer(issuer)
                .expirationTime(Date.from(now.plusSeconds(validityPeriod)))
                .claim("roles", roles)
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(rsaKey.getKeyID())
                .type(JOSEObjectType.JWT)
                .build();

        SignedJWT signedJWT = new SignedJWT(header, claims);
        JWSSigner signer = new RSASSASigner(rsaKey.toPrivateKey());
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }
}
