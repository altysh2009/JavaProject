/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtopkg;

import javafx.scene.image.Image;

/**
 *
 * @author Hagar
 */
public class UserGroupsDTO {

    private UserDTO user = new UserDTO();
    private String adminMail = user.GetUserEmail();
    private int groupId;
    private Image groupPic;

    /**
     *
     * @param image the group image
     */
    public void SetGroupUmage(Image image) {
        this.groupPic = image;
    }

    
    public Image GetGroupImage() {
        return groupPic;
    }


    public void SetGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     *
     * @return groupId
     */
    public int GetGroupId() {
        return groupId;
    }

    /**
     *
     * @param adminMail the admin of the group
     */
    public void SetGroupAdmin(String adminMail) {
        user.SetUserEmail(adminMail);

    }

    /**
     *
     * @return adminMail
     */
    public String GetGroupAdmin() {
        return adminMail;
    }

}
