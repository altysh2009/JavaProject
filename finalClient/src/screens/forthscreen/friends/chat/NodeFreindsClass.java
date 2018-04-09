/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.chat;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *
 * @author Aya
 */
public class NodeFreindsClass extends Application{
       private FXMLLoader loader;
        private Parent root;
        NodeAddFriendsController controller;
        HBox hBoxCon;
        
   /**
     *
     * 
     * 
     * 
     */     
    @Override
    public void start(Stage stage) {
           try {
               // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
               loader = new FXMLLoader(getClass().getResource("NodeFriends.fxml"));
               controller = new NodeAddFriendsController();
               controller = loader.getController();
               root = loader.load();
               Scene scene = new Scene(root);
               //HBox hBoxCon=controller.getNode();
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               System.out.println("screens.forthscreen.friends.chat.NodeFreindsClass.start() error");
               
               Logger.getLogger(NodeFreindsClass.class.getName()).log(Level.SEVERE, null, ex);
               System.exit(0);
           }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
