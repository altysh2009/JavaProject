/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.Node;

import chathandler.ChatHandler;
import chatobj.ChatManger;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objRmi.client.Client;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;
import screens.chat.ChatController;
import screens.firstscreen.img.FirstScreenImg;
import screens.forthscreen.friends.ForthScreenContactController;
import sendfile.FXMLController;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class NodeFileController implements Initializable {
  @FXML
  private ImageView imageViewNode;
  @FXML
  private Label labelNameNode;
  @FXML
  private Label labelStatuesNode;
  @FXML
  private VBox vBoxLabels;
  @FXML
  private Button send_btn;
   @FXML
  private Button chat;
  private Client c;
  private String sMail;
  private File f;
  private FileChooser fileChooser;
  private Stage stage;
  private ServerRmi server;
   private ClientImpl client;

  public NodeFileController(ServerRmi rmi, ClientImpl client)
  {
      server = rmi;
      this.client = client;
  }
    /**
     *
     * @param c which is client object
     */
    public void setC(Client c) {
        this.c = c;
    }

    /**
     *
     * @return imageview
     */
    public ImageView getImageViewNode() {
        return imageViewNode;
    }

    /**
     *
     * @return label of name
     */
    public Label getLabelNameNode() {
        return labelNameNode;
    }

    /**
     *
     * @return label of statues
     */
    public Label getLabelStatuesNode() {
        return labelStatuesNode;
    }

    /**
     *
     * @return circle which is contain image
     */
    public Circle getShapeNode() {
        return shapeNode;
    }
    
    
  @FXML
  private Circle shapeNode;
      
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        
    }
    
    
    @FXML
    void action()
    {if(c!=null)
        System.out.println(c);
    
    
    }

    /**
     *
     * @param mail set the mail
     */
    public void setSMail(String mail) {
        sMail=mail;
    }
    
    @FXML
    public void send(){
        
         f=   fileChooser.showOpenDialog(stage);
      if(f!=null)
      {
             try {
                 //System.out.println(f.getName()+ f.length()+sMail+c );
                 if(server!=null)
                 server.sendRequstFile(f.getAbsolutePath(), f.length(),sMail , c.getEmail());
             } catch (RemoteException ex) {
                 Logger.getLogger(NodeFileController.class.getName()).log(Level.SEVERE, null, ex);
             }
      }
        
    }
    
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

   @FXML
   private void stratChat()
   {
      try {
          ArrayList<String> list = new ArrayList<>();
          list.add(sMail);
          list.add(c.getEmail());
          
          long id =server.stratGroupChat(sMail, list);
           try {
               System.out.println(id);
               ChatHandler ch = ChatHandler.getDeFultChatHandler();
               ChatManger chat = new ChatManger(list, list.toString());
               chat.setId(id);
               ch.addChat(chat);
               //ch.getChat(list).setId(id);
                stage = FirstScreenImg.getPrimary();
                FXMLLoader loader = new FXMLLoader(new File("./src/screens/chat/chat.fxml").toURL());
                ChatController chatController = new ChatController(id);
                
                loader.setController(chatController);
                chatController.setServer(server);
                chatController.setUserName(c.getEmail());
                client.setChat(chatController);
                loader.load();
                Parent p = loader.getRoot();
                stage.setScene(new Scene(p));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
            }

      } catch (RemoteException ex) {
          Logger.getLogger(NodeFileController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
    
}

