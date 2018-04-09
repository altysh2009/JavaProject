/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.or.receive.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hesham
 */
public class ReceiveFile {

    Socket sock = null;
    InputStream is = null;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    int bytesRead = 0;
    byte[] buffer = null;
 ServerSocket servsock = null;
    
   

    
    public ReceiveFile( int port , File file) {
        try {
            System.out.println("before serverSocket");
            servsock = new ServerSocket(port);
            sock = servsock.accept();
            System.out.println("after server socket");
            is = sock.getInputStream();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            buffer = new byte[4096];
            int count;
             DataInputStream b=  new DataInputStream(sock.getInputStream());
//            int size = b.readInt();
           Long size = b.readLong();
            System.out.println(size);
            while ((count = is.read(buffer)) > 0) {
                bos.write(buffer, 0, count);
                //System.out.println("");
            }

            System.out.println("Received");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch from receiveFile");
        } finally {

            try {
                bos.close();
                sock.close();
            } catch (Exception e) {
                System.out.println("catch from finally");
            }

        }
    }
 
 

 

}
