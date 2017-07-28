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
public class EmailCredential extends Credential{
    private String loginUrl;
    private String email;
    private String password;

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
