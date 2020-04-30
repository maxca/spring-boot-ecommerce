package com.ecommerce.helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptionHelper {
    private static String secretKey = "1qaZ2wsx3edc4rfv5tgb6yhbn7jm";
    private static String salt = "!@#$%^&*(!@WSX81Samark";
    private static byte[] iv = {0, 2, 3, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    private static String algorithm = "PBKDF2WithHmacSHA256";

    public static String md5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            return Base64.getEncoder()
                    .encodeToString(getCipher().doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            return new String(getCipher().doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Cipher getCipher() {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(), ivSpec);
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKeySpec getSecretKeySpec() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
