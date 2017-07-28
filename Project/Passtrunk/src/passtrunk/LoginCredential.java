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
public class LoginCredential extends Credential {
    private String username;
    private String password;
    private String acctEmail;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAcctEmail(String acctEmail) {
        this.acctEmail = acctEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAcctEmail() {
        return acctEmail;
    }
}
