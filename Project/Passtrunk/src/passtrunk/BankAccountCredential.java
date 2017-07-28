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
public class BankAccountCredential extends Credential{
    private String bankName;
    private String bankUrl;
    private String username;
    private String password;
    private String acctNumber;
    private String routingNumber;
    
    public String getBankName() {
        return bankName;
    }

    public String getBankUrl() {
        return bankUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }
}
