/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_db_interface;

import java.awt.Image;
import objRmi.GroupChat.GroupChat;

/**
 *
 * @author Aya
 */
public interface GChat_Interface {

    int searchGid(String AdminMail, String GName);

    boolean addGroup(String GName, String AdminMail, String[] CMails);

    GroupChat getGChat(int Gid);

    String getAdminMail(int Gid);

    int addMember(String[] CMail, int Gid);

    int removeMember(String[] CMail, int Gid);

    void changeGProp(String GName, Image GImg);

    void removeGroup(int Gid);

}
