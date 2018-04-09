/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendfile;

import java.io.File;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rmi.interfaces.ServerRmi;
import send.or.receive.file.ReceiveFile;
import send.or.receive.file.SendFile;
import send.or.receive.file.SendOrReceive;

/**
 * FXML Controller class
 *
 * @author Altysh
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button send;
    @FXML
    Button recive;
    @FXML
    Text filename;   
    @FXML
    Button refuse;
    FileChooser fileChooser = new FileChooser();
    Stage stage;
    ServerRmi server;
    File f;
    String filen;
    static int port = 5001;
    SendOrReceive sendOrReceive;
 
 
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ServerRmi getServer() {
        return server;
    }

    public void setServer(ServerRmi server) {
        this.server = server;
    }
   @FXML
    void sendFile()
    {
     f=   fileChooser.showOpenDialog(stage);
      if(f!=null)
      {
          try {

              server.sendRequstFile(f.getName(), f.length(), "hesham", "hesham");
          } catch (RemoteException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
   public void recivenoti(String fileName, long fileSize, String toMail, String fromMail)
    {
        filename.setText(fileName +" "+ (fileSize/(1024*1024)+" MB " +fromMail));
        recive.setDisable(false);
        refuse.setDisable(false);
        filename.setVisible(true);
    }
  public void send(String mail, String ip, int port, String file)
    {Platform.runLater(new Runnable() {
            @Override
            public void run() {
                sendOrReceive.SendFile(port,FXMLController.this.f , ip);
//                new SendFile(port,FXMLController.this.f , ip);
            }
        });
        
    }
    @FXML
    void Recive()
    {
        refuse.setDisable(true);
        f=   fileChooser.showSaveDialog(stage);
        if(f!=null)
        {
            port++;
            System.out.println("from f!=null");
            System.out.println(port);
            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                sendOrReceive.ReceiveFile(port, f);                   
//                    rec.getProgressBar(progressBar);
//                 new ReceiveFile(port , f);
                
            }
        });
           try {
               
            server.acceptFile(Inet4Address.getLocalHost().getHostAddress(), port, "", "hesham");
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 

        }
        
        
        System.out.println("after inside receive runnable");
        
//            server.acceptFile(InetAddress.getLocalHost().toString(), 5000, filen, "hesham");
    }
    
    @FXML
    public void refuse(){
        try {
            recive.setDisable(true);
            filename.setText("File declined !");
            server.refuseFile("ahmed" , "ahmed" , "");
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRefused(){
        filename.setText("File declined !");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO4
         sendOrReceive = new SendOrReceive();

                 
    }    

    
    
}
