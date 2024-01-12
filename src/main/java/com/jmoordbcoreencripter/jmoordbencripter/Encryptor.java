/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmoordbcoreencripter.jmoordbencripter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Lokesh Gupta Email: howtodoinjava@gmail.com Twitter: @HowToDoInJava
 * https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
 */
public class Encryptor {
    private static Long count=0L;
    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Encryptor.encrypt()->[Error while encrypting]: " + e.toString());
        }
        return null;
    }

    public static String decrypt(final String strToDecrypt, final String secret, final String nameOfMethod) {
        try {
            count++;
//            System.out.println("\t\tþ[decrypt count] "+count);
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("\t[test] secretKey" + secretKey);
            System.out.println("\t[test] strToDecrypt" + strToDecrypt);
            System.out.println("\t[test] nameOfMethod" + nameOfMethod);
          
            System.out.println("Encryptor.decrypt()-> [Error while dencrypting]: " + e.toString());
        }
        return null;
    }
}
