/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
public class testSocket {
    private ArrayList<String> list = new ArrayList<String>();
    private int k=0;
    public ArrayList<String> getList(){
        return list;
    }
    public testSocket(){
        list.add("12");
        list.add("ba");
        list.add("me");
        list.add("anh");
        list.add("chi");
    }
    public static void main(String[] args) throws IOException {
//        try {
//            Socket socket = new Socket("127.0.0.1",3000);
//        } catch (Exception e) {
//            System.out.println("error");
//        }
          /*
          testSocket test = new testSocket();
          test.getList().forEach(i->{
              if(i.equals("12")){
                  test.getList().set(test.getList().indexOf(i), i);
              }
          });
          test.getList().forEach(i->{
              System.out.print(i+" - ");
          });
          */
          
          //--------test multi thread-------
          testSocket test = new testSocket();
//          for(int i=0;i<3;i++){
//              Thread th = new Thread(){
//                  public void run(){
//                      test.k++;
//                      System.out.println("k bang: "+test.k);
//                  }
//              };
//              th.start();
//          }
            /*
            for(String s:test.list){
                if(s.equals("12")) test.list.set(test.list.indexOf(s), "change");
            }
            for(String s:test.list){
                System.out.print(s+"-");
            }
            */
            /*DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime date = LocalDateTime.now();
            System.out.println(format.format(date));*/
            boolean c=false;
            while(!c){System.out.println("c");c=true;}
            //System.out.println("k final bang: "+test.k);
          
    }
}
