/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

import java.util.ArrayList;

/**
 *
 * @author Yendy
 */
public class User {
    private String userFullName;
    private String fName;
    private String lName;
    private String username;
    private String email;
    private String phNumber;
    private int userID;
    private String token;
    ArrayList<Credential> credentials;
    ArrayList<Team> teams;
    ArrayList<LoginInfo> loginList;
    Settings settings;

    public User (){
        credentials = new ArrayList();
        teams = new ArrayList();
        loginList = new ArrayList();
        settings = new Settings();                
    }
    
    public void setUserFullName(String userName) {
        this.userFullName = userName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }
    
    public String getUsername() {
        return username;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public int getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }
    
}
