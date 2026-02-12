package org.leeknow.userservice.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtKeysConfig {

    @Bean
    public RSAKey rsaKey(@Value("classpath:keys/jwt-private.pem") Resource privateKeyPath,
                         @Value("classpath:keys/jwt-public.pem") Resource publicKeyPath,
                         @Value("${app.jwt.kid}") String kid) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateKey rsaPrivateKey = rsaPrivateKey(privateKeyPath);
        RSAPublicKey rsaPublicKey = rsaPublicKey(publicKeyPath);

        return new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .keyID(kid)
                .build();
    }

    @Bean
    public JWKSet jwkSet(RSAKey rsaKey) {
        return new JWKSet(rsaKey.toPublicJWK());
    }

    private static RSAPrivateKey rsaPrivateKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = new String(resource.getInputStream().readAllBytes());
        byte[] decode = Base64.getMimeDecoder().decode(pem);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    private static RSAPublicKey rsaPublicKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String pem =new String(resource.getInputStream().readAllBytes());
        byte[] decode = Base64.getMimeDecoder().decode(pem);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
