/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

/**
 *
 * @author Yendy
 */
public class WirelessCredential extends Credential {
    private String ssid;
    private String encryption;
    private String username;
    private String password;

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsid() {
        return ssid;
    }

    public String getEncryption() {
        return encryption;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
