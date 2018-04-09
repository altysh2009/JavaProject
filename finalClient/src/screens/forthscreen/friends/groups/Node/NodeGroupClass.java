/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.groups.Node;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
/**
 *
 * @author Aya
 */
public class NodeGroupClass extends Application{
       private FXMLLoader loader;
        private Parent root;
        NodeFileGroupController controller;
        HBox hBoxCon;
        
    /**
     *
     * @param stage primary 
     * 
     * 
     */
    @Override
    public void start(Stage stage) {
           try {
               // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
               loader = new FXMLLoader(getClass().getResource("NodeFileGroup.fxml"));
               controller = new NodeFileGroupController();
               controller = loader.getController();
               root = loader.load();
               Scene scene = new Scene(root);
               //HBox hBoxCon=controller.getNode();
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
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
