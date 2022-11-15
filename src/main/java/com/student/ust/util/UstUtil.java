package com.student.ust.util;
import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;
import sun.security.provider.SHA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The type Ust util.
 */
public class UstUtil {


    /**
     * Validate email int.
     *
     * @param email the email
     * @return the int
     */
    public static int validateEmail(String email) {
        String regex="^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches()==true){
            return  0;
        }
        else {
            throw new InvalidEmail();
        }
    }

    /**
     * Validate password int.
     *
     * @param password the password
     * @return the int
     */
    public static int validatePassword(String password) {
        String regex1="^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,})(?=.*[A-Z]).{8,}$";
        Pattern pattern=Pattern.compile(regex1);
        Matcher matcher=pattern.matcher(password);
        if(matcher.matches()==true){
            return  0;
        }
        else {
            throw new InvalidPassword();
        }
    }

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     */
    public static String hashPassword(String password){
        try{
            return  toHexString(getSHA(password));
        }
        catch (NoSuchAlgorithmException | NoSuchMethodException e){
            throw  new InvalidPassword();
        }
    }
    private static  byte[] getSHA(String password) throws NoSuchMethodException, NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        return  md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * To hex string string.
     *
     * @param hash the hash
     * @return the string
     */
    public static String toHexString(byte[] hash){
        BigInteger number=new BigInteger(1,hash);
        StringBuilder hexString=new StringBuilder(number.toString(16));
        while (hexString.length()<64) {
            hexString.insert(0,0);
        }
        return  hexString.toString();
    }

}
