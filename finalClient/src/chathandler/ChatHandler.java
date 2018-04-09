/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chathandler;

import chatobj.ChatManger;
import chatobj.MessageInfo;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Ahmed
 * used to handle the chat screen and the messages  only created once and return the object always
 */
public class ChatHandler {

    ObservableList<ChatManger> chatList;
    static ChatHandler handler;
    static boolean created = false;
    ListView listMsg;

    /**
     *
     * @param id the id of the chat
     * used to change the chat window when the client touch the item in chatList
     */
    public void setViewById(long id)
    {
       chatList.stream().filter((d)->d.getId()==id).forEach((d)->d.show());
    }
    private ChatHandler() {
        chatList = FXCollections.synchronizedObservableList(FXCollections.observableArrayList());
    }

    /**
     *
     * @return a new object if it was the first time else it return the same object
     */
    public static ChatHandler getDeFultChatHandler() {
        if (created) {
            return handler;
        } else {
            created = true;
            handler = new ChatHandler();
            return handler;
        }

    }

    /**
     *
     * @param id the chat id
     * @param msg the object of MessageInfo
     */
    public void addMsg(long id,MessageInfo msg)
    {
        System.out.println("print");
        for(ChatManger chat:chatList)
        {
          System.out.println(id );
           System.out.println(chat.getId() );
            if(chat.getId()==id)
            {  System.out.println("added");
            chat.addMessage(msg);
            break;
            }
        }
       // chatList.stream().filter((d)->d.getId()==id).forEach((d)->d.addMessage(msg));
    }

    /**
     *
     * @param listChat reference to the chat list
     * @param listMsg  reference to the message list
     * set the reference of the lists to the object on the screen
     */
    public void setListViews(ListView listChat,ListView listMsg)
    {
      listChat.setItems(chatList);
      chatList.stream().forEach((d)->d.setList(listMsg));
      this.listMsg = listMsg;
    }
/**
     *
     * @param id the chat id
     * checks if the chat exit before
     * 
     */
    boolean checkForChat(long id) {
        for(ChatManger m :chatList)
        {
            if(m.getId()==id)
                return true;
        }
        return false;
    }

    /**
     *
     * @param users list of the clients in the chat
     * @param name chat name
     * @param id the i of the chat
     * @return true if the chat was inserted and it didn't exit before else return false
     * 
     */
    public boolean addChat(ArrayList<String> users,String name,long id) {
        if (!checkForChat(id)) {
            ChatManger cha = new ChatManger(users,name);
            cha.setId(id);
            cha.setList(listMsg);
            chatList.add(cha);
            return true;
        }
        return false;

    }

    /**
     *
     * @param chatobj holds the chat information
     * @return true if it was the first else return false
     */
    public boolean addChat(ChatManger chatobj) {
        if (!checkForChat(chatobj.getId())) {
            chatobj.setList(listMsg);
            chatList.add(chatobj);
            return true;
        }
        return false;

    }

    /**
     *
     * @param users list of the client in chat
     * @return returns  Chatmanger that have this users
     */
    public ChatManger getChat(ArrayList<String> users) {
        return chatList.stream().filter(d -> d.getClients().equals(users)).findFirst().get();
    }

    /**
     *
     * @param id chat id
     * @return returns  Chatmanger that have this users
     */
    public ChatManger getChat(long id) {
        return chatList.stream().filter(d -> d.getId()==id).findFirst().get();
    }

    
    
}
