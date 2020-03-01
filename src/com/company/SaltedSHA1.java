package com.company;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SaltedSHA1 {



    public static void main(String[] args)throws IOException {
        String[] passes = {"123456","12345","123456789","password","iloveyou","princess","1234567","rockyou",
                "12345678","abc123","nicole","daniel","babygirl","monkey","lovely","jessica","654321","michael",
                "ashley","qwerty","111111","iloveu","000000","michelle","tigger"};
        int[] occ = new int[25];
        File file = new File("files/rockyou-samples.sha1-salt.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String salt;
        String out_txt = "";
        String hash;
        while ((st = br.readLine()) != null){
            salt = st.substring(7,17);
            hash =  st.substring(18);
            for(int i = 0; i < 25; i++){
                if(hash.equals(encrypt(salt + passes[i]))){
                    occ[i]++;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(occ));
        for(int i = 0; i < 25; i++){
            out_txt+=occ[i]+","+passes[i]+"\n";
        }
        PrintWriter out = new PrintWriter( "salt-cracked.txt");
        out.println(out_txt);
        out.close();

    }

    private static String encrypt(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
