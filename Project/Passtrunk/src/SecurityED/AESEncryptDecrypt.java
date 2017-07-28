/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityED;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;

public class AESEncryptDecrypt {
   
    public static String encrypt(String plainText, String secK) throws Exception {   
      
        //Get Key:
        Key k = generateKey(secK);
        //encrypt text
       Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
       c.init(Cipher.ENCRYPT_MODE, k);
       byte[] encVal = c.doFinal(plainText.getBytes("UTF8"));
       String encryptedValue = Base64.getEncoder().encodeToString(encVal);
       return encryptedValue;
       
       
    }
    
    public static String decrypt(String encryptedText, String secK) throws Exception {
        
        //Get Key:
        Key k = generateKey(secK);
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //decrypt text
        c.init(Cipher.DECRYPT_MODE, k);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedText);
        byte[]decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
 
    public static Key generateKey(String secK) {
        MessageDigest digest;
        Key k = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashKey = digest.digest(secK.getBytes(StandardCharsets.UTF_8));
            k = new SecretKeySpec(hashKey,"AES");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
    
    
}
