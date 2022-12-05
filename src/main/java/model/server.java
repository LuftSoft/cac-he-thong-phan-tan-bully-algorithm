/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ThirdNode;

/**
 *
 * @author Admin
 */
public class server {
    private int Id;
    private boolean IsAdmin=false;
    private int Port;
    private ArrayList<node> listNode = new ArrayList<node>();
    private JFrame mainFrame;
    private JTextPane textPane;
    private JTextPane tpChatbox;
    private JTextField tfMsg;
    private JButton btnCheckCoor;
    private boolean flagBully=true;
    private String msgText = "";
    private String LogContain = "";

    public server(JFrame mainFrame,int port,int id,JTextPane pane,JTextPane chat,JTextField tf,JButton btn) {
        this.mainFrame = mainFrame;
        this.Port = port;
        this.Id = id;
        initListNode();
        this.textPane = pane;
        this.tfMsg = tf;
        this.btnCheckCoor = btn;
        this.tpChatbox = chat;
    }


    public int getId() {return Id;}

    public boolean isIsAdmin() {return IsAdmin;}

    public int getPort() {return Port;}

    public ArrayList<node> getListNode() {return listNode;}

    public JFrame getMainFrame() {return mainFrame;}

    public void setId(int Id) {this.Id = Id;}

    public void setIsAdmin(boolean IsAdmin) {this.IsAdmin = IsAdmin;}

    public void setPort(int Port) {this.Port = Port;}


    public void setListNode(ArrayList<node> listNode) {this.listNode = listNode;}

    public void setMainFrame(JFrame mainFrame) {this.mainFrame = mainFrame;}
    //---getter,setter
    //----execute----
    public void execute(){
        Thread th = new Thread(){
            public void run(){
                ServerSocket server;
                try {
                    server = new ServerSocket(Port);
                    while(true){
                        Socket socket = server.accept();
                        System.out.println("Da ket noi voi "+socket);
//                        receive r = new receive(socket,server.this);
//                        r.start();
                        receive(socket);
//                        if(!flagBully){
//                            bully(2);
//                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Khong the tao server: "+Id);
                }
                
            }
        };
        th.start();
    }
    //----bully----
    public void bully(int opt){
        //if(flagBully){return;}
        switch(opt){
            case 0:
                JOptionPane.showMessageDialog(mainFrame, "Điều phối viên không phản hổi! \n Bắt đầu thực hiện giải thuật bầu chọn Bully", "Thông báo", JOptionPane.YES_OPTION);
                break;
            case 1:
                JOptionPane.showMessageDialog(mainFrame, "Bạn vừa nhận request từ một tiến trình có Id lớn hơn! \n Bắt đầu thực hiện giải thuật bầu chọn Bully", "Thông báo", JOptionPane.YES_OPTION);
                break;
            case 2:
                JOptionPane.showMessageDialog(mainFrame, "Bạn vừa nhận một election \n Bắt đầu thực hiện giải thuật bầu chọn Bully", "Thông báo", JOptionPane.YES_OPTION);
                break;
            default:
                break;
        }
        
        System.out.println("Bat dau thuc hien giai thuat bau chon bully");
        int cnt=0;
        for(node i : this.listNode){
            if(i.getId()>this.Id){
                       try {
                            Socket socket = new Socket(i.getHost(),i.getPort());
                            DataInputStream reader = new DataInputStream(socket.getInputStream());
                            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                            String msg = "election-"+Id;
                            writer.writeUTF(msg);
                            System.out.println("bully send - "+msg);
                            String rev = reader.readUTF();
                            System.out.println("bully rev-"+rev);
                            reader.close();
                            writer.close();
                            socket.close();
                            System.out.println("bully-da xac nhan co tien trinh co Id cao hon minh");
                            cnt++;
                        } catch (IOException ex) {
                            System.out.println("bully - can't create socket connect to "+i.getHost()+i.getPort());
                            //--- có thể không cần field Timeout---
                            listNode.forEach(item->{
                                if(item.getId() == i.getId()){
                                    node tmp = item;
                                    tmp.setTimeoout(true);
                                    listNode.set(listNode.indexOf(item), tmp);
                                    //----setting node nay da bi hong----
                                    //item.setTimeoout(true);
                                }
                            });
                            //-------id khong phan hoi--------
            }
        }
    }   
        //--- co it nhat 1 tien trinh co Id cao hon => dung im cho thong bao
        if(cnt>0){
            System.out.println("bully confirm-da xac nhan co it nhat 1 tien trinh co Id cao hon minh");
            switch(opt){
            case 0:
                JOptionPane.showMessageDialog(mainFrame, "Quá trình bầu chọn đã kết thúc!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                break;
            case 1:
                JOptionPane.showMessageDialog(mainFrame, "Quá trình bầu chọn đã kết thúc!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                break;
            case 2:
                JOptionPane.showMessageDialog(mainFrame, "Quá trình bầu chọn đã kết thúc!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                break;
            default:
                break;
            }
//            tfMsg.setText("");
//            tfMsg.setEditable(true);
//            btnCheckCoor.setEnabled(true);
            return;
        }
        //--- day la TH khong co tien trinh nao co Id cao hon => gui xac nhan minh chinh la dieu phoi vien
        for(node n:listNode){
          if(n.isIsAdmin()){
              node tmp = n; tmp.setIsAdmin(false);
              listNode.set(listNode.indexOf(n), tmp);
          }  
          if(n.getId() == Id){
              node tmp = n; tmp.setIsAdmin(true);
              listNode.set(listNode.indexOf(n), tmp);
          }
        }
        JOptionPane.showMessageDialog(mainFrame, "Quá trình bầu chọn đã kết thúc! Bạn chính là điều phối viên mới.", "Thông báo", JOptionPane.DEFAULT_OPTION);
        tfMsg.setText("");
        tfMsg.setEditable(false);
        btnCheckCoor.setEnabled(false);
        //--- gui xac nhan dieu phoi vien moi
        Coordinator();
        //flagBully = true;
    }
    //--- Gửi phản hồi nếu nhận ra mình là điều phối viên----
    public void Coordinator(){
        for(node n : listNode){
            if(Id != n.getId()){
                Thread th = new Thread(){
                    public void run(){
                        try {
                            //--- chỉ gửi thông điệp, không cần quan tâm phản hồi ---
                            Socket socket = new Socket(n.getHost(),n.getPort());
                            //DataInputStream reader = new DataInputStream(socket.getInputStream());
                            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                            writer.writeUTF("coordinator-"+Id);
                            System.out.println("da send coordinator to "+n.getId());
                            //String rev = reader.readUTF();
                            writer.close();
                            //reader.close();
                            socket.close();
                        } catch (Exception e) {
                            System.out.println("node "+n.getId()+" was broke");
                        }
                    }
                };
                th.start();
            }
        }
    }
    //----init list node----
    public void initListNode(){
        try {
                //            for(int i=0;i<5;i++){
                //                node n = new node(3000+i,"127.0.0.1",i,false);
                //                this.listNode.add(n);
                //            }
                
                node n = new node(3000,"127.0.0.1",0,false);
                node n1 = new node(3002,"127.0.0.1",1,false);
                node n2 = new node(3003,"127.0.0.1",2,false);
                node n3 = new node(3004,"127.0.0.1",3,false);
                node n4 = new node(3005,"127.0.0.1",4,false);
                this.listNode.add(n);
                this.listNode.add(n1);
                this.listNode.add(n2);
                this.listNode.add(n3);
                this.listNode.add(n4);
        } catch (Exception e) {
            System.out.println("error - init list node");
            System.out.println(e.getMessage());
        }
    }    
    //----get coordiantor----
    public node getCoordinator(){
        node max=null;
        for(node i:listNode){
            if(i.isIsAdmin()){
                return i;
            }
            if(i.getId()>Id){
                try {
                    Socket sk = new Socket(i.getHost(),i.getPort());
                    max=i;
                    sk.close();
                } catch (Exception e) {}
            }
        }
        if(max != null){
            node tmp = max;tmp.setIsAdmin(true);
            listNode.set(listNode.indexOf(max), tmp);
            return max;
        }
        return null;
    }
    //----
    public void checkCoordinator(String msg){
        node n = getCoordinator();
        if(n == null){
            System.out.println("checkCoordinator-can't find Coordinator");
            return;
        }
        try {
            //System.out.println(n.getHost() + n.getPort());
            Socket socket = new Socket(n.getHost(),n.getPort());
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeUTF("check-"+Id+"-"+msg);
            System.out.println("checkCoordinator send - "+msg);
            String rev = reader.readUTF();
            System.out.println("checkCoordinator rev-"+rev);
            LogContain+=getCurrentTime()+":"+n.getId()+":"+msg+"\n";
            tpSettext(textPane,getCurrentTime()+":"+n.getId()+": "+rev);
            tpSetMessage(tpChatbox, msg,0);
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("checkCoordinator-can't create socket to server");
            //0
            bully(0);
            //----server khong phan hoi, bat dau chay tien trinh bully----
            return;
        }
    }
//--- text panel settext
    public void tpSettext(JTextPane pane,String txt){
        String current = pane.getText();
        pane.setText(txt+"\n"+current);
        LogContain+=txt+"\n";
    }
    public void tpSetMessage(JTextPane pane,String msg,int opt){
        if(opt==0){
            msgText+="<h3 style=\"margin-left:150px;background-color:#0984e3;color:#fff;padding:5px;\">"+msg+"</h3>";
        }else{
            msgText+="<h3 style=\"margin-right:150px;background-color:#636e72;color:#fff;padding:5px;\">"+msg+"</h3>";
        }
        pane.setText("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<style>body {color: #fff;}</style></head>\n"+"<body>\n" +
                    msgText+"</body>\n"+"</html>");
    }
    public void WriteLog(String filename) throws IOException{
        //File f = new File("C:\\Users\\Admin\\OneDrive\\Documents\\java_netbeans\\MulticartSocket\\src\\main\\java\\log\\"+filename);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Admin\\OneDrive\\Documents\\java_netbeans\\MulticartSocket\\src\\main\\java\\log\\"+filename,true));
            pw.println(LogContain);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//--- get current time
    public String getCurrentTime(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime date = LocalDateTime.now();
        return(String.valueOf(format.format(date)));
    }
//-----------thread receive
    public void receive(Socket socket){
        Thread th = new Thread(){
          public void run(){
            try {
            DataInputStream reader = new DataInputStream(socket.getInputStream());
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            //while(true){
                String msg = reader.readUTF();
                String [] list = msg.split("-");
                switch(list[0]){
                    case "check":
                        tpSettext(textPane,getCurrentTime()+":"+list[1]+": "+msg);
                        //---check xem m co phai dieu phoi vien khong
                        if(getCoordinator().getId() == Id){
                            if(Integer.parseInt(list[1])<Id){
                                tpSetMessage(tpChatbox,list[1]+": "+list[2],1);
                                System.out.println("receive from client: "+msg);
                                writer.writeUTF("response from server");
                                for(node i:listNode){
                                    if(i.getId()<Id && i.getId()!= Integer.parseInt(list[1])){
                                        try {
                                            Socket msgSocket = new Socket(i.getHost(),i.getPort());
                                            DataOutputStream w = new DataOutputStream(msgSocket.getOutputStream());
                                            w.writeUTF("message-"+list[1]+"-"+list[2]);
                                            w.close();
                                            msgSocket.close();
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                                return;
                            }
                            else{
                                System.out.println("id moi vao co id lon hon coor");
                                writer.writeUTF("response from server");
                                
                                listNode.forEach(item->{
                                    //-----setting la server da tro lai---
                                    if(item.getId() == Integer.parseInt(list[1])){
                                        node tmpnode = item;
                                        tmpnode.setTimeoout(true);
                                        listNode.set(listNode.indexOf(item), tmpnode);
                                    }
                                });
                                bully(1);
                                return;
                            }
                        }
                        else{
                            System.out.println("ban k phai dieu phoi vien nma nhan dc check");
                            bully(0);
                            return;
                            //---bau chon bully tai day---
                        }
                    case "election":
                        tpSettext(textPane,getCurrentTime()+":"+list[1]+": "+msg);
                        writer.writeUTF("answer-"+Id);
                        try {
                            node n=null;
                            for(node item : listNode){
                                if(item.getId() == Integer.parseInt(list[1])){
                                    n=item;
                                    break;
                                }
                            }
                            if(n!=null){
                                Socket sk = new Socket(n.getHost(),n.getPort());
                                DataOutputStream output = new DataOutputStream(sk.getOutputStream());
                                output.writeUTF("answer-"+Id);
                                output.close();
                                sk.close();
                                System.out.println("Gui phan hoi election thanh cong");
                            }
                            int max =n.getId();
                            for(node item:listNode){
                                if(item.getId()>n.getId() && item.getId()<Id){
                                    try {
                                        Socket sk = new Socket(item.getHost(),item.getPort());
                                        max=item.getId();
                                        sk.close();
                                    } catch (Exception e) {            
                                    }
                                }
                            }
                            System.out.println("max bang: "+max);
                            if(max == n.getId()){
                                bully(2);
                            }
                            
                        } catch (Exception e) {
                            System.out.println("election-answer-fail");
                        }
                        //tao socket gui answer
                        //bully(2);
                        return;
                    case "answer":
                        tpSettext(textPane,getCurrentTime()+":"+list[1]+": "+msg);
                        System.out.println("Da nhan duoc cau tra loi");
                        break;
                    case "coordinator":
                        tpSettext(textPane,getCurrentTime()+":"+list[1]+": "+msg);
                        //--- set lai dieu phoi vien ---
                        System.out.println("receive-coordinator "+msg);
                        int t=0,f=0;
                        for(node n:listNode){
                            if(n.getId() == Integer.parseInt(list[1])){
                                t=listNode.indexOf(n);
                            }
                            if(n.isIsAdmin()){
                                f=listNode.indexOf(n);
                            }
                        }
                        node ntmp = listNode.get(f);ntmp.setIsAdmin(false);
                        listNode.set(f, ntmp);
                        ntmp = listNode.get(t);ntmp.setIsAdmin(true);
                        listNode.set(t, ntmp);
                        tfMsg.setText("");
                        tfMsg.setEditable(true);
                        btnCheckCoor.setEnabled(true);
                        //flagBully=false;
                        JOptionPane.showMessageDialog(mainFrame, "Coordinator có id: "+list[1]);
                        break;
                    case "message":
                        tpSetMessage(tpChatbox, list[1]+" : "+list[2], 1);
                        break;
                    default:
                        break;
                }
                reader.close();
                writer.close();
                socket.close();
            //}
        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                System.out.println("Ngat ket noi voi server");
            }
        }
        }   
          };
        th.start();
        }
    //---end class
}

