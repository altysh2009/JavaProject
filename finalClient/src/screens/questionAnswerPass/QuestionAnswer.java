/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.questionAnswerPass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Aya
 */
public class QuestionAnswer extends Application{
private static Stage primary;
 private FXMLLoader loader;
    private QuestionAnswerController controller;
    private Parent root;
    

    @Override
    public void start(Stage stage) throws Exception {
        primary=stage;
        //root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));
        loader = new FXMLLoader(getClass().getResource("QuestionAnswerFXML.fxml"));
        //controller = new QuestionAnswerController();
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //String pass= controller.returnPass();
//        Mpass m = null;
//        m.setPass(pass);
        
    }

    /**
     *
     * @return Stage primary
     */
    public static Stage getPrimary ()
    {
     return (primary);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(QuestionAnswer.class, new String[0]);
        
    }
    
}
