/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.beforeFifthScreen;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aya
 */
public class beforeFifthScreenEdit extends Application {
     private FXMLLoader loader;
    private BeforeFifthScreenController controller;
    private Parent root;
    private static Stage primary;
    
   /**
 *
 * used to load screen of profile
 */ 
    @Override
    public void start(Stage stage) throws Exception {
        primary=stage;
        loader = new FXMLLoader(getClass().getResource("beforeFifthScreenEditFXML.fxml"));
       // controller = new BeforeFifthScreenController();
        controller = loader.getController();
        root = loader.load();
      
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     *
     * @return primary stage
     */
    public static Stage getPrimary ()
    {
     return (primary);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
