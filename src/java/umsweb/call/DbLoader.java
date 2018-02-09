/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.call;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import org.bson.types.ObjectId;
import umsweb.entities.UserMeetPeople;
import umsweb.entities.UserMeetPeopleServer;

/**
 *
 * @author PC
 */
public class DbLoader {

    private DBCollection table;
    private String ip, database, collection;
    private int port;

    public DbLoader() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("db.properties");
            prop.load(input);
            ip = prop.getProperty("ip");
            database = prop.getProperty("database");
            collection = prop.getProperty("collection");
            port = Integer.parseInt(prop.getProperty("port"));
            Mongo mongo = new Mongo(ip, port);
            DB db = mongo.getDB(database);
            this.table = db.getCollection(collection);
        } catch (IOException ex) {
            
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void updateUser(UserMeetPeople user) {
        BasicDBObject objectSearch = new BasicDBObject().append("_id", new ObjectId(user.getUserId()));
        BasicDBObject newDocument = new BasicDBObject();
//        newDocument.app("region", user.getRegion());
        newDocument.append("$set", new BasicDBObject().append("region", user.getRegion())
                .append("video_call_waiting", user.getVideoCallWaiting())
                .append("voice_call_waiting", user.getVoieCallWaiting())
                .append("user_name", user.getUserName())
                .append("gender", user.getGender()));
        try {
            this.table.update(objectSearch, newDocument);
        } catch (Exception e) {
            
        }
    }

    public UserMeetPeopleServer findUserOnDb(UserMeetPeople user) {
        
        return null;
    }
    
    public static void main(String[] args) {
        try {
            Mongo mongo = new Mongo("10.64.100.80", 27017);
            List<String> dbs = mongo.getDatabaseNames();
            DB db = mongo.getDB("userdb");
            for (String temp : db.getCollectionNames()) {
                System.out.println(temp);
            }
            DBCollection table = db.getCollection("user");
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", new ObjectId("577db7520cf275d7466e71c3"));
            DBCursor dBCursor = table.find(searchQuery);
            System.out.println(dBCursor.size());
            while(dBCursor.hasNext()) {
                DBObject ob = dBCursor.next();
                System.out.println("name: " + ob.get("user_name"));
            }
        } catch (UnknownHostException e) {

        }

        DbLoader db = new DbLoader();
        UserMeetPeople us = new UserMeetPeople();
        us.setUserId("57aaa29de4b04addedbb15f8");
        us.setVideoCallWaiting(Boolean.FALSE);
        us.setVoieCallWaiting(Boolean.FALSE);
        us.setUserName("taina");
        us.setGender(1L);
        db.updateUser(us);
    }

    public DBCollection getTable() {
        return table;
    }

    public void setTable(DBCollection table) {
        this.table = table;
    }

}
