package com.challenge.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryptor {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "K7gNU3sdo+OL0wNhqoVWhr3g6s1xYv72ol/pe/Unols="; // 16 chars for AES-128

    public static String encrypt(String plainText) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        SecretKeySpec key = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        SecretKeySpec key = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
