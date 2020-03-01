package com.company;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import java.util.HashMap;

public class MD5_BruteForcer {

    public static void main(String[] args) throws IOException {
        File file = new File("files/rockyou-samples.md5.txt");
        HashMap<String,Integer> hashie = new HashMap<String,Integer>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int occ;
        String out_txt="";

        while ((st = br.readLine()) != null){
            if(hashie.get(st) == null) occ = 1;
            else occ = 1 + hashie.get(st);
            hashie.put(st, occ);
            }
        String pass = "00000";
        while(pass != null){
            if(hashie.get(getMd5(pass)) != null){
                out_txt += hashie.get(getMd5(pass))+","+pass+"\n";
                //System.out.println("Password \'"+pass+"\' matched to hash "
                 //       +getMd5(pass)+" "+hashie.get(getMd5(pass)) + " times.");
            }
            pass = nextPass(pass, 4);
        }
        PrintWriter out = new PrintWriter( "md5-cracked.txt");
        out.println(out_txt);
        out.close();
    }

    private static String nextPass(String lastPass, int relevantChar){
        if(lastPass == null) return null;
        else if(relevantChar ==-1) return null;
        char[] lastpass_chars = lastPass.toCharArray();
        if((int) lastpass_chars[relevantChar] == 122){
            lastpass_chars[relevantChar]= '0';
            lastPass = new String (lastpass_chars);
            return nextPass(lastPass,relevantChar-1);
        }
        else if (lastpass_chars[relevantChar]=='9'){
            lastpass_chars[relevantChar]= 'a';
            return new String(lastpass_chars);
        }
        else{
            lastpass_chars[relevantChar] = (char) (lastpass_chars[relevantChar] + 1);
            return new String(lastpass_chars);
        }
    }

        public static String getMd5(String input)
        {
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

    }
