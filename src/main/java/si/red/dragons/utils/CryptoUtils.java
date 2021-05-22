package si.red.dragons.utils;

import org.jboss.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class CryptoUtils {
    private static final Logger log = Logger.getLogger(CryptoUtils.class);

    private CryptoUtils() {
    }

    public static String sha512(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("Couldn't create hash: ", e);
        }
        return null;
    }
}
