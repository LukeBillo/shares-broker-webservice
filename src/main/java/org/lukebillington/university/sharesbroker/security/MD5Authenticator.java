package org.lukebillington.university.sharesbroker.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Authenticator {
    public static boolean Authenticate(String password, String hash) {
        return MD5(password).equals(hash);
    }

    public static String MD5(String password) {
        MessageDigest md5 = GetMd5MessageDigest();

        // below code is courtesy of StackOverflow
        // https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
        byte[] passwordBytes = GetUtf8Bytes(password);
        byte[] digest = md5.digest(passwordBytes);


        // returns MD5 digest as a string
        return String.format("%032x", new BigInteger(1, digest));
    }

    public static MessageDigest GetMd5MessageDigest() {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5;
    }

    public static byte[] GetUtf8Bytes(String string) {
        byte[] bytes = new byte[0];

        try {
            bytes = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
