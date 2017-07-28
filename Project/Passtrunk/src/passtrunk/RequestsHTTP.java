/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

//import java.net.HttpURLConnection;
//import java.net.URL;
import SecurityED.AESEncryptDecrypt;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yendy
 */
public class RequestsHTTP {

    private String url, type, reqbody;
    private HttpURLConnection con;
    private int code;
    private int searchUserID;

    public int getSearchUserID() {
        return searchUserID;
    }

    public int getCode() {
        return code;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReqbody(String reqbody) {
        this.reqbody = reqbody;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getReqbody() {
        return reqbody;
    }
    private final String encoding = "UTF-8";
    static User user = new User();
    
    public RequestsHTTP(){
        this.url = "";
        this.type = "";
        this.reqbody = "";
    }
    
    public RequestsHTTP(String url, String type, String reqbody)
    {
        this.url = url;
        this.type = type;
        this.reqbody = reqbody;
    }

    public boolean doTransaction(String transaction, int team) {
        
        code = 0;
       
        try {
            if (reqbody != null && !reqbody.isEmpty()) {
                
                switch(type){
                    case "GET":
                        URL uri = new URL(url + "?" + reqbody);
                        //URL tmp= new URL(url,);
                        con = (HttpURLConnection) uri.openConnection();
                        con.setRequestMethod("GET"); //type: POST, PUT, DELETE, GET
                        con.setDoInput(true);
                        con.setConnectTimeout(60000); //60 secs
                        con.setReadTimeout(60000); //60 secs
                        con.setRequestProperty("Accept-Charset", encoding);
                        con.connect();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            sb.append(inputLine);
                           // System.out.println(inputLine);
                        }
                        code = con.getResponseCode();
                        in.close();
                        if(code == 201){
                            if ("login".equals(transaction)){
                                retrieveToken(sb.toString());
                            }
                            if("getcred".equals(transaction)){
                                retrieveCredInfo(sb.toString());
                            }
                            if("getteams".equals(transaction)){
                                retrieveTeamsInfo(sb.toString());
                            }
                            if("getsettings".equals(transaction)){
                                retrieveSettings(sb.toString());
                            }
                            if("getloginlist".equals(transaction)){
                                retrieveLoginList(sb.toString());
                            }
                            if("getteamcreds".equals(transaction)){
                                retrieveTeamCredentials(sb.toString(), team);
                            }
                            if("getteammembers".equals(transaction)){
                                retrieveTeamMembers(sb.toString(), team);
                            }
                            if("searchuserid".equals(transaction)){
                                retrieveSearchUserID(sb.toString());
                            }
                        }
                        //System.out.println(code);
                        break;
                    case "POST":
                        uri = new URL(url);
                        con = (HttpURLConnection) uri.openConnection();
                        con.setRequestMethod("POST"); //following line includes the execution of this line.
                        con.setDoOutput(true);
                        con.setRequestProperty("Accept-Charset", encoding);
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
                        try (OutputStream output = con.getOutputStream()) {
                            output.write(reqbody.getBytes(encoding));
                            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String temp;
                            sb = new StringBuilder();
                            while ((temp = in.readLine()) != null) {
                                sb.append(temp);
                            }
                        }
                        code = con.getResponseCode();
                        if(code != 201){
                            //JOptionPane.showMessageDialog(null, "Accion Failed" , "ErrorBox: ", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "PUT":
                        uri = new URL(url);
                        con = (HttpURLConnection) uri.openConnection();
                        con.setRequestMethod("PUT");
                        con.setDoOutput(true);
                        con.setRequestProperty("Accept-Charset", encoding);
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
                        try (OutputStream output = con.getOutputStream()){
                            output.write(reqbody.getBytes(encoding));
                            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String temp;
                            sb = new StringBuilder();
                            while ((temp = in.readLine()) != null) {
                                sb.append(temp);
                            }
                            //JOptionPane.showMessageDialog(null, sb.toString(), "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                            //System.out.println("code is: " + code);
                        }
                        code = con.getResponseCode();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error: request type not valid.", "ErrorBox: ", JOptionPane.ERROR_MESSAGE);
                        break;
                        
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code == 201;
    }
    
    
    /*
    *****Helper Methods
    */
    //Retrieves Token and populates user class.
    private void retrieveToken(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            String token = jsonObj.getString("token");
            JSONObject userData = jsonObj.getJSONObject("userdata");
            int id = userData.getInt("userid");
            String fname = userData.getString("firstname");
            String lname = userData.getString("lastname");
            String uName = userData.getString("username");
            String email = userData.getString("emailaddress");
            String phNumber = userData.getString("phone");
            user.setToken(token);
            user.setUserID(id);
            user.setUsername(uName);
            user.setfName(fname);
            user.setlName(lname);
            user.setEmail(email);
            user.setPhNumber(phNumber);
            user.setUserFullName(fname + " " + lname);
            //System.out.println("user info: "+user.getToken()+"; "+user.getUserID()+"; "+user.getUserFullName()+".");
            
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves credentials
    private void retrieveCredInfo(String json){
        try {
            String uName = user.getUsername();
            String uToken = user.getToken();
            String keyS = uName.substring(0, 2) + uToken.substring(0,5) + uName.substring(2) + uToken.substring(5);
            
            JSONObject jsonObj = new JSONObject(json);
            JSONArray credArray = jsonObj.getJSONArray("credentials");
            //System.out.println(credArray.length());
            for(int i = 0; i < credArray.length(); i++){
                JSONObject credential = credArray.getJSONObject(i);
                String credType = credential.getString("type");
                switch(credType){
                    case "Bank Account":
                        BankAccountCredential bankAcct = new BankAccountCredential();
                        bankAcct.setName(credential.getString("name"));
                        bankAcct.setOwnerID(user.getUserID());
                        bankAcct.setCredID(credential.getInt("presetid"));
                        bankAcct.setBankName(AESEncryptDecrypt.decrypt(credential.getString("bankname"), keyS));
                        bankAcct.setBankUrl(AESEncryptDecrypt.decrypt(credential.getString("bankurl"), keyS));
                        bankAcct.setUsername(AESEncryptDecrypt.decrypt(credential.getString("bankusername"), keyS));
                        bankAcct.setPassword(AESEncryptDecrypt.decrypt(credential.getString("bankpassword"), keyS));
                        bankAcct.setRoutingNumber(AESEncryptDecrypt.decrypt(credential.getString("routingnumber"), keyS));
                        bankAcct.setAcctNumber(AESEncryptDecrypt.decrypt(credential.getString("accountnumber"), keyS));
                        user.credentials.add(bankAcct);
                        break;
                    case "Credit Card":
                        CreditCardCredential credCard = new CreditCardCredential();
                        credCard.setName(credential.getString("name"));
                        credCard.setOwnerID(user.getUserID());
                        credCard.setCredID(credential.getInt("presetid"));
                        credCard.setBankName(AESEncryptDecrypt.decrypt(credential.getString("bankname"), keyS));
                        credCard.setCardNumber(AESEncryptDecrypt.decrypt(credential.getString("creditcardnumber"), keyS)); 
                        credCard.setExpDate(AESEncryptDecrypt.decrypt(credential.getString("expdate"), keyS));
                        credCard.setNameOnCard(AESEncryptDecrypt.decrypt(credential.getString("owner"), keyS));
                        credCard.setCcvNumber(AESEncryptDecrypt.decrypt(credential.getString("ccv"), keyS));
                        credCard.setType(AESEncryptDecrypt.decrypt(credential.getString("cardtype"), keyS));
                        user.credentials.add(credCard);
                        break;
                    case "Email":
                        EmailCredential emailCred = new EmailCredential();
                        emailCred.setName(credential.getString("name"));
                        emailCred.setOwnerID(user.getUserID());
                        emailCred.setCredID(credential.getInt("presetid"));
                        emailCred.setLoginUrl(AESEncryptDecrypt.decrypt(credential.getString("loginurl"), keyS));
                        emailCred.setEmail(AESEncryptDecrypt.decrypt(credential.getString("emailaddress"), keyS));
                        emailCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        user.credentials.add(emailCred);
                        break;
                    case "Login":
                        LoginCredential loginCred = new LoginCredential();
                        loginCred.setName(credential.getString("name"));
                        loginCred.setOwnerID(user.getUserID());
                        loginCred.setCredID(credential.getInt("presetid"));
                        loginCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("username"), keyS));
                        loginCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        loginCred.setAcctEmail(AESEncryptDecrypt.decrypt(credential.getString("assocemail"), keyS));
                        user.credentials.add(loginCred);
                        break;
                    case "Software License":
                        SoftwareLicenseCredential softLicCred = new SoftwareLicenseCredential();
                        softLicCred.setName(credential.getString("name"));
                        softLicCred.setOwnerID(user.getUserID());
                        softLicCred.setCredID(credential.getInt("presetid"));
                        softLicCred.setSoftwareName(AESEncryptDecrypt.decrypt(credential.getString("softwarename"), keyS));
                        softLicCred.setKey(AESEncryptDecrypt.decrypt(credential.getString("softwarekey"), keyS));
                        softLicCred.setUrl(AESEncryptDecrypt.decrypt(credential.getString("websiteurl"), keyS));
                        softLicCred.setExpDate(AESEncryptDecrypt.decrypt(credential.getString("expdate"), keyS));
                        user.credentials.add(softLicCred);
                        break;
                    case "SSN":
                        SocialSecurityCredential ssnCred = new SocialSecurityCredential();
                        ssnCred.setName(credential.getString("name"));
                        ssnCred.setOwnerID(user.getUserID());
                        ssnCred.setCredID(credential.getInt("presetid"));
                        ssnCred.setOwnerName(AESEncryptDecrypt.decrypt(credential.getString("ownername"), keyS));
                        ssnCred.setSsn(AESEncryptDecrypt.decrypt(credential.getString("ssn"), keyS));
                        user.credentials.add(ssnCred);
                        break;
                    case "Website Login":
                        WebsiteCredential websiteCred = new WebsiteCredential();
                        websiteCred.setName(credential.getString("name"));
                        websiteCred.setOwnerID(user.getUserID());
                        websiteCred.setCredID(credential.getInt("presetid"));
                        websiteCred.setWebsiteName(AESEncryptDecrypt.decrypt(credential.getString("websitename"), keyS));
                        websiteCred.setUrl(AESEncryptDecrypt.decrypt(credential.getString("websiteurl"), keyS));
                        websiteCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("websiteusername"), keyS));
                        websiteCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("websitepassword"), keyS));
                        websiteCred.setEmail(AESEncryptDecrypt.decrypt(credential.getString("websiteassocemail"), keyS));
                        user.credentials.add(websiteCred);
                        break;
                    case "Wireless":
                        WirelessCredential wirelessCred = new WirelessCredential();
                        wirelessCred.setName(credential.getString("name"));
                        wirelessCred.setOwnerID(user.getUserID());
                        wirelessCred.setCredID(credential.getInt("presetid"));
                        wirelessCred.setSsid(AESEncryptDecrypt.decrypt(credential.getString("ssid"), keyS));
                        wirelessCred.setEncryption(AESEncryptDecrypt.decrypt(credential.getString("encryption"), keyS));
                        wirelessCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("username"), keyS));
                        wirelessCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        user.credentials.add(wirelessCred);
                        break;
                    case "Custom":
                        CustomCredential custCred = new CustomCredential();
                        custCred.setName(credential.getString("name"));
                        custCred.setOwnerID(user.getUserID());
                        custCred.setCredID(credential.getInt("presetid"));
                        
                        user.credentials.add(custCred);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error: Type of credential not valid", "ErrorBox: ", JOptionPane.ERROR_MESSAGE);
                }
            }
            //System.out.println(user.credentials.size());
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Retrieves teams
    private void retrieveTeamsInfo(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray teamArray = jsonObj.getJSONArray("teams");
            for(int i = 0; i < teamArray.length(); i++){
                JSONObject jsonTeam = teamArray.getJSONObject(i);
                Team team = new Team();
                team.setId(jsonTeam.getInt("id"));
                team.setName(jsonTeam.getString("name"));
                team.setOwnerID(jsonTeam.getInt("ownerid"));
                team.setShortName(jsonTeam.getString("shortname"));
                RequestsHTTP.user.teams.add(team);
            }
            //System.out.println(RequestsHTTP.user.teams.size());
            
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves Settings
    private void retrieveSettings(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONObject settings = jsonObj.getJSONObject("usersettings");
            int comMethod = settings.getInt("communicationmethod");
            int twoStepDel = settings.getInt("twostepdelivery");
            int timeOut = settings.getInt("timeout");
            int visibAuth= settings.getInt("visibilityauthentication");
            int twoStepEn = settings.getInt("twostepenabled");
            user.settings.setComMethod(comMethod);
            user.settings.setTwoStepDelivery(twoStepDel);
            user.settings.setTimeOut(timeOut);
            user.settings.setVisibilityAuth((visibAuth != 0));
            user.settings.setTwoStepEnabled(twoStepEn != 0);
            
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves Login list
    private void retrieveLoginList(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray loginsArray = jsonObj.getJSONArray("logins");
            for(int i = 0; i< loginsArray.length();i++){
                JSONObject jsonLog = loginsArray.getJSONObject(i);
                LoginInfo log = new LoginInfo();
                log.setDateTime(jsonLog.getString("date"));
                log.setDevice(jsonLog.getString("device"));
                log.setIpAddress(jsonLog.getString("ipaddress"));
                log.setLocation(jsonLog.getString("geo"));
                RequestsHTTP.user.loginList.add(log);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves the credentials associated with a specific team:
    private void retrieveTeamCredentials(String json, int team){
        try {
            
            JSONObject jsonObj = new JSONObject(json);
            JSONArray credArray = jsonObj.getJSONArray("credentials");
            for(int i = 0; i < credArray.length(); i++){
                JSONObject credential = credArray.getJSONObject(i);
                String keyS  = credential.getString("skey");
                String credType = credential.getString("type");
                switch(credType){
                    case "Bank Account":
                        BankAccountCredential bankAcct = new BankAccountCredential();
                        bankAcct.setName(credential.getString("name"));
                        bankAcct.setOwnerID(credential.getInt("ownerid"));
                        bankAcct.setCredID(credential.getInt("presetid"));
                        bankAcct.setBankName(AESEncryptDecrypt.decrypt(credential.getString("bankname"), keyS));
                        bankAcct.setBankUrl(AESEncryptDecrypt.decrypt(credential.getString("bankurl"), keyS));
                        bankAcct.setUsername(AESEncryptDecrypt.decrypt(credential.getString("bankusername"), keyS));
                        bankAcct.setPassword(AESEncryptDecrypt.decrypt(credential.getString("bankpassword"), keyS));
                        bankAcct.setRoutingNumber(AESEncryptDecrypt.decrypt(credential.getString("routingnumber"), keyS));
                        bankAcct.setAcctNumber(AESEncryptDecrypt.decrypt(credential.getString("accountnumber"), keyS));
                        user.teams.get(team).teamCreds.add(bankAcct);
                        break;
                    case "Credit Card":
                        CreditCardCredential credCard = new CreditCardCredential();
                        credCard.setName(credential.getString("name"));
                        credCard.setOwnerID(credential.getInt("ownerid"));
                        credCard.setCredID(credential.getInt("presetid"));
                        credCard.setBankName(AESEncryptDecrypt.decrypt(credential.getString("bankname"), keyS));
                        credCard.setCardNumber(AESEncryptDecrypt.decrypt(credential.getString("creditcardnumber"), keyS)); 
                        credCard.setExpDate(AESEncryptDecrypt.decrypt(credential.getString("expdate"), keyS));
                        credCard.setNameOnCard(AESEncryptDecrypt.decrypt(credential.getString("owner"), keyS));
                        credCard.setCcvNumber(AESEncryptDecrypt.decrypt(credential.getString("ccv"), keyS));
                        credCard.setType(credential.getString("cardtype"));
                        user.teams.get(team).teamCreds.add(credCard);
                        break;
                    case "Email":
                        EmailCredential emailCred = new EmailCredential();
                        emailCred.setName(credential.getString("name"));
                        emailCred.setOwnerID(credential.getInt("ownerid"));
                        emailCred.setCredID(credential.getInt("presetid"));
                        emailCred.setLoginUrl(AESEncryptDecrypt.decrypt(credential.getString("loginurl"), keyS));
                        emailCred.setEmail(AESEncryptDecrypt.decrypt(credential.getString("emailaddress"), keyS));
                        emailCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        user.teams.get(team).teamCreds.add(emailCred);
                        break;
                    case "Login":
                        LoginCredential loginCred = new LoginCredential();
                        loginCred.setName(credential.getString("name"));
                        loginCred.setOwnerID(credential.getInt("ownerid"));
                        loginCred.setCredID(credential.getInt("presetid"));
                        loginCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("username"), keyS));
                        loginCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        loginCred.setAcctEmail(AESEncryptDecrypt.decrypt(credential.getString("assocemail"), keyS));
                        user.teams.get(team).teamCreds.add(loginCred);
                        break;
                    case "Software License":
                        SoftwareLicenseCredential softLicCred = new SoftwareLicenseCredential();
                        softLicCred.setName(credential.getString("name"));
                        softLicCred.setOwnerID(credential.getInt("ownerid"));
                        softLicCred.setCredID(credential.getInt("presetid"));
                        softLicCred.setSoftwareName(AESEncryptDecrypt.decrypt(credential.getString("softwarename"), keyS));
                        softLicCred.setKey(AESEncryptDecrypt.decrypt(credential.getString("softwarekey"), keyS));
                        softLicCred.setUrl(AESEncryptDecrypt.decrypt(credential.getString("websiteurl"), keyS));
                        softLicCred.setExpDate(AESEncryptDecrypt.decrypt(credential.getString("expdate"), keyS));
                        user.teams.get(team).teamCreds.add(softLicCred);
                        break;
                    case "SSN":
                        SocialSecurityCredential ssnCred = new SocialSecurityCredential();
                        ssnCred.setName(credential.getString("name"));
                        ssnCred.setOwnerID(credential.getInt("ownerid"));
                        ssnCred.setCredID(credential.getInt("presetid"));
                        ssnCred.setOwnerName(AESEncryptDecrypt.decrypt(credential.getString("ownername"), keyS));
                        ssnCred.setSsn(AESEncryptDecrypt.decrypt(credential.getString("ssn"), keyS));
                        user.teams.get(team).teamCreds.add(ssnCred);
                        break;
                    case "Website Login":
                        WebsiteCredential websiteCred = new WebsiteCredential();
                        websiteCred.setName(credential.getString("name"));
                        websiteCred.setOwnerID(credential.getInt("ownerid"));
                        websiteCred.setCredID(credential.getInt("presetid"));
                        websiteCred.setWebsiteName(AESEncryptDecrypt.decrypt(credential.getString("websitename"), keyS));
                        websiteCred.setUrl(AESEncryptDecrypt.decrypt(credential.getString("websiteurl"), keyS));
                        websiteCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("websiteusername"), keyS));
                        websiteCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("websitepassword"), keyS));
                        websiteCred.setEmail(AESEncryptDecrypt.decrypt(credential.getString("websiteassocemail"), keyS));
                        user.teams.get(team).teamCreds.add(websiteCred);
                        break;
                    case "Wireless":
                        WirelessCredential wirelessCred = new WirelessCredential();
                        wirelessCred.setName(credential.getString("name"));
                        wirelessCred.setOwnerID(credential.getInt("ownerid"));
                        wirelessCred.setCredID(credential.getInt("presetid"));
                        wirelessCred.setSsid(AESEncryptDecrypt.decrypt(credential.getString("ssid"), keyS));
                        wirelessCred.setEncryption(AESEncryptDecrypt.decrypt(credential.getString("encryption"), keyS));
                        wirelessCred.setUsername(AESEncryptDecrypt.decrypt(credential.getString("username"), keyS));
                        wirelessCred.setPassword(AESEncryptDecrypt.decrypt(credential.getString("password"), keyS));
                        user.teams.get(team).teamCreds.add(wirelessCred);
                        break;
                    case "Custom":
                        CustomCredential custCred = new CustomCredential();
                        custCred.setName(credential.getString("name"));
                        custCred.setOwnerID(credential.getInt("ownerid"));
                        custCred.setCredID(credential.getInt("presetid"));
                        user.teams.get(team).teamCreds.add(custCred);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error: Type of credential not valid", "ErrorBox: ", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves the team Members of a specific team
    private void retrieveTeamMembers(String json, int team){
        
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray membArray = jsonObj.getJSONArray("members");
            for(int i = 0; i < membArray.length(); i++){
                JSONObject member = membArray.getJSONObject(i);
                int id = member.getInt("userid");
                String fname = member.getString("firstname");
                String lname = member.getString("lastname");
                String uName = member.getString("username");
                String email = member.getString("emailaddress");
                String phNumber = member.getString("phone");
                User memb = new User();
                memb.setEmail(email);
                memb.setPhNumber(phNumber);
                memb.setUserID(id);
                memb.setUsername(uName);
                memb.setfName(fname);
                memb.setlName(lname);
                memb.setUserFullName(fname + " " + lname);
                user.teams.get(team).teamMembers.add(memb);
            }
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Retrieves a user ID given username.
    private void retrieveSearchUserID(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONObject userData = jsonObj.getJSONObject("userdata");
            searchUserID = userData.getInt("userid");
        } catch (JSONException ex) {
            Logger.getLogger(RequestsHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
