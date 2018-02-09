/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.entities;

import java.io.Serializable;
import org.json.simple.JSONObject;

/**
 *
 * @author PC
 */
public class UserMeetPeopleServer extends UserMeetPeople implements Serializable {
    private int showme;
    private Double distance;
    private String avatarId;

    public UserMeetPeopleServer(int showme,
            Double distance,
            String avatarId,
            Long region,
            Boolean isOnline,
            Boolean videoCallWaiting,
            Long bodyType,
            Long isAvatar,
            Boolean voieCallWaiting,
            Long callWaiting,
            Double lon,
            String lastLogin,
            String userName,
            Long age,
            Long gender,
            String userId,
            Double lat) {
        super(region, isOnline, videoCallWaiting, bodyType, isAvatar, voieCallWaiting, callWaiting, lon, lastLogin, userName, age, gender, userId, lat);
        this.showme = showme;
        this.distance = distance;
        this.avatarId = avatarId;
    }

    public int getShowme() {
        return showme;
    }

    public void setShowme(int showme) {
        this.showme = showme;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }
    
    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        
        return jo;
    }
}
