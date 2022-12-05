/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//  www .  ja  va 2s  .  c o  m

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class testSocket {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileWriter bwr = new FileWriter("C:\\Users\\Admin\\OneDrive\\Documents\\java_netbeans\\MulticartSocket\\src\\main\\java\\log\\FirstNode.txt",true);
        PrintWriter pw = new PrintWriter(bwr);
        pw.println("add more text");
        pw.flush();
        pw.close();
    }
}