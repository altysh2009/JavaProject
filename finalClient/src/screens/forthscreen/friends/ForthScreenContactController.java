/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends;

import chathandler.ChatHandler;
import chatobj.ChatManger;
import dtopkg.UserDTO;
import java.io.ByteArrayInputStream;
import java.util.logging.Level;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import objRmi.Reqests.RequsetsObj;
import objRmi.client.Client;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

import screens.beforeFifthScreen.BeforeFifthScreenController;
import screens.chat.ChatController;
import screens.firstscreen.img.FirstScreenImg;
import screens.forthscreen.friends.addFriends.Node.NodeAddFriendsController;
import screens.secondscreen.login.loginController;

/**
 *
 * @author Aya
 */
public class ForthScreenContactController implements Initializable {

    @FXML
    private ImageView imageViewProf;
    @FXML
    private Label labelName;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonGroups;
    @FXML
    private Button buttonContacts;
    @FXML
    private Button buttonFriendRequest;
    @FXML
    private Button buttonLogout;
    @FXML
    private Button buttonAddFriend;
    @FXML
    private ImageView image;

    /**
     *
     */
    @FXML
    public ListView listViewAll;
    @FXML
    private Button buttonCreate;
    ObservableList<Client> list;
    ObservableList<Client> list3;
    ObservableList<RequsetsObj> list2;
    Client clientObj;
    long id;
    UserDTO userDto;
    String name;
    ServerRmi serverObj;
    ArrayList<Client> allClients;
    ArrayList<Client> allClient;
    ArrayList<RequsetsObj> allClientsSend;
    ArrayList<RequsetsObj> allClientsReq;
    ArrayList<String> user;
    FactoryAddFriend facAddFriend;
    FactoryReq facReq;
    FactoryClients facClient;
    FactoryAddToGroup facAddGroup;
    NodeAddFriendsController obj;
    ObservableList<RequsetsObj> observ2;
    ClientImpl client;
    private static Stage primaryStage = ForthScreenContacts.getPrimary();
    // ClientDao clientFn;

    /**
     *
     * @param userDtoCop object of userDto contain info f client
     * @param rmi object of sever rmi
     * @param client object of client implementation which is contain methods of
     * implementation
     */
    public ForthScreenContactController(UserDTO userDtoCop, ServerRmi rmi, ClientImpl client) {
        userDto = userDtoCop;
        name = userDto.GetUserEmail();
        serverObj = rmi;
        this.client = client;

    }

    /**
     *
     * this method used to create group using startGroupChat method to start
     * group chat by having mail of sender and mails of receivers and we shift
     * to chat screen
     */
    @FXML
    void createGroup() {

        try {
            id = serverObj.stratGroupChat(userDto.GetUserEmail(), user);
        } catch (RemoteException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(id);
        System.out.println(user);
        if (user.size() > 0) {

            try {
                ChatHandler ch = ChatHandler.getDeFultChatHandler();
                ch.addChat(new ChatManger(user, user.toString()));
                ch.getChat(user).setId(id);
                primaryStage = FirstScreenImg.getPrimary();
                ChatManger chat = new ChatManger(user, list.toString());
                chat.setId(id);
                ch.addChat(chat);
                FXMLLoader loader = new FXMLLoader(new File("./src/screens/chat/chat.fxml").toURL());
                ChatController chatController = new ChatController(id);
                loader.setController(chatController);
                chatController.setServer(serverObj);
                chatController.setUserName(userDto.GetUserEmail());
                client.setChat(chatController);
                loader.load();
                Parent p = loader.getRoot();
                primaryStage.setScene(new Scene(p));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     * we use this method to initialize controller and create array list to
     * contain mails of receivers
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new ArrayList<>();
        labelName.setText(userDto.GetUserName());

        Image img = new Image(new ByteArrayInputStream(userDto.GetUserPic()));

        image.setImage(img);
        setContactListScreen();
    }

    /**
     *
     * used to move to profile screen by setting controller on loader and load
     * loader using primary stage
     */
    @FXML
    void goToProfile() {
        try {
            primaryStage = FirstScreenImg.getPrimary();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../../beforeFifthScreen/beforeFifthScreenEditFXML.fxml"));
            // System.out.println(name+"  namer");
            // userDto=clientFn.getUserDTO(name);
            // System.out.println(userDto);
            BeforeFifthScreenController controller = new BeforeFifthScreenController(userDto, serverObj, client);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * used to load contact list node on list view in forth screen by using
     * getContactList method
     */
    @FXML
    void setContactListScreen() {
        try {
            allClients = serverObj.getContactList(name);
            System.out.println("array clients" + allClients);
        } catch (RemoteException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println("jkhkjhj");
        facClient = new FactoryClients(userDto.GetUserEmail(), serverObj, client);
        listViewAll.cellFactoryProperty().set(facClient);

        // listViewAll.setCellFactory(facClient);
        ObservableList<Client> observ = FXCollections.observableArrayList();
        listViewAll.setItems(observ);
        observ.addAll(allClients);

    }

    /**
     *
     * used to add new group by using getcontactlist method to get contact list
     * of someone and put it into array to add new group
     */
    @FXML
    void goToAddGroup() {
        try {
            allClient = serverObj.getContactList(name);

            System.out.println("array" + allClients);
        } catch (RemoteException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("jkhkjhj");
        facAddGroup = new FactoryAddToGroup(userDto.GetUserEmail(), user);
        listViewAll.setCellFactory(facAddGroup);
        ObservableList<Client> observ = FXCollections.observableArrayList();
        listViewAll.setItems(observ);
        observ.addAll(allClient);

    }

    /**
     * this method used to get requests of client by using getRecController
     * method
     *
     */
    @FXML
    void sendRequest() {

        try {
            //obj = new NodeAddFriendsController(userDto);

            // System.out.println(obj);
            allClientsReq = serverObj.getRecReq(name);
            System.out.println(name + "mail of requests");
            System.out.println("req array" + allClientsReq);
        } catch (RemoteException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("here factory req");
        observ2 = FXCollections.observableArrayList();
        facReq = new FactoryReq(userDto.GetUserEmail(), observ2, serverObj);
        listViewAll.setCellFactory(facReq);
        listViewAll.setItems(observ2);
        observ2.addAll(allClientsReq);
        System.out.println("after added");

    }

    /**
     * this method used to logout from application using setofflineclient method
     * from server rmi class
     *
     */
    @FXML
    void logOut() {
        try {
            primaryStage = FirstScreenImg.getPrimary();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../../secondscreen/login/loginFXML.fxml"));
            serverObj.setOfflineClient(name);
            // System.out.println(userDto.GetUserStatus());
            loginController controller = new loginController(serverObj);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * this method used to add more contacts to profile someone by checking send
     * request and send requests to clients which is choosen
     *
     */
    @FXML
    void goToAdd() {
        allClient = new ArrayList<>();
        allClientsSend = new ArrayList<>();
        try {

            allClients = serverObj.getAvailableUsers();
            System.out.println(allClients + "all array");
            if (serverObj.getSendReq(name) != null) {
                allClientsSend = serverObj.getSendReq(name);
                System.out.println(allClientsSend + "exist array");
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(allClients);
        for (int loop = 0; loop < allClients.size(); loop++) {
            if (allClients.get(loop).getEmail().equals(userDto.GetUserEmail())) {
                allClients.remove(loop);
                System.out.println("heeee");
            }

        }
        for (int outer = 0; outer < allClient.size(); outer++) {
            for (int inner = 0; inner < allClientsSend.size(); inner++) {
                if (allClient.get(outer).getEmail().equals(allClientsSend.get(inner).getClient().getEmail())) {
                    allClient.remove(outer);
                }
            }
        }
        facAddFriend = new FactoryAddFriend(userDto.GetUserEmail(), serverObj);
        listViewAll.setCellFactory(facAddFriend);
        ObservableList<Client> observ = FXCollections.observableArrayList();
        listViewAll.setItems(observ);
        observ.addAll(allClients);

    }

}
