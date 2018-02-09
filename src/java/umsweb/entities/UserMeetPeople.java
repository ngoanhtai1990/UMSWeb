/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.entities;

import java.io.Serializable;
import java.util.Objects;
import org.json.simple.JSONObject;

/**
 *
 * @author PC
 */
public class UserMeetPeople implements Serializable {
//<editor-fold defaultstate="collapsed" desc="comment">

    /*
    "region": 11,
    "is_online": false,
    "video_call_waiting": false,
    "body_type": 10,
    "is_avatar": 0,
    "voice_call_waiting": false,
    "call_waiting": 0,
    "long": 139.693772988524,
    "last_login": null,
    "user_name": "",
    "age": 23,
    "gender": 0,
    "user_id": "5779169e0cf2f66d6719ae85",
    "lat": 35.7851428119799
     */
//</editor-fold>
    public UserMeetPeople(JSONObject jo) {
        this.region = (Long) jo.get("region");
        this.online = (boolean) jo.get("is_online");
        this.videoCallWaiting = (boolean) jo.get("video_call_waiting");
        this.bodyType = (Long) jo.get("body_type");
        this.isAvatar = (Long) jo.get("is_avatar");
        this.voieCallWaiting = (boolean) jo.get("voice_call_waiting");
        this.callWaiting = (Long) jo.get("call_waiting");
        this.lon = (Double) jo.get("long");
        this.lastLogin = (String) jo.get("last_login");
        this.userName = (String) jo.get("user_name");
        this.age = (Long) jo.get("age");
        this.gender = (Long) jo.get("gender");
        this.userId = (String) jo.get("user_id");
        this.lat = (Double) jo.get("lat");
        this.status = true;
    }
    private Long region;
    private boolean online;
    private boolean videoCallWaiting;
    private Long bodyType;
    private Long isAvatar;
    private boolean voieCallWaiting;
    private Long callWaiting;
    private Double lon;
    private String lastLogin;
    private String userName;
    private Long age;
    private Long gender;
    private String userId;
    private Double lat;
    private Boolean status;
    
    public UserMeetPeople(Long region,
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
        this.region = region;
        this.online = isOnline;
        this.videoCallWaiting = videoCallWaiting;
        this.bodyType = bodyType;
        this.isAvatar = isAvatar;
        this.voieCallWaiting = voieCallWaiting;
        this.callWaiting = callWaiting;
        this.lon = lon;
        this.lastLogin = lastLogin;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.userId = userId;
        this.lat = lat;
    }

    public UserMeetPeople() {
        this.region = 0L;
        this.online = false;
        this.videoCallWaiting = false;
        this.bodyType = 0L;
        this.isAvatar = 0L;
        this.voieCallWaiting = false;
        this.callWaiting = 0L;
        this.lon = 0.0;
        this.lastLogin = "";
        this.userName = "";
        this.age = 0L;
        this.gender = 0L;
        this.userId = "";
        this.lat = 0.0;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Boolean getVideoCallWaiting() {
        return videoCallWaiting;
    }

    public void setVideoCallWaiting(Boolean videoCallWaiting) {
        this.videoCallWaiting = videoCallWaiting;
    }

    public Long getBodyType() {
        return bodyType;
    }

    public void setBodyType(Long bodyType) {
        this.bodyType = bodyType;
    }

    public Long getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Long isAvatar) {
        this.isAvatar = isAvatar;
    }

    public Boolean getVoieCallWaiting() {
        return voieCallWaiting;
    }

    public void setVoieCallWaiting(Boolean voieCallWaiting) {
        this.voieCallWaiting = voieCallWaiting;
    }

    public Long getCallWaiting() {
        return callWaiting;
    }

    public void setCallWaiting(Long callWaiting) {
        this.callWaiting = callWaiting;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
//        if (obj == null) {
//            return true;
//        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserMeetPeople other = (UserMeetPeople) obj;
//        if (this.online != other.online) {
//            return false;
//        }
//        if (this.videoCallWaiting != other.videoCallWaiting) {
//            return false;
//        }
//        if (this.voieCallWaiting != other.voieCallWaiting) {
//            return false;
//        }
//        if (!this.lastLogin.contains(other.lastLogin)) {
//            return false;
//        }

        if ((other.userName != null || !other.userName.trim().isEmpty()) && !this.userName.contains(other.userName)) {
            return false;
        }

        if ((other.userId != null || !other.userId.trim().isEmpty()) && !this.userId.contains(other.userId)) {
            return false;
        }

        if (other.region != null && !Objects.equals(this.region, other.region)) {
            return false;
        }
        if (other.bodyType != null && !Objects.equals(this.bodyType, other.bodyType)) {
            return false;
        }
        if (other.isAvatar != null && !Objects.equals(this.isAvatar, other.isAvatar)) {
            return false;
        }
        if (other.callWaiting != null && !Objects.equals(this.callWaiting, other.callWaiting)) {
            return false;
        }
        if (other.lon != null && !Objects.equals(this.lon, other.lon)) {
            return false;
        }
        if (other.age != null && !Objects.equals(this.age, other.age)) {
            return false;
        }
        if (other.gender !=null && !Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (other.lon != null && !Objects.equals(this.lat, other.lat)) {
            return false;
        }
        return true;
    }

    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("email", this.userId);
        jo.put("region", region);
        jo.put("body_type", bodyType);
        jo.put("is_avatar", isAvatar);
        jo.put("lon", lon);
        jo.put("lat", lat);
        jo.put("last_login", lastLogin);
        jo.put("call_waiting", callWaiting);
        jo.put("is_online", online);
        jo.put("user_name", userName);
        jo.put("avatar_id", "");
        jo.put("video_call_waiting", videoCallWaiting);
        jo.put("voice_call_waiting", voieCallWaiting);
        jo.put("age", age);
        return jo;
    }
}
