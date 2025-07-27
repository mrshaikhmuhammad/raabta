package security;

import java.security.*;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class AES{
    private SecretKey key;
    private Cipher cipher;

    public void generateKey(){}{
        try{
            cipher = Cipher.getInstance("AES");

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            key = keyGenerator.generateKey();
        } catch(NoSuchAlgorithmException | NoSuchPaddingException e){
        }
    }
    

    public String encrypt(String text) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptBytes = cipher.doFinal(text.getBytes());
        
        return Base64.getEncoder().encodeToString(encryptBytes);
    }

    public String decrypt(String text, SecretKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptBytes = cipher.doFinal(Base64.getDecoder().decode(text));
        return new String(decryptBytes);
    }

    public String getKey(){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    public void setKey(String key){
        byte[] keyBytes = Base64.getDecoder().decode(key); 
        this.key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }

    public static SecretKey stringToKey(String key){
        byte[] keyBytes = Base64.getDecoder().decode(key); 
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }
} 


