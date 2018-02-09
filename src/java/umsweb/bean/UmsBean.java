/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.ParseException;
import org.primefaces.event.SelectEvent;
import umsweb.call.DbLoader;
import umsweb.call.NetClientPost;
import umsweb.entities.UserInfor;
import umsweb.entities.UserMeetPeople;

/**
 *
 * @author PC
 */
@ManagedBean(name = "umsBean")
@SessionScoped
public class UmsBean {

    private List<UserInfor> dataUMS;
    private List<UserMeetPeople> dataMeet;
    private List<UserMeetPeople> listFiltered;
    private UserMeetPeople userSearch;
    private UserMeetPeople userSelected;
    private Map<String, UserMeetPeople> userUpdate;
    private DbLoader dbLoader;
    
    NetClientPost client;

    public UmsBean() throws ParseException {
        userSearch = new UserMeetPeople();
        client = new NetClientPost();
//        this.dataUMS = getListDataOnCache();
        this.dataMeet = getDataMeetPeople();
        this.listFiltered = new ArrayList<>(dataMeet);
        userUpdate = new HashMap<>();
        userSelected = listFiltered.get(0);
        dbLoader = new DbLoader();
    }

    public final List<UserInfor> getListDataOnCache() throws ParseException {
        List<UserInfor> data = new ArrayList<>();
        try {
            data = client.getDataUms();
        } catch (IOException ex) {
            Logger.getLogger(UmsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void searchMeetPeople() {
        listFiltered.clear();
        for (UserMeetPeople temp : dataMeet) {
            if (temp.equals(userSearch)) {
                listFiltered.add(temp);
            }
        }
    }

    public void deactiveUserMeetPeople() throws ParseException {
        client.removeUser(userSelected.getUserId());
        listFiltered.get(listFiltered.indexOf(userSelected)).setStatus(Boolean.FALSE);
    }

    public void activeUserMeetPeople() throws ParseException {
        listFiltered.get(listFiltered.indexOf(userSelected)).setStatus(Boolean.TRUE);
        client.reActiveUser(userSelected);
    }

    public void updateUser() {
        userUpdate.put(userSelected.getUserId(), userSelected);
    }
    
    public void updateCache() {
        
    }
    
    public void updateDatabase() {
        dbLoader.updateUser(userSelected);
    }
    
    public final List<UserMeetPeople> getDataMeetPeople() {
        List<UserMeetPeople> data = new ArrayList<>();
        try {
            data = client.getDataMeet();
        } catch (IOException e) {
        } catch (ParseException ex) {
            Logger.getLogger(UmsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void onRowSelect(SelectEvent event) {
        userSelected = (UserMeetPeople) event.getObject();
    }
    
    public List<UserInfor> getDataUMS() {
        return dataUMS;
    }

    public void setDataUMS(List<UserInfor> dataCache) {
        this.dataUMS = dataCache;
    }

    public List<UserMeetPeople> getDataMeet() {
        return dataMeet;
    }

//    public void setDataMeet(List<UserMeetPeople> dataMeet) {
//        this.dataMeet = dataMeet;
//    }
    public List<UserMeetPeople> getListFiltered() {
        return listFiltered;
    }

    public void setListFiltered(List<UserMeetPeople> listFiltered) {
        this.listFiltered = listFiltered;
    }

    public UserMeetPeople getUserSearch() {
        return userSearch;
    }

    public void setUserSearch(UserMeetPeople userSearch) {
        this.userSearch = userSearch;
    }

    public UserMeetPeople getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(UserMeetPeople userSelected) {
        this.userSelected = userSelected;
    }
}
