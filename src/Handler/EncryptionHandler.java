/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * a class which helps to facilitate a certain extend of security regarding 
 * {@link Classes.User} profiles. Passwords are encrypted
 * @author Patricia
 */
public class EncryptionHandler {
    
    /**
     * function to digest a password of a {@link Classes.User} and return it
     * @param input the password of a {@link Classes.User}
     * @return the encrypted password
     */
    public static String encryptPassword(char[] input) {
        try {
            //Encryption Object is built
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            //original password is parsed into the encrypter
            messageDigest.update(new String(input).getBytes());
            //password is encrypted
            byte[] digestedBytes = messageDigest.digest();
            //String builder since the encrypted password ain't a String anymore.
            StringBuilder sb = new StringBuilder();
            //Format the encrypted Password to its respective String Value to return it
            for (byte b : digestedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }
}
