/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.implementation;

import chathandler.ChatHandler;
import chatobj.ChatManger;
import chatobj.MessageInfo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objRmi.client.Client;
import objRmi.massage.Message;
import rmi.interfaces.ClientRmi;
import rmi.interfaces.ServerRmi;
import screens.chat.ChatController;
import com.notification.Notification;
import com.notification.NotificationFactory;
import com.notification.NotificationListener;
import com.notification.manager.QueueManager;
import com.notification.types.IconNotification;
import java.io.File;
import com.theme.ThemePackagePresets;
import com.utils.Time;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import send.or.receive.file.SendOrReceive;

/**
 *
 * @author Hesham
 */
public class ClientImpl extends UnicastRemoteObject implements ClientRmi {

    ServerRmi myServer;
    ArrayList<Client> blockList;
    ArrayList<Client> friendList;
    // int port;
    String ip, pathOfFile;
    ArrayList<String> onlineMails;
    ArrayList<Long> counterList;
    ChatController chat;
    ChatHandler chatHandler;
    static ClientImpl client = null;
    NotificationFactory factory;
    QueueManager manager;
    SendOrReceive receive;
    static int port = 10000;
    private FileChooser fileChooser;
    private Stage stage;
    private String userMail;
    private String sender = null;

    /**
     * 5005
     *
     * @param chat object of ChatController class created by ahmed
     */
    public void setChat(ChatController chat) {
        this.chat = chat;
    }

    /**
     *
     * @param server the object of server's RMI
     * @param st the main stage
     * @param user the user mail
     * @throws RemoteException throws Exception in case connection error
     */
    public ClientImpl(ServerRmi server, Stage st,String user) throws RemoteException {

        myServer = server;
        onlineMails = new ArrayList<>();
        friendList = new ArrayList<>();
        blockList = new ArrayList<>();
        counterList = new ArrayList<>();
        chatHandler = ChatHandler.getDeFultChatHandler();
        factory = new NotificationFactory(ThemePackagePresets.cleanDark());
        manager = new QueueManager(NotificationFactory.Location.NORTHEAST);
        manager.setScrollDirection(QueueManager.ScrollDirection.SOUTH);
        receive = new SendOrReceive();
        fileChooser = new FileChooser();
        stage = st;
        userMail = user;
        
    }

    /**
     *
     * @param msg messege object contains data of the message
     * @param mails ArrayList of mails to send the message to them
     * @throws RemoteException not used , it was just for check
     */
    @Override
    public void receiveMsg(Message msg, ArrayList<String> mails) throws RemoteException {
        // here set the message to UI for this user mails \\

        System.out.println(msg.getText());

    }

    @Override
    public void reqReply(String reply, String mail) throws RemoteException {
//        if (reply.equals("Accept")) {
//            System.out.println(reply);
//            new ReceiveFile();
//        } else {
//            System.out.println("Refused");
//        }
    }

    @Override
    public void sendOfflineNotification(String mail, ArrayList<Client> friends) throws RemoteException {
        String u = new File("./src/notifcatoin/hhh.png").toURI().toString();
        ImageIcon icon;
        try {
            icon = new ImageIcon(new File("./src/notifcatoin/hhh.png").toURL());

            IconNotification note = factory.buildIconNotification("Notification", mail + " is ofline",
                    icon);

            note.setCloseOnClick(true);
            note.addNotificationListener(new NotificationListener() {
                @Override
                public void actionCompleted(Notification ntfctn, String string) {
                    //System.out.println();
                    if (!ntfctn.isManaged()) {
                        System.out.println("clicked");

                    }
                    //System.out.println("hallo");

                }
            });

            // make it show in the queue for five seconds
            manager.addNotification(note, Time.seconds(5));
            // one second delay between creations
            Thread.sleep(1000);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param mail current user mail
     * @return ArrayList of the block list to the current user
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public ArrayList<Client> getBlockList(String mail) throws RemoteException {
        // call the function that get the block list from the server \\
        return blockList;
    }
/////////////////////////////////////////

    @Override
    public void recReqFile(String fileName, long fileSize, String toMail, String fromMail) throws RemoteException {
        if (Platform.isFxApplicationThread()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("File transfare");
            alert.setHeaderText(new File(fileName).getName() + " " + fileSize / (1024 * 1024) + "MB");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                File f = fileChooser.showSaveDialog(stage);

                if (f != null) {

                    try {
                        Platform.runLater(() -> {
                            receive.ReceiveFile(port, f);
                            showNoti(new File(fileName).getName() + " recived");
                        }
                        );

                        myServer.acceptFile(Inet4Address.getLocalHost().getHostAddress(), port, fileName, fromMail);
                        port++;
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("File transfareg");
                alert.setHeaderText(new File(fileName).getName() + " " + fileSize / (1024 * 1024) + "MB");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    File f = fileChooser.showSaveDialog(stage);
                    if (f != null) {

                        try {
                            //receive.ReceiveFile(port, f);

                            if (!Platform.isFxApplicationThread()) {
                                Platform.runLater(() -> {
                                    receive.ReceiveFile(port, f);
                                    showNoti(new File(fileName).getName() + " reccvid");
                                });
                            } else {
                                Platform.runLater(() -> {
                                    receive.ReceiveFile(port, f);
                                    showNoti(new File(fileName).getName() + " recevid");
                                });

                            }
                            myServer.acceptFile(Inet4Address.getLocalHost().getHostAddress(), port, fileName, fromMail);

                        } catch (UnknownHostException ex) {
                            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex) {
                            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else {
                    try {
                        myServer.refuseFile(toMail, fileName);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
//////////////////////////////

    @Override
    public void openSocket(String mail, String ip, int port, String file) throws RemoteException {
        Platform.runLater(() -> {
            try {
                Thread.sleep(1000);
                receive.SendFile(port, new File(file), ip);
                showNoti(new File(file).getName() + " sended");

            } catch (InterruptedException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void refused(String mail, String fileName) throws RemoteException {
        showNoti(new File(fileName).getName() + " reffused");
    }

    /**
     *
     * @param msg object of Message contains data of the message
     * @param myMail current client mail
     * @param myFriendMail other client mail
     * @throws RemoteException throws Exception in connection error
     */
    @Override
    public void receivePrivateMsg(Message msg, String myMail, String myFriendMail) throws RemoteException {

        // here is the logic that I thought about in private chat handling \\
        try {
            friendList = myServer.getContactList(myMail);
            for (Client client : friendList) {
                if (client.getEmail().equals(myMail)) {
                    System.out.println("Me : " + msg.getText());
                } else if (client.getEmail().equals(myFriendMail)) {
                    System.out.println("My Friend : " + msg.getText());
                }
                System.out.println("from client receive private chat");
            }
        } catch (RemoteException ex) {
            System.out.println("Catch from receivePrivateMsg from ClientImpl");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param clientRmi the object of Client Rmi of current client
     * @param mail of the current client
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public void removeClient(ClientRmi clientRmi, String mail) throws RemoteException {
        for (int i = 0; i < onlineMails.size(); i++) {
            if (onlineMails.get(i).equals(mail)) {
                onlineMails.remove(i);
            }
        }

    }

    /**
     *
     * @param mail current client mail
     * @throws RemoteException throws Exception connection error
     */
    @Override
    public void setOnlineStatus(String mail) throws RemoteException {
        // here I'll get the client's contact list to send notification for them !! \\
        friendList = myServer.getContactList(mail);
        // here we will send notifications to contactList \\

        //\\
        onlineMails.add(mail);
    }

    /**
     *
     * @param counter the Id of the group chat
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public void myGroupsCounter(long counter) throws RemoteException {
        counterList.add(Long.valueOf(counter));
        ArrayList<String> s = myServer.getGroupMails(counter);
        ChatManger m = new ChatManger(myServer.getGroupMails(counter), s.toString().replace("[ ]", ""));
        m.setId(counter);
        chatHandler.addChat(m);
//        chatHandler.getChat(s).setId(counter);
    }

    /**
     *
     * @return ArrayList of group ids
     */
    @Override
    public ArrayList<Long> getMyCounterList() {
        return counterList;
    }

    /**
     *
     * @param msg object of message contains message data
     * @param id of chat
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public void receiveMsg(Message msg, long id) throws RemoteException {
        if (chat != null) {
            chat.reciveMassage(msg, id);
        } else {
            MessageInfo mI = new MessageInfo(msg);
            showNoti(msg.getSender() + " " + msg.getText());
            if(msg.getSender().equals(userMail))
            {
                
                 mI.setDir("right");
            }else{
                  mI.setDir("left");
            }
            if (sender != null && sender.equals(msg.getSender())) {
            mI.setIsFirst(true);
          
        }
           chatHandler.addChat(myServer.getGroupMails(id), myServer.getGroupMails(id).toString(), id);
            chatHandler.getChat(id).addMessage(mI); 
        }
         sender = msg.getSender();

    }

    /**
     *
     * @param onlineFriendName the name of the friend who became online
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public void getLoginNoti(String onlineFriendName) throws RemoteException {

        String u = new File("./src/notifcatoin/hhh.png").toURI().toString();
        ImageIcon icon;
        try {
            icon = new ImageIcon(new File("./src/notifcatoin/hhh.png").toURL());

            IconNotification note = factory.buildIconNotification("Notification", onlineFriendName + " is Online",
                    icon);

            note.setCloseOnClick(true);
            note.addNotificationListener(new NotificationListener() {
                @Override
                public void actionCompleted(Notification ntfctn, String string) {
                    //System.out.println();
                    if (!ntfctn.isManaged()) {
                        System.out.println("clicked");

                    }
                    //System.out.println("hallo");

                }
            });

            // make it show in the queue for five seconds
            manager.addNotification(note, Time.seconds(5));
            // one second delay between creations
            Thread.sleep(1000);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param msg message sent from server to all clients
     * @throws RemoteException throws Exception in case connection error
     */
    @Override
    public void receiveServerNot(String msg) throws RemoteException {

        String u = new File("./src/notifcatoin/hhh.png").toURI().toString();
        ImageIcon icon;
        try {
            icon = new ImageIcon(new File("./src/notifcatoin/hhh.png").toURL());

            IconNotification note = factory.buildIconNotification("Server Announcement", msg,
                    icon);

            note.setCloseOnClick(true);
            note.addNotificationListener(new NotificationListener() {
                @Override
                public void actionCompleted(Notification ntfctn, String string) {
                    //System.out.println();
                    if (!ntfctn.isManaged()) {
                        System.out.println("clicked");

                    }
                    //System.out.println("hallo");

                }
            });

            // make it show in the queue for five seconds
            manager.addNotification(note, Time.seconds(5));
            // one second delay between creations
            Thread.sleep(1000);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void showNoti(String msg) {

        String u = new File("./src/notifcatoin/hhh.png").toURI().toString();
        ImageIcon icon;
        try {
            icon = new ImageIcon(new File("./src/notifcatoin/hhh.png").toURL());

            IconNotification note = factory.buildIconNotification("NotifCattion", msg,
                    icon);

            note.setCloseOnClick(true);
            note.addNotificationListener(new NotificationListener() {
                @Override
                public void actionCompleted(Notification ntfctn, String string) {
                    //System.out.println();
                    if (!ntfctn.isManaged()) {
                        System.out.println("clicked");

                    }
                    //System.out.println("hallo");

                }
            });

            // make it show in the queue for five seconds
            manager.addNotification(note, Time.seconds(5));
            // one second delay between creations
            Thread.sleep(1000);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
