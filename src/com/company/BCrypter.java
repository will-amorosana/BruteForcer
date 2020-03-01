package com.company;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class BCrypter {



    public static void main(String[] args)throws IOException {
        String pass = "123456";
        String out_txt = "";
        File file = new File("files/rockyou-samples.bcrypt.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String salt;
        String hash;
        while ((st = br.readLine()) != null){
            if(BCrypt.checkpw(pass, st))
                break;
            }
            System.out.println("Found one!");
        /*
        PrintWriter out = new PrintWriter( "salt-cracked.txt");
        out.println(out_txt);
        out.close();*/
        }

    }


