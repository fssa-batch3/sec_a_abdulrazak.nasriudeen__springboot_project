package com.reparo.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public abstract class UserPassword {

    protected  byte[] generateSalt() {
        byte[] salt = new byte[16]; // You can adjust the size of the salt
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    protected  byte[] deriveKey(String password, byte[] salt) throws Exception {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(keySpec).getEncoded();
    }

    protected  boolean verifyPassword(String inputPassword, String salt, String storedHashedPassword) throws Exception {
        byte[] decodedSalt = Base64.getDecoder().decode(salt);
        byte[] decodedPassword = Base64.getDecoder().decode(storedHashedPassword);
        byte[] hashedPasswordInput = deriveKey(inputPassword, decodedSalt);
        return Arrays.equals(hashedPasswordInput, decodedPassword);
    }


}
