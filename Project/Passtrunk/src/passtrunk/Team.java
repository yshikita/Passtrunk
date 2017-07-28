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
public class Team {
    
    private int id;
    private int ownerID;
    private String name;
    private String shortName;
    ArrayList<Credential> teamCreds;
    ArrayList<User> teamMembers;
    
    public Team() {
        this.teamCreds = new ArrayList();
        this.teamMembers = new ArrayList();
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
