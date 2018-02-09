/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.entities;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class UserInfor implements Serializable {
    public String userId;
    public int point;
    public int gender;
    public boolean havePurchased;
    public String applicationId;
    
    public UserInfor() {
        
    }
    
    public UserInfor(String userId, int point, int gender, boolean havePurchased) {
        this.userId = userId;
        this.point = point;
        this.gender = gender;
        this.havePurchased = havePurchased;
    }
    public UserInfor(String userId, int point, int gender, boolean havePurchased,String applicationId) {
        this.userId = userId;
        this.point = point;
        this.gender = gender;
        this.havePurchased = havePurchased;
        this.applicationId = applicationId;
    }

    public int getPoint() {
        return point;
    }

    public int getGender() {
        return gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isHavePurchased() {
        return havePurchased;
    }

    public void setHavePurchased(boolean havePurchased) {
        this.havePurchased = havePurchased;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
