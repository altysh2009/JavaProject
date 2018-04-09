    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.or.receive.file;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hesham
 */
public class SendFile {

   
    File myFile = null;
    Socket sock = null;
    byte[] mybytearray = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    DataOutputStream dos;
    
   
           
           

    public SendFile( int port , File pathOfFile,String ip) {
        try {
            sock = new Socket(ip, port);
            myFile = pathOfFile;
            System.out.println("e7na fl try bta3et el send");
            long size = myFile.length();
            System.out.println("File Size = " + size);
            
            
            int count;

            bis = new BufferedInputStream(new FileInputStream(myFile));
//            bis.read(mybytearray, 0, mybytearray.length);
            os = sock.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeLong(size);

//            os.write();
            double ss =0;
            byte[] buffer = new byte[4096];
            while ((count = bis.read(buffer)) > 0) {
                os.write(buffer, 0, count);
                ss =(double)(ss + count);
                System.out.println(ss * 100L / 100);
                
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch from SendFile");
        } finally {
            try {
                os.flush();
                sock.close();
            } catch (Exception e) {

            }
        }
    }
    
    



}
