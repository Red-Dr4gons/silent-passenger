package si.red.dragons.utils;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import si.red.dragons.entity.Account;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Set;

public final class JWTUtils {
    private static final long DAY = 60 * 60 * 24L;
    private static final long SESSION_DURATION = DAY * 2L;
    private static final String PRIVATE_KEY_NAME = "private.pem";

    private JWTUtils() {
        // no-op: utility class
    }

    public static String generateToken(Account account) throws Exception {
        PrivateKey pk = readPrivateKey("/" + PRIVATE_KEY_NAME);
        JwtClaims claims = new JwtClaims();
        claims.setClaim(Claims.exp.name(), currentTimeInSecs() + SESSION_DURATION);
        claims.setClaim(Claims.auth_time.name(), currentTimeInSecs());
        claims.setClaim(Claims.iss.name(), "https://silent-passenger.com");
        claims.setClaim(Claims.upn.name(), account.getEmail());
        claims.setClaim(Claims.groups.name(), Set.of("user"));
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(pk);
        jws.setKeyIdHeaderValue("PRIVATE_KEY_NAME");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        return jws.getCompactSerialization();

    }

    /**
     * Read a PEM encoded private key from the classpath
     *
     * @param pemResName - key file resource name
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        InputStream contentIS = JWTUtils.class.getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int length = contentIS.read(tmp);
        return decodePrivateKey(new String(tmp, 0, length, StandardCharsets.UTF_8));
    }

    /**
     * Decode a PEM encoded private key string to an RSA PrivateKey
     *
     * @param pemEncoded - PEM string for private key
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);

        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    /**
     * @return the current time in seconds since epoch
     */
    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
