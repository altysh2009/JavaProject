/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatobj;

import chathandler.Compartor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author ahmed
 * Contain the massages and have a reference to the list view and have list of the clients and the id of the chat
 */
public class ChatManger {

    private List<String> clients;
    private ObservableList<MessageInfo> messages;
    private long id;
    private String chatName;
    private ListView<MessageInfo> list;

    /**
     *
     * @param list the list view of the massage on the screen
     * used to set the listView
     */
    public void setList(ListView<MessageInfo> list) {
        this.list = list;
    }

    /**
     *
     * @return the chat name
     */
    public String getChatName() {
        return chatName;
    }

    /**
     *
     * @param chatName the new chat name
     */
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    /**
     *
     * @return the client list
     */
    public List<String> getClients() {
        return clients;
    }

    /**
     *
     * @param clients the new client list
     */
    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    /**
     *
     * @return an ObservableList of the messages
     */
    public ObservableList<MessageInfo> getMessages() {
        return messages;
    }

    /**
     *
     * @param messages sets the messages
     */
    public void setMessages(ObservableList<MessageInfo> messages) {
        this.messages = messages;
    }

    /**
     *
     * @param clientList list of the users
     * @param name chat name
     */
    public ChatManger(ArrayList<String> clientList, String name) {
        // List<Client> clientList=  Arrays.asList(client);
        Collections.sort(clientList, new Compartor());
        clients = Collections.synchronizedList(new ArrayList<>());
        clients.addAll(clientList);
        this.chatName = name;
        messages = FXCollections.synchronizedObservableList(FXCollections.observableArrayList());
    }

    /**
     *
     * @return the chat id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id set the chat id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @param m an object containing the massage and extra info to be used
     */
    public void addMessage(MessageInfo m) {
        if (Platform.isFxApplicationThread()) {
            messages.add(m);
            list.scrollTo(messages.size() - 1);
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    messages.add(m);
                    list.scrollTo(messages.size() - 1);
                }
            });
        }
    }

    /**
     * used to show this chat on the listView
     */
    public void show() {
        list.setItems(messages);
        list.scrollTo(messages.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder bu = new StringBuilder();
        clients.stream().forEach(d -> bu.append(d));

        return bu.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
