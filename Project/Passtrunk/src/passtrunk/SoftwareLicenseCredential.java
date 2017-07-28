/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

import java.util.Date;

/**
 *
 * @author Yendy
 */
public class SoftwareLicenseCredential extends Credential{
    private String softwareName;
    private String key;
    private String url;
    private String expDate;

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public String getExpDate() {
        return expDate;
    }
}
