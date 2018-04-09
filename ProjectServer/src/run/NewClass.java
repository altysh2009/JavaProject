/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import dtopkg.UserDTO;
import dao.ClientDao;
import static general_db_interface.Client_Interface.PICTURE;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import objRmi.client.Client;
import oracle.jdbc.OracleDriver;
import javax.sql.rowset.serial.SerialBlob;


/**
 *
 * @author ahmed
 */
public class NewClass extends Application{
    public static void main(String[] args) {
        try {
            System.out.println("feijf");
            ClientDao c = ClientDao.getDeflutClientDoa();
           // Connection con;
          //  DriverManager.registerDriver(new OracleDriver());
           // con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
           // c.setCon(con);
            //c.createTable(new UserDTO());
//            Blob b = con.createBlob();
//            b.setBinaryStream(0).write(convertFileContentToBlob("pic.jpg"));
            
            
            
          
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

//                InputStream in = b.getBinaryStream();
                byte[] buf = new byte[1024];
                int n = 0;
//                while ((n = in.read(buf)) >= 0) {
//                    baos.write(buf, 0, n);
//
//                }
//
//                in.close();
//                byte[] bytes = baos.toByteArray();
            
            UserDTO userDTO = new UserDTO("altwefyserhh2ui009@gmail.com",0,0,"egypt","ahmed","12345","BUSY",null,"what is your name","ahmed");
            UserDTO userDT = new UserDTO("altyshewferh27709@gmail.com",0,0,"egypt","ahmed","12345","BUSY",null,"what is your name","ahmed");
            UserDTO userTO = new UserDTO("altyswefherh20trh]09@gmail.com",0,0,"egypt","ahmed","12345","BUSY",null,"what is your name","ahmed");
            UserDTO userDO = new UserDTO("altywefsherh2hj09@gmail.com",0,0,"egypt","ahmed","12345","BUSY",null,"what is your name","ahmed");           
//c.InsertRow(userDTO);
//c.InsertRow(userDT);
//c.InsertRow(userTO);
//c.InsertRow(userDO);
            //System.out.println(c.login("altysh2009@gmail.com", "1234"));
            //System.out.println(c.getCurrentClient("altysh2009@gmail.com"));
                      //List<Client> cc =  c.getContactList("altysh2009@gmail.com");
 // System.out.println(c.forgetPassword("altysh2009@gmail.com","what is your name"));
          
            //List<Client> ccc = c.getAvailableUsers();
            //System.out.println(c.getContactList("altysh2009@gmail.com"));
             //System.out.println(c.getContactList("yah"));
              //System.out.println(c.getContactList("yaheaa"));
             // c.blockFriend("altysh2009@gmail.com", "yah");
              //c.removeFriend("yah", "altysh2009@gmail.com");
             // c.blockFriend("altysh2009@gmail.com", "yaheaa");
             // System.out.println(c.getContactList("altysh2009@gmail.com"));
              //c.unblockFriend("altysh2009@gmail.com", "yah");
              //System.out.println(c.getBlockedList("altysh2009@gmail.com"));
            //System.out.println(ccc);
            //c.addRecReq("altysh2009@gmail.com", "yaha");
            //c.addSendReq("altysh2009@gmail.com", "yaha");
           // c.getRecReq("altysh2009@gmail.com");
            //System.out.println(c.getSendReq("altysh2009@gmail.com"));
            System.out.println(c.getMale());
            System.out.println(c.getOnlineClient());
            //c.signIn("yahc");
            //c.signOut("yahc");
         //c.AddFriend("altysh2009@gmail.com", "yah");
         //c.AddFriend("altysh2009@gmail.com", "yaheaa");
            
           // System.out.println(c.getStatus("altysh2009@gmail.com"));
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       // launch(new String[]{});//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
