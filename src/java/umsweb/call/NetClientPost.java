/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsweb.call;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import umsweb.entities.UserInfor;
import umsweb.entities.UserMeetPeople;


/**
 *
 * @author PC
 */
public class NetClientPost {
    
    public String url = "http://10.64.100.80:9119";
//    public String url = "http://10.64.100.22:9119";
    
    public String umsPostParam = "{\"api\":\"get_user_inf_demo\"}";
    public String meetPeoplePostParam = "{\"api\":\"get_user_meet_people\"}";
    public String userIdRemoveApi = "\"api\":\"remove_user_on_meet_people\",";
    public String reactiveUser = "reactive_from_web";
    
    public List<UserInfor> getDataUms() throws IOException, ParseException {
        String jsonString = getJSonData(url, umsPostParam);
        ObjectMapper mapper = new ObjectMapper();
        List<UserInfor> output = new ArrayList<>();
//        jsonString = jsonString.substring(jsonString.indexOf("["), jsonString.indexOf("]") + 1);
        JSONParser parser = new JSONParser();
        JSONObject jo = (JSONObject) parser.parse(jsonString);
        JSONArray jSONArray = (JSONArray) jo.get("data");
        output = mapper.readValue(jSONArray.toJSONString(), new TypeReference<List<UserInfor>>(){});
        return output;
    }
    
    public List<UserMeetPeople> getDataMeet() throws IOException, ParseException {
        
        List<UserMeetPeople> listUser = new ArrayList<>();
        
        UserMeetPeople people;
        String jsonString = getJSonData(url, meetPeoplePostParam);
        JSONParser parser = new JSONParser();
        JSONObject jo = (JSONObject) parser.parse(jsonString);
        JSONArray jSONArray = (JSONArray) jo.get("data");
        for (Iterator it = jSONArray.iterator(); it.hasNext();) {
            JSONObject obj = (JSONObject) it.next();
            people = new UserMeetPeople(obj);
            listUser.add(people);
        }
        return listUser;
    }
    
    public Long removeUser(String userId) throws ParseException {
        String param = "{" + userIdRemoveApi + "\"user_id\":\""+userId+"\"}";
        UserMeetPeople people;
        String jsonString = getJSonData(url, param);
        JSONParser parser = new JSONParser();
        JSONObject jo = (JSONObject) parser.parse(jsonString);
        Long out = (Long) jo.get("code");
        return out;
    }
    
    /**
     * Sua thong tin, re-active user
     * @param user
     * @return 0 - success
     *         1 - error
     */
    public Long editUser(UserMeetPeople user) {
        return null;
    }

    public Long reActiveUser(UserMeetPeople user) throws ParseException {
        Long codeReturn = 0L;
        JSONObject jo = new JSONObject();
        jo.put("api", reactiveUser);
        jo.put("data", user.toJsonObject().toJSONString());
        String postParam = jo.toJSONString();
        JSONParser jp = new JSONParser();
        JSONObject json = (JSONObject) jp.parse(getJSonData(url, postParam));
        codeReturn = (Long) json.get("code");
        return codeReturn;
    }
    
    public String getJSonData(String urlString, String postParam) {
            String output = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Language", "en-US");
            String input = postParam;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), StandardCharsets.UTF_8));
            output = br.readLine();
            
//            convertToMap(output.toString());
            conn.disconnect();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return output;
    }
}
