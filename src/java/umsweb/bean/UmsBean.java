/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.json.simple.parser.ParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import umsweb.call.App;
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
    private UserMeetPeople userOnDB;
    private Map<String, UserMeetPeople> userUpdate;
    private DbLoader dbLoader;
    NetClientPost client;

    public UmsBean() throws ParseException, IOException {
        userSearch = new UserMeetPeople();
        client = new NetClientPost();
//        this.dataUMS = getListDataOnCache();
        this.dataMeet = getDataMeetPeople();
        this.listFiltered = new ArrayList<>(dataMeet);
        userUpdate = new HashMap<>();
        if (listFiltered.isEmpty()) {
            userSelected = new UserMeetPeople();
        } else {
            userSelected = listFiltered.get(0);
        }
        userOnDB = new UserMeetPeople();
        dbLoader = new DbLoader();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            System.out.println(context.getRealPath("/WEB-INF/"));
            InputStream input = new FileInputStream(context.getRealPath("/WEB-INF/") + "db.properties");
            Properties prop = new Properties();
            prop.load(input);
            System.out.println("Load: " + prop.getProperty("ip"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (listFiltered.isEmpty()) {
            //xxx
            userSelected = new UserMeetPeople();
            listFiltered.add(userSelected);
        }
        userSelected = listFiltered.get(0);
    }

    public void deactiveUserMeetPeople() throws ParseException {
        client.removeUser(userSelected.getUserId());
        listFiltered.get(listFiltered.indexOf(userSelected)).setStatus(Boolean.FALSE);
    }

    public void activeUserMeetPeople() throws ParseException {
        listFiltered.get(listFiltered.indexOf(userSelected)).setStatus(Boolean.TRUE);
        client.reActiveUser(userSelected);
    }

    public void updateUser() throws ParseException {
//        userUpdate.put(userSelected.getUserId(), userSelected);
        updateCache();
        updateDatabase();
    }

    public void updateCache() throws ParseException {
        client.reActiveUser(userSelected);
    }

    public void updateDatabase() {
        dbLoader.updateUser(userSelected);
    }

    public void editOnClick() {
        userOnDB = dbLoader.findUserOnDb(userSelected);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').show();");
        System.out.println("Id DB: " + userOnDB.getUserName());
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

    public UserMeetPeople getUserOnDB() {
        return userOnDB;
    }

    public void setUserOnDB(UserMeetPeople userOnDB) {
        this.userOnDB = userOnDB;
    }
}
