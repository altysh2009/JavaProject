/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.or.receive.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hesham
 */
public class SendOrReceive {

    // send file attributes \\
    File myFile = null;
    Socket sock = null;
    byte[] mybytearray = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    DataOutputStream dos;
    //\\
    // receive file attribute \\
    Socket sockReceive = null;
    InputStream is = null;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    int bytesRead = 0;
    byte[] buffer = null;
    ServerSocket servsock = null;
    //\\
    double ss = 0;
    double s2 = 0;
    String fileName;
    long fileSize;
    File fileObj;

    /**
     *
     */
    public SendOrReceive() {
        ss = 0;
        s2 = 0;
    }

    /**
     *
     * @param port of server socket
     * @param file data of file to be sent
     * @param ip of the server socket machine
     */
    public void SendFile(int port, File file, String ip) {
        try {

            System.out.println(port+ip);
            
            sock = new Socket(ip, port);
            myFile = file;
            System.out.println("e7na fl try bta3et el send");
            long size = myFile.length();
            System.out.println("File Size = " + size);
            int count;

            bis = new BufferedInputStream(new FileInputStream(myFile));
            os = sock.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeLong(size);

            byte[] buffer = new byte[4096];
            while ((count = bis.read(buffer)) > 0) {
                os.write(buffer, 0, count);
                ss = (double) (ss + count);
                System.out.println(ss * 100.0 / size);
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

    /**
     *
     * @param port of the server socket
     * @param file the place where the file will be saved in
     */
    public void ReceiveFile(int port, File file) {
        try {

            System.out.println("before serverSocket");
            System.out.println(port);
            servsock = new ServerSocket(port);
            sockReceive = servsock.accept();
            System.out.println("after server socket");

            is = sockReceive.getInputStream();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer2 = new byte[4096];
            int count;
            DataInputStream b = new DataInputStream(sockReceive.getInputStream());
            Long size = b.readLong();
            System.out.println(size);
            while ((count = is.read(buffer2)) > 0) {
                bos.write(buffer2, 0, count);
                s2 = (double) (s2 + count);
                System.out.println(s2 * 100.0 / size);

            }

            System.out.println("Received");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catch from receiveFile");
        } finally {

            try {
                bos.close();
                sockReceive.close();
            } catch (Exception e) {
                System.out.println("catch from finally");
            }

        }

    }

}
