/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.friendstogroup.Node;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
/**
 *
 * @author Aya
 */
public class NodeFreindsGroupClass extends Application{
       private FXMLLoader loader;
        private Parent root;
        NodeAddFriendsToGroupController controller;
        HBox hBoxCon;
        
        
    /**
     *
     * @param stage primary
     * @throws java.lang.Exception throws Exception in case of error
     */
    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        loader = new FXMLLoader(getClass().getResource("NodeFriendsGroup.fxml"));
        //controller = new NodeAddFriendsToGroupController();
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);
        //HBox hBoxCon=controller.getNode();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
