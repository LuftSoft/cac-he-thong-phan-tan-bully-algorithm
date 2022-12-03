/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class client {
    private Socket socket;

    public client(Socket socket) {
        this.socket = socket;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket sk;
        while(true){
            try {
                sk = new Socket("127.0.0.1",3000);
                break;
            } catch (Exception e) {
                System.out.println("can't connect to server");
            }
            Thread.sleep(1000);
        }
        //client cli = new client(new Socket("127.0.0.1",3000));
        write w = new write(sk);
        w.start();
    }
}

class read extends Thread{
    public void run(){
        
    }
}

class write extends Thread{
    private Socket socket;

    public write(Socket socket) {
        try {
            this.socket = socket;
        } catch (Exception e) {
            System.out.println("khong the ket noi voi server");
        }
    }
    
    public void run(){
        try {
            Scanner sc = new Scanner(System.in);
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            while(true){
                String msg = sc.nextLine();
                writer.writeUTF(msg);
                System.out.println("send to server success. msg: "+msg);
                String rev = reader.readUTF();
                System.out.println("receive from server. rev: "+rev);
            }
        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                System.out.println("Ngat ket noi server");
            }
            
        }
    }
    
}


class receive extends Thread{
    private Socket socket;
    private server sv;

    public receive(Socket socket,server sv) {
        this.socket = socket;
        this.sv = sv;
    }
    public void run(){
        try {
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            while(true){
                String msg = reader.readUTF();
                String [] list = msg.split("-");
                switch(list[0]){
                    case "check":
                        //---check xem m co phai dieu phoi vien khong
                        if(sv.getCoordinator().getId() == sv.getId()){
                            if(Integer.parseInt(list[1])<sv.getId()){
                                System.out.println("receive from client: "+msg);
                                writer.writeUTF("response from server");
                                return;
                            }
                            else{
                                System.out.println("id moi vao co id lon hon coor");
                                ArrayList<node> tmp = sv.getListNode();
                                tmp.forEach(item->{
                                    //-----setting la server da tro lai---
                                    if(item.getId() == Integer.parseInt(list[1])){
                                        node tmpnode = item;
                                        tmpnode.setTimeoout(true);
                                        tmp.set(tmp.indexOf(item), tmpnode);
                                    }
                                    sv.setListNode(tmp);
                                });
                                sv.bully(0);
                                //--can chay giai thuat bully tai day
                                return;
                            }
                        }
                        else{
                            sv.bully(0);
                            return;
                            //---bau chon bully tai day---
                        }
                    case "election":
                        writer.writeUTF("answer-"+sv.getId());
                        try {
                            node n=null;
                            for(node item : sv.getListNode()){
                                if(item.getId() == Integer.parseInt(list[1])){
                                    n=item;
                                    break;
                                }
                            }
                            if(n!=null){
                                Socket sk = new Socket(n.getHost(),n.getPort());
                                DataOutputStream output = new DataOutputStream(sk.getOutputStream());
                                output.writeUTF("answer-"+sv.getId());
                                output.close();
                                sk.close();
                            }
                        } catch (Exception e) {
                            System.out.println("election-answer-fail");
                        }
                        //tao socket gui answer
                        sv.bully(0);
                        return;
//                        break;
                    case "answer":
                        System.out.println("Da nhan duoc cau tra loi");
                        break;
                    case "coordinator":
                        //--- set lai dieu phoi vien ---
                        ArrayList<node> tmp = sv.getListNode();
                        for(node n:tmp){
                            if(n.getId() == Integer.parseInt(list[1])){
                                node ntmp = n;ntmp.setIsAdmin(true);
                                tmp.set(tmp.indexOf(n), ntmp);
                                //n.setIsAdmin(true);
                            }
                            if(n.isIsAdmin()){
                                //n.setIsAdmin(false);
                                node ntmp = n;ntmp.setIsAdmin(false);
                                tmp.set(tmp.indexOf(n), ntmp);
                            }
                        }
                        sv.setListNode(tmp);
                        break;
                    default:
                        break;
                }
                //System.out.println("receive from client: "+msg);
                //writer.writeUTF("response from server");
            }
        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                System.out.println("Ngat ket noi voi server");
            }
        }
    }
}

