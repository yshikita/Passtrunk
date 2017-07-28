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
public class SocialSecurityCredential extends Credential {
    private String ownerName;
    private String ssn;

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getSsn() {
        return ssn;
    }
}
