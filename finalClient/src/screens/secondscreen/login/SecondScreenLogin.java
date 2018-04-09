/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.secondscreen.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objRmi.client.Client;
import rmi.interfaces.ServerRmi;

/**
 *
 * @author Aya
 */
public class SecondScreenLogin extends Application { //implements screenLogin {
    private FXMLLoader loader;
    private loginController controller;
    private Parent root;
    String mail;
    String password;
    boolean result;
    private static Stage primary;
    Client client;
    ServerRmi server;

    /**
     *
     * @param server object of server rmi
     */
    public SecondScreenLogin(ServerRmi server)
    {
        this.server=server;
    }
    
     /**
     *
     * used to start login screen
     * @param stage primary
     * @throws java.lang.Exception no need
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        primary=stage;
        //root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));
        loader = new FXMLLoader(getClass().getResource("loginFXML.fxml"));
        controller = new loginController(server);
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

  
    }
    
    /**
     *
     * @return  Stage primary
     */
    public static Stage getPrimary ()
    {
     return (primary);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(SecondScreenLogin.class, new String[0]);
    }

    
}
